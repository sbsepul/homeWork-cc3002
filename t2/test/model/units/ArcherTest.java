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

import model.items.Bow;
import model.items.IEquipableItem;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * Test set for the Archer unit.
 *
 * @author Sebastian Sepulveda
 * @since 1.0
 */
public class ArcherTest extends AbstractTestUnit {

  private Archer archer;
  private Bow bow_p;

  /**
   * Set up the main unit that's going to be tested in the test set
   */
  @Override
  public void setTestUnit() {
    archer = new Archer(50, 2, field.getCell(0, 0));
    bow_p = new Bow("Bow_private", 20,2,3);
  }

  /**
   * @return the current unit being tested
   */
  @Override
  public IUnit getTestUnit() {
    return archer;
  }

  /**
   * Checks if the bow is equipped correctly to the unit
   * Besides, verify that a item added isn't equipped
   *
   */
  @Test
  @Override
  public void equipBowTest() {
    assertFalse(archer.getEquippedItem().isUtil());
    archer.equipItem(bow);
    assertFalse(archer.getEquippedItem().isUtil());
    archer.addItem(bow);
    archer.equipItem(bow);
    assertEquals(bow, archer.getEquippedItem());
    assertEquals(1, archer.getItems().size());
    archer.addItem(bow_p);
    assertEquals(2 , archer.getItems().size());
    archer.equipItem(sword);
    assertEquals(bow, archer.getEquippedItem());
    archer.addItem(sword);
    assertEquals(3 , archer.getItems().size());
    archer.addItem(sword);
    assertEquals(3 , archer.getItems().size());
  }

  @Test
  @Override
  public void giveToUnitArcherTest() {
    assertFalse(archer.getEquippedItem().isUtil());
    assertEquals(0, archer.getItems().size());
    archer.giveItem(getTargetArcherTrade(), bow_p);
    assertEquals(2, getTargetArcherTrade().getItems().size());
    assertEquals(false, getTargetArcherTrade().getItems().contains(bow_p));
    archer.addItem(bow_p);
    assertFalse(archer.getEquippedItem().isUtil());
    assertEquals(1, archer.getItems().size());
    assertEquals(true, archer.getItems().contains(bow_p));
    archer.equipItem(bow_p);
    assertEquals(bow_p, archer.getEquippedItem());
    archer.giveItem(getTargetArcherTrade(),bow_p);
    assertEquals(0,archer.getItems().size());
    //verify that bow_p equipped isn't
    assertFalse(archer.getEquippedItem().isUtil());
    assertEquals(3,getTargetArcherTrade().getItems().size());
    assertEquals(true, getTargetArcherTrade().getItems().contains(bow_p));
    archer.addItem(getAxeTrade());
    assertEquals(true, archer.getItems().contains(getAxeTrade()));
    archer.giveItem(getTargetArcherTrade(),getAxeTrade());
    assertEquals(true, archer.getItems().contains(getAxeTrade()));
    assertEquals(false,getTargetArcherTrade().getItems().contains(getAxeTrade()));
  }

  /**
   * This test use a archer, a bow, a axe
   * @Test Archer's equipped item, the attack, and the currentPoints
   */
  @Test
  @Override
  public void testCombat() {
    getTestUnit().addItem(bow_p);
    getTestUnit().equipItem(bow_p);
    getTargetFighter().addItem(getAxe());
    getTargetFighter().equipItem(getAxe());
    getTargetHero().addItem(getSpear());
    getTargetHero().equipItem(getSpear());
    getTargetSwordMaster().addItem(getSword());
    getTargetSwordMaster().equipItem(getSword());
    //combat Fighter
    getTargetFighter().attack(getTestUnit());
    assertEquals(50, getTargetFighter().getCurrentHitPoints(),EPSILON);
    assertEquals(30,getTestUnit().getCurrentHitPoints(),EPSILON);
    getTargetArcher().addItem(getBow());
    getTargetArcher().equipItem(getBow());
    getTargetFighter().attack(getTargetArcher());
    assertEquals(30, getTargetFighter().getCurrentHitPoints(),EPSILON);
    assertEquals(30,getTargetArcher().getCurrentHitPoints(),EPSILON);
    getTestUnit().receiveRecovery(getStaff_normal());
    //combat SwordMaster
    getTargetSwordMaster().attack(getTestUnit());
    assertEquals(50, getTargetSwordMaster().getCurrentHitPoints(),EPSILON);
    assertEquals(30,getTestUnit().getCurrentHitPoints(),EPSILON);
    getTargetSwordMaster().attack(getTargetArcher());
    assertEquals(30, getTargetSwordMaster().getCurrentHitPoints(),EPSILON);
    assertEquals(10,getTargetArcher().getCurrentHitPoints(),EPSILON);
    //combat Hero
    getTargetArcher().receiveRecovery(getStaff_normal());
    getTargetHero().attack(getTestUnit());
    assertEquals(50,getTargetHero().getCurrentHitPoints(),EPSILON);
    assertEquals(10,getTestUnit().getCurrentHitPoints(),EPSILON);
    getTargetHero().attack(getTargetArcher());
    assertEquals(30, getTargetHero().getCurrentHitPoints(),EPSILON);
    assertEquals(10,getTargetArcher().getCurrentHitPoints(),EPSILON);

  }

