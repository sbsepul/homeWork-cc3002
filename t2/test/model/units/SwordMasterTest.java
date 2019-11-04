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
import model.items.Sword;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;


/**
 * Test set for the SwordMaster unit
 *
 * @author Sebastian Sepulveda
 */
public class SwordMasterTest extends AbstractTestUnit {

  private SwordMaster swordMaster;
  private Sword sword1;
  private Sword sword2;
  //private Sword sword3;
  private Sword sword_p;

  /**
   * Set up the main unit that's going to be tested in the test set
   */
  @Override
  public void setTestUnit() {
    swordMaster = new SwordMaster(50, 2, field.getCell(0, 0));
    sword1 = new Sword("Sword1", 20,1,2);
    sword2 = new Sword("Sword2", 20,1,2);
    //sword3 = new Sword("Sword3", 20,1,2);
    sword_p = new Sword("Sword_private", 20,1,2);
  }

  /**
   * @return the current unit being tested
   */
  @Override
  public IUnit getTestUnit() {
    return swordMaster;
  }

  /**
   * Test that a SwordMaster equip a Sword correctly and can add items
   */
  @Test
  @Override
  public void equipSwordTest() {
    assertFalse(swordMaster.getEquippedItem().isUtil());
    swordMaster.equipItem(sword);
    assertFalse(swordMaster.getEquippedItem().isUtil());
    swordMaster.addItem(sword);
    swordMaster.equipItem(sword);
    assertEquals(sword, swordMaster.getEquippedItem());
    assertEquals(1, swordMaster.getItems().size());
    swordMaster.addItem(sword1);
    assertEquals(2 , swordMaster.getItems().size());
    swordMaster.equipItem(axe);
    assertEquals(sword, swordMaster.getEquippedItem());
    swordMaster.addItem(sword2);
    assertEquals(3 , swordMaster.getItems().size());
  }

  @Override
  public void giveToUnitSwordMasterTest() {
    assertFalse(getTestUnit().getEquippedItem().isUtil());
    assertEquals(0, getTestUnit().getItems().size());
    getTestUnit().giveItem(getTargetArcherTrade(), sword_p);
    assertEquals(2, getTargetArcherTrade().getItems().size());
    assertEquals(false, getTargetArcherTrade().getItems().contains(sword_p));
    getTestUnit().addItem(sword_p);
    assertFalse(getTestUnit().getEquippedItem().isUtil());
    assertEquals(1, getTestUnit().getItems().size());
    assertEquals(true, getTestUnit().getItems().contains(sword_p));
    getTestUnit().equipItem(sword_p);
    assertEquals(sword_p, getTestUnit().getEquippedItem());
    getTestUnit().giveItem(getTargetArcherTrade(),sword_p);
    assertEquals(0,getTestUnit().getItems().size());
    //verify that sword_p equipped isn't
    assertFalse(getTestUnit().getEquippedItem().isUtil());
    assertEquals(3,getTargetArcherTrade().getItems().size());
    assertEquals(true, getTargetArcherTrade().getItems().contains(sword_p));
    getTestUnit().addItem(getAxeTrade());
    assertEquals(true, getTestUnit().getItems().contains(getAxeTrade()));
    getTestUnit().giveItem(getTargetArcherTrade(),getAxeTrade());
    assertEquals(true, getTestUnit().getItems().contains(getAxeTrade()));
    assertEquals(false,getTargetArcherTrade().getItems().contains(getAxeTrade()));
  }

  @Override
  public void testCombat() { }

  @Test
  @Override
  public void weaknessAttackTest() {
    checkWeaknessAttack(getTargetHero(), getSword(), getSpear());
  }

  @Test
  @Override
  public void resistantAttackTest() {
    checkResistantAttack(getTargetFighter(), getSword(), getAxe());
  }

  @Test
  @Override
  public void sameTypeUnitAttackTest() {
    checkSameTypeUnitAttack(getTargetSwordMaster(), sword_p, getSword());
  }

  @Test
  @Override
  public void archerAttackTest() {
    checkArcherAttack(sword_p);
  }

  @Test
  @Override
  public void sorcererAttackTest() {
    checkSorcererAttack(sword_p);
  }

  @Test
  @Override
  public void clericAttackTest() {
    checkClericAttack(sword_p);
  }

  @Override
  public IEquipableItem getTestItem() {
    return sword_p;
  }

}