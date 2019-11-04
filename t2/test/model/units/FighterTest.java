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

import model.items.Axe;
import model.items.IEquipableItem;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * Test set for the Fighter unit
 *
 * @author Sebastian Sepulveda
 */
public class FighterTest extends AbstractTestUnit {

  private Fighter fighter;
  private Axe axe_p;

  /**
   * Set up the main unit that's going to be tested in the test set
   */
  @Override
  public void setTestUnit() {
    fighter = new Fighter(50, 2, field.getCell(0, 0));
    axe_p = new Axe("Axe_private",20,1,2);
  }

  /**
   * @return the current unit being tested
   */
  @Override
  public IUnit getTestUnit() {
    return fighter;
  }

  /**
   * Checks if the axe is equipped correctly to the unit
   */
  @Test
  @Override
  public void equipAxeTest() {
    assertFalse(fighter.getEquippedItem().isUtil());
    fighter.equipItem(axe);
    assertFalse(fighter.getEquippedItem().isUtil());
    fighter.addItem(axe);
    fighter.equipItem(axe);
    assertEquals(axe, fighter.getEquippedItem());
    assertEquals(1, fighter.getItems().size());
    fighter.addItem(staff);
    assertEquals(2 , fighter.getItems().size());
    fighter.equipItem(spear);
    assertEquals(axe, fighter.getEquippedItem());
    fighter.addItem(sword);
    assertEquals(3 , fighter.getItems().size());
  }

  @Test
  @Override
  public void giveToUnitFighterTest() {
    assertFalse(getTestUnit().getEquippedItem().isUtil());
    assertEquals(0, getTestUnit().getItems().size());
    getTestUnit().giveItem(getTargetClericTrade(), axe_p);
    assertEquals(2, getTargetClericTrade().getItems().size());
    assertEquals(false, getTargetClericTrade().getItems().contains(axe_p));
    getTestUnit().addItem(axe_p);
    assertFalse(getTestUnit().getEquippedItem().isUtil());
    assertEquals(1, getTestUnit().getItems().size());
    assertEquals(true, getTestUnit().getItems().contains(axe_p));
    getTestUnit().equipItem(axe_p);
    assertEquals(axe_p, getTestUnit().getEquippedItem());
    getTestUnit().giveItem(getTargetClericTrade(),axe_p);
    assertEquals(0,getTestUnit().getItems().size());
    //verify that axe_p equipped isn't
    assertFalse(getTestUnit().getEquippedItem().isUtil());
    assertEquals(3,getTargetClericTrade().getItems().size());
    assertEquals(true, getTargetClericTrade().getItems().contains(axe_p));
    getTestUnit().addItem(getAxeTrade());
    assertEquals(true, getTestUnit().getItems().contains(getAxeTrade()));
    getTestUnit().giveItem(getTargetClericTrade(),getAxeTrade());
    assertEquals(true, getTestUnit().getItems().contains(getAxeTrade()));
    assertEquals(false,getTargetClericTrade().getItems().contains(getAxeTrade()));
  }

  @Override
  public void testCombat() {

  }

  @Test
  @Override
  public void weaknessAttackTest() {
    checkWeaknessAttack(getTargetSwordMaster(),getAxe(),getSword());
  }

  @Test
  @Override
  public void resistantAttackTest() {
    checkResistantAttack(getTargetHero(), getAxe(), getSpear());
  }

  @Test
  @Override
  public void sameTypeUnitAttackTest() {
    checkSameTypeUnitAttack(getTargetFighter(),axe_p, getAxe());
  }

  @Test
  @Override
  public void archerAttackTest() {
    checkArcherAttack(axe_p);
  }

  @Test
  @Override
  public void sorcererAttackTest() {
    checkSorcererAttack(axe_p);
  }

  @Test
  @Override
  public void clericAttackTest() {
    checkClericAttack(axe_p);
  }

  @Override
  public IEquipableItem getTestItem() {
    return axe_p;
  }
}