  @Override
  public void weaknessAttackTest() {

  }

  @Override
  public void resistantAttackTest() { }

  @Test
  @Override
  public void sameTypeUnitAttackTest() {
    checkSameTypeUnitAttack(getTargetArcher(),bow_p,getBow());
  }

  @Override
  public void archerAttackTest() { }

  @Test
  @Override
  public void sorcererAttackTest() {
    //hp normal
    assertEquals(50, archer.getCurrentHitPoints(),EPSILON);
    assertEquals(50, getTargetCleric().getCurrentHitPoints(),EPSILON);
    //test unit with inventory
    archer.addItem(bow_p);
    archer.equipItem(bow_p);
    getTargetSorcerer().addItem(getLight());
    getTargetSorcerer().addItem(getDarkness());
    getTargetSorcerer().addItem(getSoul());
    getTargetSorcerer().equipItem(getLight());
    assertEquals(bow_p, archer.getEquippedItem());
    assertEquals(getLight(),getTargetSorcerer().getEquippedItem());
    //combat with light
    getTargetSorcerer().attack(archer);
    assertEquals(20, archer.getCurrentHitPoints(),EPSILON);
    assertEquals(50, getTargetSorcerer().getCurrentHitPoints(),EPSILON);
    getTargetArcher().addItem(getBow());
    getTargetArcher().equipItem(getBow());
    getTargetSorcerer().attack(getTargetArcher());
    assertEquals(20,getTargetArcher().getCurrentHitPoints(),EPSILON);
    assertEquals(20, getTargetSorcerer().getCurrentHitPoints(),EPSILON);
    archer.receiveRecovery(getStaff());
    getTargetSorcerer().receiveRecovery(getStaff());
    getTargetArcher().receiveRecovery(getStaff());
    assertEquals(50, archer.getCurrentHitPoints(),EPSILON);
    assertEquals(50, getTargetSorcerer().getCurrentHitPoints(),EPSILON);
    assertEquals(50, getTargetArcher().getCurrentHitPoints(),EPSILON);
    //combat with darkness
    getTargetSorcerer().changeEquippedItem(getDarkness());
    getTargetSorcerer().attack(archer);
    assertEquals(20, archer.getCurrentHitPoints(),EPSILON);
    assertEquals(50, getTargetSorcerer().getCurrentHitPoints(),EPSILON);
    // with target archer
    getTargetSorcerer().attack(getTargetArcher());
    assertEquals(20,getTargetArcher().getCurrentHitPoints(),EPSILON);
    assertEquals(20, getTargetSorcerer().getCurrentHitPoints(),EPSILON);
    archer.receiveRecovery(getStaff());
    getTargetSorcerer().receiveRecovery(getStaff());
    getTargetArcher().receiveRecovery(getStaff());
    assertEquals(50, archer.getCurrentHitPoints(),EPSILON);
    assertEquals(50, getTargetSorcerer().getCurrentHitPoints(),EPSILON);
    assertEquals(50, getTargetArcher().getCurrentHitPoints(),EPSILON);
    //combat with soul
    getTargetSorcerer().changeEquippedItem(getSoul());
    getTargetSorcerer().attack(archer);
    assertEquals(20, archer.getCurrentHitPoints(),EPSILON);
    assertEquals(50, getTargetSorcerer().getCurrentHitPoints(),EPSILON);
    //combat archer counterAttack to sorcerer
    assertEquals(2,getTargetArcher().getLocation().distanceTo(getTargetSorcerer().getLocation()));
    getTargetSorcerer().attack(getTargetArcher());
    assertEquals(20,getTargetSorcerer().getCurrentHitPoints(),EPSILON);
    assertEquals(20,getTargetArcher().getCurrentHitPoints(),EPSILON);
    getTargetSorcerer().receiveRecovery(getStaff());
    getTargetArcher().receiveRecovery(getStaff());
    assertEquals(50, getTargetSorcerer().getCurrentHitPoints(),EPSILON);
    assertEquals(50, getTargetArcher().getCurrentHitPoints(),EPSILON);
    getTargetArcher().attack(getTargetSorcerer());
    assertEquals(20,getTargetSorcerer().getCurrentHitPoints(),EPSILON);
    assertEquals(20,getTargetArcher().getCurrentHitPoints(),EPSILON);

  }

  @Test
  @Override
  public void clericAttackTest() {
    checkClericAttack(bow_p);
  }

  @Override
  public IEquipableItem getTestItem() {
    return bow_p;
  }

}