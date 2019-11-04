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

package controller;

import model.items.IEquipableItem;
import model.items.factoryItem.IFactoryItem;
import model.map.Field;
import model.units.IUnit;
import model.units.NormalUnit;
import model.units.SpecialUnit;
import model.units.factoryUnit.IFactoryUnit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Sebastian Sepulveda
 * @version 1.0
 * @since 2.0
 *
 */

public class TacticianTest {
    private Tactician tactician01;
    private GameController controller;

    @BeforeEach
    public void setUp(){
        controller = new GameController(2,9);
        tactician01 = new Tactician("Player 0");
    }

    @Test
    public void constructorTactician(){
        String name = "Player 0";
        assertEquals(name, tactician01.getName());
        assertTrue(tactician01.getStatus());
    }

    @Test
    public void getCurrentUnit(){
        IUnit unit = controller.getArcherFab().createUnit();
        assertNull(tactician01.getCurrentUnit());
        tactician01.setCurrentUnit(unit);
        assertNull(tactician01.getCurrentUnit());
        tactician01.addUnitInventory((NormalUnit) unit);
        assertEquals(1,tactician01.getUnits().size());
        tactician01.setCurrentUnit(unit);
        assertEquals(unit.getClass(),tactician01.getCurrentUnit().getClass());
    }
    @Test
    public void removeUnit(){
        IFactoryUnit fab = controller.getArcherFab();
        IUnit unit1 = fab.createUnit();
        tactician01.addUnitInventory((NormalUnit) unit1);
        assertEquals(1,tactician01.getUnits().size());
        assertTrue(tactician01.getUnits().contains(unit1));
        IUnit unit2 = fab.createUnit();
        tactician01.addUnitInventory((NormalUnit) unit2);
        assertEquals(2,tactician01.getUnits().size());
        assertTrue(tactician01.getUnits().contains(unit2));
        tactician01.removeUnit((NormalUnit) unit1);
        assertEquals(1,tactician01.getUnits().size());
        assertFalse(tactician01.getUnits().contains(unit1));
        assertTrue(tactician01.getUnits().contains(unit2));
    }

    @Test
    public void addNormalUnit(){
        NormalUnit normalUnit01 = (NormalUnit) controller.getFighterFab().createUnit();
        tactician01.addUnitInventory(normalUnit01);
        assertTrue(tactician01.getUnits().contains(normalUnit01));
    }

    @Test
    public void wrongAddNormalUnit(){
        SpecialUnit normalWrong = controller.getHeroFab().createUnit();
        boolean b = false;
        try{
            tactician01.addUnitInventory((NormalUnit) normalWrong);
        }
        catch (ClassCastException e){
            b = true;
        }
        assertTrue(b);
    }

    @Test
    public void addSpecialUnit(){
        SpecialUnit specialUnit = controller.getHeroFab().createUnit();
        tactician01.addUnitHero(specialUnit);
        assertTrue(tactician01.getUnits().contains(specialUnit));
    }

    @Test
    public void addWrongSpecialUnit(){
        NormalUnit normalUnit = (NormalUnit) controller.getAlpacaFab().createUnit();
        boolean b = false;
        try{
            tactician01.addUnitHero((SpecialUnit) normalUnit);
        }
        catch (ClassCastException e){
            b = true;
        }
        assertTrue(b);
    }

    @Test
    public void getUnits(){
        assertEquals(0, tactician01.getUnits().size());
        NormalUnit alpaca = (NormalUnit) controller.getAlpacaFab().createUnit();
        tactician01.addUnitInventory(alpaca);
        assertEquals(1,tactician01.getUnits().size());
        assertTrue(tactician01.getUnits().contains(alpaca));
        NormalUnit fighter = (NormalUnit) controller.getFighterFab().createUnit();
        tactician01.addUnitInventory(fighter);
        assertEquals(2, tactician01.getUnits().size());
        assertTrue(tactician01.getUnits().contains(fighter));
        assertEquals(alpaca, tactician01.getUnits().get(0));
        assertEquals(fighter, tactician01.getUnits().get(1));
    }

    @Test
    public void selectCurrentUnit(){
        NormalUnit archer = (NormalUnit) controller.getArcherFab().createUnit();
        NormalUnit alpaca = (NormalUnit) controller.getAlpacaFab().createUnit();
        SpecialUnit hero = controller.getHeroFab().createUnit();
        tactician01.addUnitInventory(archer);
        tactician01.addUnitInventory(alpaca);
        tactician01.addUnitHero(hero);
        assertNull(tactician01.getCurrentUnit());
        assertEquals(3, tactician01.getUnits().size());
        tactician01.setCurrentUnit(archer);
        assertEquals(archer, tactician01.getCurrentUnit());
        tactician01.setCurrentUnit(hero);
        assertEquals(hero, tactician01.getCurrentUnit());
        tactician01.setCurrentUnit(alpaca);
        assertEquals(alpaca, tactician01.getCurrentUnit());
    }

    @Test
    public void getHitPoints(){
        NormalUnit sorcerer = (NormalUnit) controller.getSorcererFab().createUnit();
        NormalUnit cleric = (NormalUnit) controller.getClericFab().createUnit();
        tactician01.addUnitInventory(sorcerer);
        tactician01.addUnitInventory(cleric);
        tactician01.setCurrentUnit(sorcerer);
        assertEquals(50, tactician01.hitPointsCurrentUnit());
        assertEquals(tactician01.maxHitPointsCurrentUnit(), tactician01.hitPointsCurrentUnit());
        tactician01.setCurrentUnit(cleric);
        assertEquals(50, tactician01.hitPointsCurrentUnit());
        assertEquals(tactician01.maxHitPointsCurrentUnit(), tactician01.hitPointsCurrentUnit());
    }

    @Test
    public void getItems(){
        IUnit swordmaster = controller.getSwordMasterFab().createUnit();
        tactician01.addUnitInventory((NormalUnit) swordmaster);
        tactician01.setCurrentUnit(swordmaster);
        assertEquals(0, tactician01.getItemsCurrentUnit().size());
        IEquipableItem sword = controller.getSwordFab().createItem();
        IEquipableItem staff = controller.getStaffFab().createItem();
        tactician01.getCurrentUnit().addItem(sword);
        tactician01.getCurrentUnit().addItem(staff);
        assertEquals(2, tactician01.getItemsCurrentUnit().size());
        assertEquals(sword, tactician01.getItemsCurrentUnit().get(0));
        assertEquals(staff, tactician01.getItemsCurrentUnit().get(1));
    }

    @Test
    public void getEquippedItem(){
        tacticianUnits();
        assertFalse(tactician01.getEquipItemCurrentUnit().isUtil());
        IFactoryItem fabBow = controller.getBowFab();
        IEquipableItem bow01 = fabBow.createItem();
        tactician01.getCurrentUnit().addItem(bow01);
        tactician01.setEquipItem(bow01);
        assertEquals(bow01, tactician01.getEquipItemCurrentUnit());
        IEquipableItem bow02 = fabBow.createItem();
        tactician01.getCurrentUnit().addItem(bow02);
        tactician01.setEquipItem(bow02);
        assertEquals(bow02, tactician01.getEquipItemCurrentUnit());
    }

    private void tacticianUnits(){
        IUnit archer = controller.getArcherFab().createUnit();
        tactician01.addUnitInventory((NormalUnit) archer);
        tactician01.setCurrentUnit(archer);
    }

}
