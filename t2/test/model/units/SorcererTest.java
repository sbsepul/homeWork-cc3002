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

import model.items.IEquipableItem;
import model.items.magic.Darkness;
import model.items.magic.Light;
import model.items.magic.Soul;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * Test set for the Sorcerer unit
 *
 * @author Sebastian Sepulveda
 * @since 1.0
 */
public class SorcererTest extends AbstractTestUnit {
    private Sorcerer sorcerer;
    private Light light_p;
    private Darkness darkness_p;
    private Soul soul_p;

    @Override
    public void setTestUnit() {
        sorcerer = new Sorcerer(50, 2, field.getCell(0, 0));
        light_p = new Light("Light_private", 20, 1,2);
        darkness_p = new Darkness("Darkness_private", 20,1,2);
        soul_p = new Soul("Soul_private", 20, 1,2);
    }

    @Override
    public IUnit getTestUnit() {
        return sorcerer;
    }

    /**
     * Test that a Sorcerer equip a Darkness correctly and can add items
     */
    @Test
    @Override
    public void equipDarknessTest() {
        assertFalse(sorcerer.getEquippedItem().isUtil());
        sorcerer.equipItem(darkness);
        assertFalse(sorcerer.getEquippedItem().isUtil());
        sorcerer.addItem(darkness);
        sorcerer.equipItem(darkness);
        assertEquals(darkness, sorcerer.getEquippedItem());
        assertEquals(1, sorcerer.getItems().size());
        sorcerer.addItem(darkness_p);
        assertEquals(2 , sorcerer.getItems().size());
        sorcerer.equipItem(axe);
        assertEquals(darkness, sorcerer.getEquippedItem());
        sorcerer.addItem(sword);
        assertEquals(3 , sorcerer.getItems().size());
    }

    /**
     * Test that a Sorcerer equip a Light correctly and can add items
     */
    @Test
    @Override
    public void equipLightTest() {
        assertFalse(sorcerer.getEquippedItem().isUtil());
        sorcerer.equipItem(light);
        assertFalse(sorcerer.getEquippedItem().isUtil());
        sorcerer.addItem(light);
        sorcerer.equipItem(light);
        assertEquals(light, sorcerer.getEquippedItem());
        assertEquals(1, sorcerer.getItems().size());
        sorcerer.addItem(light_p);
        assertEquals(2 , sorcerer.getItems().size());
        sorcerer.equipItem(axe);
        assertEquals(light, sorcerer.getEquippedItem());
        sorcerer.addItem(sword);
        assertEquals(3 , sorcerer.getItems().size());
    }

    /**
     * Test that a Sorcerer equip a Soul correctly and can add items
     */
    @Test
    @Override
    public void equipSoulTest() {
        assertFalse(sorcerer.getEquippedItem().isUtil());
        sorcerer.equipItem(soul);
        assertFalse(sorcerer.getEquippedItem().isUtil());
        sorcerer.addItem(soul);
        sorcerer.equipItem(soul);
        assertEquals(soul, sorcerer.getEquippedItem());
        assertEquals(1, sorcerer.getItems().size());
        sorcerer.addItem(soul_p);
        assertEquals(2 , sorcerer.getItems().size());
        sorcerer.equipItem(axe);
        assertEquals(soul, sorcerer.getEquippedItem());
        sorcerer.addItem(sword);
        assertEquals(3 , sorcerer.getItems().size());
    }

    /**
     * Test in item
     */
    @Test
    @Override
    public void giveToUnitSorcererTest() {
        assertFalse(getTestUnit().getEquippedItem().isUtil());
        assertEquals(0, getTestUnit().getItems().size());
        getTestUnit().giveItem(getTargetArcherTrade(), light_p);
        assertEquals(2, getTargetArcherTrade().getItems().size());
        assertEquals(false, getTargetArcherTrade().getItems().contains(light_p));
        getTestUnit().addItem(light_p);
        assertFalse(getTestUnit().getEquippedItem().isUtil());
        assertEquals(1, getTestUnit().getItems().size());
        assertEquals(true, getTestUnit().getItems().contains(light_p));
        getTestUnit().equipItem(light_p);
        assertEquals(light_p, getTestUnit().getEquippedItem());
        getTestUnit().giveItem(getTargetArcherTrade(),light_p);
        assertEquals(0,getTestUnit().getItems().size());
        //verify that light_p equipped isn't
        assertFalse(getTestUnit().getEquippedItem().isUtil());
        assertEquals(3,getTargetArcherTrade().getItems().size());
        assertEquals(true, getTargetArcherTrade().getItems().contains(light_p));
        getTestUnit().addItem(getAxeTrade());
        assertEquals(true, getTestUnit().getItems().contains(getAxeTrade()));
        getTestUnit().giveItem(getTargetArcherTrade(),getAxeTrade());
        assertEquals(true, getTestUnit().getItems().contains(getAxeTrade()));
        assertEquals(false,getTargetArcherTrade().getItems().contains(getAxeTrade()));
    }

