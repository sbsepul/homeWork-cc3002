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
import model.items.Spear;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * Test set for the Hero unit
 *
 * @author Sebastian Sepulveda
 */
public class HeroTest extends AbstractTestUnit {

  private Hero hero;
  private Spear spear_p;

  /**
   * Set up the main unit that's going to be tested in the test set
   */
  @Override
  public void setTestUnit() {
    hero = new Hero(50, 2, field.getCell(0, 0));
    spear_p = new Spear("Spear_private", 20, 1,2);
  }

  /**
   * @return the current unit being tested
   */
  @Override
  public IUnit getTestUnit() {
    return hero;
  }

  @Test
  @Override
  public void equipSpearTest() {
    assertFalse(hero.getEquippedItem().isUtil());
    hero.equipItem(spear);
    assertFalse(hero.getEquippedItem().isUtil());
    hero.addItem(spear);
    hero.equipItem(spear);
    assertEquals(spear, hero.getEquippedItem());
    assertEquals(1, hero.getItems().size());
    hero.addItem(sword);
    assertEquals(2 , hero.getItems().size());
    hero.equipItem(axe);
    assertEquals(spear, hero.getEquippedItem());
    hero.addItem(staff);
    assertEquals(3 , hero.getItems().size());
  }

  @Test
  @Override
  public void giveToUnitHeroTest() {
    assertFalse(getTestUnit().getEquippedItem().isUtil());
    assertEquals(0, getTestUnit().getItems().size());
    getTestUnit().giveItem(getTargetArcherTrade(), spear_p);
    assertEquals(2, getTargetArcherTrade().getItems().size());
    assertEquals(false, getTargetArcherTrade().getItems().contains(spear_p));
    getTestUnit().addItem(spear_p);
    assertFalse(getTestUnit().getEquippedItem().isUtil());
    assertEquals(1, getTestUnit().getItems().size());
    assertEquals(true, getTestUnit().getItems().contains(spear_p));
    getTestUnit().equipItem(spear_p);
    assertEquals(spear_p, getTestUnit().getEquippedItem());
    getTestUnit().giveItem(getTargetArcherTrade(),spear_p);
    assertEquals(0,getTestUnit().getItems().size());
    //verify that spear_p equipped isn't
    assertFalse(getTestUnit().getEquippedItem().isUtil());
    assertEquals(3,getTargetArcherTrade().getItems().size());
    assertEquals(true, getTargetArcherTrade().getItems().contains(spear_p));
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
    checkWeaknessAttack(getTargetFighter(),getSpear(),getAxe());
  }

  @Test
  @Override
  public void resistantAttackTest() {
    checkResistantAttack(getTargetSwordMaster(),getSpear(),getSword());
  }

  @Test
  @Override
  public void sameTypeUnitAttackTest() {
    checkSameTypeUnitAttack(getTargetHero(), spear_p, getSpear());
  }

  @Test
  @Override
  public void archerAttackTest() {
    checkArcherAttack(spear_p);
  }

  @Test
  @Override
  public void sorcererAttackTest() {
    checkSorcererAttack(spear_p);
  }

  @Test
  @Override
  public void clericAttackTest() {
    checkClericAttack(spear_p);
  }
  @Override
  public IEquipableItem getTestItem() {
    return spear_p;
  }

}