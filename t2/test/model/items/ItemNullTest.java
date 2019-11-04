/*
 * The MIT License
 *
 * Copyright (c) 2019 Google, Inc. http://angularjs.org
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package model.items;

import model.items.magic.Darkness;
import model.items.magic.Light;
import model.items.magic.Soul;
import model.units.IUnit;
import model.units.factoryUnit.ArcherFactory;
import model.units.factoryUnit.IFactoryUnit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemNullTest {
    IFactoryUnit fabArcher = new ArcherFactory();
    IUnit unit;
    IEquipableItem itemNull, item;
    IUnit target;



    @BeforeEach
    public void setUp(){
        unit = fabArcher.createUnit();
        target = fabArcher.createUnit();
        itemNull = new ItemNull();
        item = new Bow("bow", 10, 2,3);
    }

    @Test
    public void methodTest(){
        assertEquals(unit.getEquippedItem().getClass(), itemNull.getClass());
        itemNull.equipTo(unit);
        assertEquals(unit.getEquippedItem(), itemNull);
        assertFalse(itemNull.isUtil());
        assertFalse(itemNull.canAttack(item));
        assertFalse(itemNull.inRangeItem());
        assertNull(itemNull.getOwner());
        assertEquals("", itemNull.getName());
        assertEquals(0, itemNull.getPower());
        assertEquals(0, itemNull.getMinRange());
        assertEquals(0, itemNull.getMaxRange());
        itemNull.setDistance(1);
        assertEquals(0, itemNull.getDistance());
        itemNull.receiveAxeAttack(new Axe("",1,1,2));
        itemNull.receiveSoulAttack(new Soul("",1,1,1));
        itemNull.receiveLightAttack(new Light("",1,1,1));
        itemNull.receiveDarknessAttack(new Darkness("",1,1,1));
        itemNull.receiveSwordsAttack(new Sword("",1,1,1));
        itemNull.receiveSpearsAttack(new Spear("",1,1,1));
        itemNull.receiveBowAttack(new Bow("",1,1,1));
        itemNull.receiveUnitAttack(item);
        itemNull.receiveUnitResistantAttack(item);
        itemNull.receiveUnitWeaknessAttack(item);
        itemNull.giveMagicAttack(item);
        itemNull.setOwner(unit);
        itemNull.setOwner(target);
        assertFalse(itemNull.equals(target.getEquippedItem()));
    }

}