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

package model.items.factoryItem;

import model.items.*;
import model.items.magic.Darkness;
import model.items.magic.Light;
import model.items.magic.Soul;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FactoryItemProviderTest {
    private FactoryItemProvider factory;

    @BeforeEach
    public void setUp() {
        factory = new FactoryItemProvider();
    }

    @Test
    public void makeItemBow(){
        IFactoryItem bow = factory.makeItem(ItemType.BOW);
        assertEquals(bow.createItem().getClass(), Bow.class);
    }

    @Test
    public void makeItemAxe(){
        IFactoryItem axe = factory.makeItem(ItemType.AXE);
        assertEquals(axe.createItem().getClass(), Axe.class);
    }

    @Test
    public void makeItemLight(){
        IFactoryItem light = factory.makeItem(ItemType.LIGHT);
        assertEquals(light.createItem().getClass(), Light.class);
    }

    @Test
    public void makeItemDarkness(){
        IFactoryItem darkness = factory.makeItem(ItemType.DARKNESS);
        assertEquals(darkness.createItem().getClass(), Darkness.class);
    }

    @Test
    public void makeItemSoul(){
        IFactoryItem soul = factory.makeItem(ItemType.SOUL);
        assertEquals(soul.createItem().getClass(), Soul.class);
    }
    @Test
    public void makeItemSpear(){
        IFactoryItem spear = factory.makeItem(ItemType.SPEAR);
        assertEquals(spear.createItem().getClass(), Spear.class);
    }
    @Test
    public void makeItemStaff(){
        IFactoryItem staff = factory.makeItem(ItemType.STAFF);
        assertEquals(staff.createItem().getClass(), Staff.class);
    }
    @Test
    public void makeItemSword(){
        IFactoryItem sword = factory.makeItem(ItemType.SWORD);
        assertEquals(sword.createItem().getClass(), Sword.class);
    }
    @Test
    public void illegalItemArgument() throws IllegalArgumentException{
        boolean thrown = false;
        try {
            IFactoryItem aItem = factory.makeItem(ItemType.OTHER);
        } catch (IllegalArgumentException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }
}