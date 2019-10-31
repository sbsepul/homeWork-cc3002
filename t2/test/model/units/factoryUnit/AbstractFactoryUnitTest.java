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

package model.units.factoryUnit;

import model.items.IEquipableItem;
import model.items.factoryItem.*;
import model.map.InvalidLocation;
import model.map.Location;
import model.units.IUnit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public abstract class AbstractFactoryUnitTest implements ITestFactoryUnit {
    protected int expectedHP;
    protected int expectedMovement;
    protected Location expectedLocation;
    protected IUnit unitCreated;
    protected IEquipableItem expectedItem;
    protected IEquipableItem[] expectedInventory;
    protected IFactoryItem factoryItem1 = new AxeFactoryItem();
    protected IFactoryItem factoryItem2 = new SwordFactoryItem();
    protected IFactoryItem factoryItem3 = new BowFactoryItem();
    protected IFactoryItem factoryItem4 = new StaffFactoryItem();
    protected IEquipableItem armyDefault;

    @BeforeEach
    public void setUp() {
        setFactory();
        setEquippedItem();
        expectedHP = 50;
        expectedLocation = new InvalidLocation();
        expectedMovement = 2;
        expectedInventory = new IEquipableItem[]{factoryItem1.createItem()
                ,factoryItem2.createItem(),factoryItem3.createItem()};
        expectedItem = factoryItem4.createItem();
    }

    @Override
    public void setEquippedItem() {
        armyDefault = factoryItem3.createItem();
    }

    @Override
    public IEquipableItem getArmyDefault() {
        return armyDefault;
    }

    @Test
    public void addEquippedItemTest(){
        getFactory().addItemForDefault();
        IUnit unitCreated = getFactory().createUnit();
        assertEquals(1, unitCreated.getItems().size());
        assertEquals(getArmyDefault().getClass(), unitCreated.getItems().get(0).getClass());
    }

    public IEquipableItem getExpectedItem() {
        return expectedItem;
    }

    public int getExpectedHP() {
        return expectedHP;
    }

    public int getExpectedMovement() {
        return expectedMovement;
    }

    public IEquipableItem[] getExpectedInventory() {
        return expectedInventory;
    }

    public Location getExpectedLocation() {
        return expectedLocation;
    }

    public IUnit getUnitCreated() {
        return unitCreated;
    }

    protected abstract void setFactory();

    protected abstract IFactoryUnit getFactory();

    @Test
    public void constructorTest(){
        assertEquals(getExpectedHP(), getFactory().getHp());
        assertEquals(getExpectedLocation(), getFactory().getLocation());
        assertEquals(getExpectedMovement(),getFactory().getMove());
    }

    @Test
    public void setItems(){
        getFactory().setItems(getExpectedInventory());
        assertEquals(getExpectedInventory(), getFactory().getItemAll());
        getFactory().setItems(getExpectedItem());
        assertEquals(1,getFactory().getItemAll().length);
        getFactory().setItems(null);
        assertNull(getFactory().getItemAll());
    }

    @Test
    public void setLocation(){
        Location loc = new Location(0,0);
        getFactory().setLocation(loc);
        assertEquals(loc,getFactory().getLocation());
    }
    @Test
    public void createUnitTest(){
        IUnit fabUnit = getFactory().createUnit();
        assertEquals(getUnitCreated().getClass(), fabUnit.getClass());
        assertEquals(getUnitCreated().getLocation(), fabUnit.getLocation());
        assertEquals(getUnitCreated().getCurrentHitPoints(), fabUnit.getCurrentHitPoints());
        assertEquals(getUnitCreated().getMovement(), fabUnit.getMovement());
        Location loc = new Location(0,0);
        getFactory().setLocation(loc);
        getFactory().setItems(getExpectedItem());
        IUnit unitChange = getFactory().createUnit();
        assertEquals(getExpectedItem(), unitChange.getItems().get(0));
        assertEquals(loc,unitChange.getLocation());
    }


}
