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

import model.items.Spear;
import model.items.factoryItem.FactoryItemProvider;
import model.items.factoryItem.IFactoryItem;
import model.items.factoryItem.ItemType;
import model.units.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FactoryProviderUnitTest {
    private FactoryProviderUnit factory;

    @BeforeEach
    public void setUp() {
        factory = new FactoryProviderUnit();
    }

    @Test
    public void makeUnitAlpaca(){
        IFactoryUnit alpaca = factory.makeUnit(UnitType.ALPACA);
        assertEquals(alpaca.createUnit().getClass(), Alpaca.class);
    }

    @Test
    public void makeUnitArcher(){
        IFactoryUnit archer = factory.makeUnit(UnitType.ARCHER);
        assertEquals(archer.createUnit().getClass(),Archer.class);
    }

    @Test
    public void makeUnitCleric(){
        IFactoryUnit cleric = factory.makeUnit(UnitType.CLERIC);
        assertEquals(cleric.createUnit().getClass(),Cleric.class);
    }

    @Test
    public void makeUnitFighter(){
        IFactoryUnit fighter = factory.makeUnit(UnitType.FIGHTER);
        assertEquals(fighter.createUnit().getClass(),Fighter.class);
    }

    @Test
    public void makeUnitHero(){
        IFactoryUnit hero = factory.makeUnit(UnitType.HERO);
        assertEquals(hero.createUnit().getClass(),Hero.class);
    }
    @Test
    public void makeUnitSorcerer(){
        IFactoryUnit sorcerer = factory.makeUnit(UnitType.SORCERER);
        assertEquals(sorcerer.createUnit().getClass(),Sorcerer.class);
    }
    @Test
    public void makeUnitSwordMaster(){
        IFactoryUnit swordmaster = factory.makeUnit(UnitType.SWORDMASTER);
        assertEquals(swordmaster.createUnit().getClass(),SwordMaster.class);
    }

    @Test
    public void illegalItemArgument() throws IllegalArgumentException{
        boolean thrown = false;
        try {
            IFactoryUnit aUnit = factory.makeUnit(UnitType.OTHER);
        } catch (IllegalArgumentException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }
}
