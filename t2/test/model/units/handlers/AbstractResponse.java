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

package model.units.handlers;

import controller.Tactician;
import model.items.factoryItem.AxeFactoryItem;
import model.items.factoryItem.IFactoryItem;
import model.items.factoryItem.SpearFactoryItem;
import model.items.factoryItem.StaffFactoryItem;
import model.map.Field;
import model.map.Location;
import model.map.factoryMap.FactoryMap;
import model.map.factoryMap.IFactoryMap;
import model.units.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public abstract class AbstractResponse {
    protected IFactoryMap map = new FactoryMap(3);
    protected Field field;
    protected Tactician tacticianTest;
    protected Tactician tacticianTarget;
    protected NormalUnit
            normalUnitTest,
            normalUnitTarget,
            cleric;
    protected SpecialUnit
            specialUnitTest,
            specialUnitTarget;
    protected IFactoryItem fabAxe = new AxeFactoryItem();
    protected IFactoryItem fabSpear = new SpearFactoryItem();
    protected IFactoryItem fabStaff = new StaffFactoryItem();


    @BeforeEach
    void setUp(){
        setTactician();
        setResponse();
        setField();
        setUnits();
        setEquipUnit();
        System.out.println(getField().toString());
    }

    public void setEquipUnit(){
        getTacticianTest().addUnitInventory(getNormalUnitTest());
        getTacticianTest().addUnitHero(getSpecialUnitTest());

        assertEquals(getTacticianTest(), getNormalUnitTest().getTactician());
        assertEquals(getTacticianTest(), getSpecialUnitTest().getTactician());

        getTacticianTarget().addUnitInventory(getNormalUnitTarget());
        getTacticianTarget().addUnitHero(getSpecialUnitTarget());

        assertEquals(getTacticianTarget(), getNormalUnitTarget().getTactician());
        assertEquals(getTacticianTarget(), getSpecialUnitTarget().getTactician());
    }

    public void setTactician(){
        tacticianTest = new Tactician("Player 1");
        tacticianTarget = new Tactician("Player 2");
    }

    public void setField() {
        field = map.createMap();
    }

    public Field getField(){
        return field;
    }

    public abstract void setResponse();

    public void setUnits(){
        normalUnitTest = new Fighter(
                50,2, getField().getCell(0,0));
        normalUnitTest.addItem(fabAxe.createItem());
        normalUnitTest.equipItem(normalUnitTest.getItems().get(0));

        normalUnitTarget = new Fighter(
                50, 2, getField().getCell(1,0));
        normalUnitTarget.addItem(fabAxe.createItem());
        normalUnitTarget.equipItem(normalUnitTarget.getItems().get(0));

        specialUnitTest = new Hero(
                50, 2, getField().getCell(0,1));

        specialUnitTest.addItem(fabSpear.createItem());
        specialUnitTest.equipItem(specialUnitTest.getItems().get(0));

        specialUnitTarget = new Hero(
                50, 2, field.getCell(1,1));

        specialUnitTarget.addItem(fabSpear.createItem());
        specialUnitTarget.equipItem(specialUnitTarget.getItems().get(0));

        cleric = new Cleric(
                50,2,getField().getCell(0,2)
        );
        cleric.addItem(fabStaff.createItem());
        cleric.equipItem(cleric.getItems().get(0));
    }

    public NormalUnit getCleric() {
        return cleric;
    }

    public NormalUnit getNormalUnitTest() {
        return normalUnitTest;
    }

    public NormalUnit getNormalUnitTarget() {
        return normalUnitTarget;
    }

    public SpecialUnit getSpecialUnitTest(){
        return specialUnitTest;
    }

    public SpecialUnit getSpecialUnitTarget() {
        return specialUnitTarget;
    }

    public abstract IResponseToTactician getListener();

    @Test
    public void testConstructor(){
        assertEquals(tacticianTest, getListener().getResponse());
    }

    public Tactician getTacticianTest(){
        return tacticianTest;
    }

    public Tactician getTacticianTarget() {
        return tacticianTarget;
    }

}
