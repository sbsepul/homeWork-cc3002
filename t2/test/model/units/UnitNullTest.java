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

package model.units;

import controller.Tactician;
import model.items.IEquipableItem;
import model.items.factoryItem.BowFactoryItem;
import model.items.factoryItem.IFactoryItem;
import model.map.Location;
import model.units.handlers.ResponseUnitMovement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnitNullTest {
    IUnit unitNull, unitNormal;
    IFactoryItem fabBow = new BowFactoryItem();
    IEquipableItem item;

    @BeforeEach
    public void setUp(){
        unitNull = new UnitNull();
        IEquipableItem item = fabBow.createItem();
        IUnit unitNormal = new Hero(50,2,new Location(0,0));
    }



    @Test
    public void testMethod(){
        unitNull.addItem(item);
        assertNull(unitNull.getItems());
        unitNull.equipItem(item);
        assertNull(unitNull.getEquippedItem());
        assertNull(unitNull.getItems());
        assertNull(unitNull.getLocation());
        assertNull(unitNull.getTactician());
        assertEquals(0,unitNull.getCurrentHitPoints());
        assertEquals(0, unitNull.getMovement());
        assertEquals(0, unitNull.getMaxCurrentHitPoints());
        assertFalse(unitNull.isInRange(unitNormal));
        assertFalse(unitNull.isEquipable());
        assertFalse(unitNull.canCounterAttack(1));
        assertFalse(unitNull.canExchange(unitNormal, item));
        assertFalse(unitNull.initCombat(unitNormal));
        assertTrue(unitNull.isItemFull());
        unitNull.setEquippedItem(item);
        assertNull(unitNull.getEquippedItem());
        unitNull.setLocation(new Location(0,0));
        assertNull(unitNull.getLocation());
        unitNull.setTactician(new Tactician("Player"));
        assertNull(unitNull.getTactician());
        unitNull.attack(unitNormal);
        assertEquals(0, unitNull.getCurrentHitPoints());
        unitNull.receiveAttack(item);
        assertEquals(0, unitNull.getCurrentHitPoints());
        unitNull.receiveRecovery(item);
        assertEquals(0, unitNull.getCurrentHitPoints());
        unitNull.receiveAttackWeakness(item);
        assertEquals(0, unitNull.getCurrentHitPoints());
        unitNull.receiveAttackResistant(item);
        assertEquals(0, unitNull.getCurrentHitPoints());
        unitNull.changeEquippedItem(item);
        unitNull.giveItem(unitNormal,item);
        unitNull.removeItem(item);
        unitNull.addObserverMovement(new ResponseUnitMovement(new Tactician("")));
        unitNull.moveTo(new Location(1,0));
        assertNull(unitNull.getLocation());
    }
}