    @Override
    public void testCombat() {

    }

    @Test
    @Override
    public void weaknessAttackTest() {
        // Light is weak to Soul
        checkWeaknessAttack(getTargetSorcerer(),getLight(), getSoul());
        // Darkness is weak to Light
        checkWeaknessAttack(getTargetSorcerer(),getDarkness(), getLight());
        // Soul is weak to Darkness
        checkWeaknessAttack(getTargetSorcerer(),getSoul(), getDarkness());
    }

    @Test
    @Override
    public void resistantAttackTest() {
        // Light is resistant to Soul
        checkResistantAttack(getTargetSorcerer(),getLight(), getDarkness());
        // Darkness is resistant to Light
        checkResistantAttack(getTargetSorcerer(),getDarkness(), getSoul());
        // Soul is resistant to Darkness
        checkResistantAttack(getTargetSorcerer(),getSoul(), getLight());
    }

    @Test
    @Override
    public void sameTypeUnitAttackTest() {
        checkSameTypeUnitAttack(getTargetSorcerer(),darkness_p,getDarkness());
        checkSameTypeUnitAttack(getTargetSorcerer(),light_p,getLight());
        checkSameTypeUnitAttack(getTargetSorcerer(),soul_p,getSoul());
    }

    @Test
    @Override
    public void archerAttackTest() {
        checkArcherAttackToMagic(light_p);
        checkArcherAttackToMagic(darkness_p);
        checkArcherAttackToMagic(soul_p);
    }

    @Override
    public void sorcererAttackTest() {
    }

    @Test
    public void sorcererLightReceiveAttackTest(){
        getTestUnit().addItem(light_p);
        getTestUnit().equipItem(light_p);
        getTargetFighter().addItem(getAxe());
        getTargetFighter().equipItem(getAxe());
        getTargetFighter().attack(getTestUnit());
        assertEquals(20, getTestUnit().getCurrentHitPoints(),EPSILON);
        assertEquals(20,getTargetFighter().getCurrentHitPoints(),EPSILON);
    }

    @Test
    public void sorcererDarknessReceiveAttackTest(){
        getTestUnit().addItem(darkness_p);
        getTestUnit().equipItem(darkness_p);
        getTargetSwordMaster().addItem(getSword());
        getTargetSwordMaster().equipItem(getSword());
        getTargetSwordMaster().attack(getTestUnit());
        assertEquals(20, getTestUnit().getCurrentHitPoints(),EPSILON);
        assertEquals(20,getTargetSwordMaster().getCurrentHitPoints(),EPSILON);
    }

    @Test
    public void sorcererSoulReceiveAttackTest(){
        getTestUnit().addItem(soul_p);
        getTestUnit().equipItem(soul_p);
        getTargetHero().addItem(getSpear());
        getTargetHero().equipItem(getSpear());
        getTargetHero().attack(getTestUnit());
        assertEquals(20, getTestUnit().getCurrentHitPoints(),EPSILON);
        assertEquals(20,getTargetHero().getCurrentHitPoints(),EPSILON);
        getTestUnit().receiveRecovery(getStaff());
        assertEquals(50, getTestUnit().getCurrentHitPoints(),EPSILON);
    }

    @Test
    @Override
    public void clericAttackTest() {
        checkClericAttack(light);
        checkClericAttack(darkness);
        checkClericAttack(soul);
    }

    @Override
    public IEquipableItem getTestItem() {
        return light_p;
    }


}
