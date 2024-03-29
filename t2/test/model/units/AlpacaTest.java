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
import model.items.ItemNull;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * Test set for the Alpaca unit
 *
 * @author Sebastian Sepulveda
 * @since 1.0
 */
public class AlpacaTest extends AbstractTestUnit {

  private Alpaca alpaca;

  @Override
  public void setTestUnit() {
    alpaca = new Alpaca(50, 2, field.getCell(0, 0));
  }

  @Override
  public Alpaca getTestUnit() {
    return alpaca;
  }

  @Test
  @Override
  public void testCombat() {
    assertEquals(50, getTargetFighter().getCurrentHitPoints(),EPSILON);
    assertEquals(50, alpaca.getCurrentHitPoints(),EPSILON);
    getTargetFighter().addItem(axe);
    getTargetFighter().equipItem(axe);
    assertEquals(axe,getTargetFighter().getEquippedItem());
    alpaca.addItem(sword);
    assertEquals(1, alpaca.getItems().size());
    alpaca.equipItem(sword);
    assertFalse(alpaca.getEquippedItem().isUtil());
    getTargetFighter().attack(alpaca);
    assertEquals(30, alpaca.getCurrentHitPoints(),EPSILON);
    assertEquals(50,getTargetFighter().getCurrentHitPoints(),EPSILON);
    alpaca.attack(getTargetFighter());
    assertEquals(50,getTargetFighter().getCurrentHitPoints(),EPSILON);
  }

  @Override
  public void weaknessAttackTest() { }

  @Override
  public void resistantAttackTest() { }

  @Override
  public void sameTypeUnitAttackTest() { }

  @Override
  public void archerAttackTest() { }

  @Test
  @Override
  public void sorcererAttackTest() {
    //hp normal
    assertEquals(50, getTestUnit().getCurrentHitPoints(),EPSILON);
    assertEquals(50, getTargetCleric().getCurrentHitPoints(),EPSILON);
    //test unit with inventory
    alpaca.addItem(getAxe());
    alpaca.equipItem(getAxe());
    assertFalse(alpaca.getEquippedItem().isUtil());
    getTargetSorcerer().addItem(getLight());
    getTargetSorcerer().equipItem(getLight());
    assertEquals(getLight(),getTargetSorcerer().getEquippedItem());
    getTargetSorcerer().attack(alpaca);
    assertEquals(30, alpaca.getCurrentHitPoints(),EPSILON);
    assertEquals(50, getTargetSorcerer().getCurrentHitPoints(),EPSILON);
  }

  @Override
  public void clericAttackTest() { }

  @Override
  public IEquipableItem getTestItem() {
    return new ItemNull();
  }

  @Test
  public void equipItemBow(){
    getTestUnit().addItem(getBow());
    assertEquals(1,getTestUnit().getItems().size());
    assertFalse(getTestUnit().getEquippedItem().isUtil());
    getTestUnit().equipItemBow(getBow());
    assertFalse(getTestUnit().getEquippedItem().isUtil());
  }
  @Test
  public void equipItemAxe(){
    getTestUnit().addItem(getAxe());
    assertEquals(1,getTestUnit().getItems().size());
    assertFalse(getTestUnit().getEquippedItem().isUtil());
    getTestUnit().equipItemAxe(getAxe());
    assertFalse(getTestUnit().getEquippedItem().isUtil());
  }
  @Test
  public void equipItemLight(){
    getTestUnit().addItem(getLight());
    assertEquals(1,getTestUnit().getItems().size());
    assertFalse(getTestUnit().getEquippedItem().isUtil());
    getTestUnit().equipItemLight(getLight());
    assertFalse(getTestUnit().getEquippedItem().isUtil());
  }
  @Test
  public void equipItemDarkness(){
    getTestUnit().addItem(getDarkness());
    assertEquals(1,getTestUnit().getItems().size());
    assertFalse(getTestUnit().getEquippedItem().isUtil());
    getTestUnit().equipItemDarkness(getDarkness());
    assertFalse(getTestUnit().getEquippedItem().isUtil());
  }
  @Test
  public void equipItemSoul(){
    getTestUnit().addItem(getSoul());
    assertEquals(1,getTestUnit().getItems().size());
    assertFalse(getTestUnit().getEquippedItem().isUtil());
    getTestUnit().equipItemSoul(getSoul());
    assertFalse(getTestUnit().getEquippedItem().isUtil());
  }
  @Test
  public void equipItemSword(){
    getTestUnit().addItem(getSword());
    assertEquals(1,getTestUnit().getItems().size());
    assertFalse(getTestUnit().getEquippedItem().isUtil());
    getTestUnit().equipItemSword(getSword());
    assertFalse(getTestUnit().getEquippedItem().isUtil());
  }

  @Test
  public void equipItemStaff(){
    getTestUnit().addItem(getStaff());
    assertEquals(1,getTestUnit().getItems().size());
    assertFalse(getTestUnit().getEquippedItem().isUtil());
    getTestUnit().equipItemStaff(getStaff());
    assertFalse(getTestUnit().getEquippedItem().isUtil());
  }

  @Test
  public void equipItemSpear(){
    getTestUnit().addItem(getSpear());
    assertEquals(1,getTestUnit().getItems().size());
    assertFalse(getTestUnit().getEquippedItem().isUtil());
    getTestUnit().equipItemSpear(getSpear());
    assertFalse(getTestUnit().getEquippedItem().isUtil());
  }

  @Test
  @Override
  public void alpacaReceiveAttack() {
    assertEquals(50, getTestUnit().getCurrentHitPoints(),EPSILON);
    assertEquals(50, getTargetAlpaca().getCurrentHitPoints(),EPSILON);
    getTestUnit().attack(getTargetAlpaca());
    assertEquals(50, getTestUnit().getCurrentHitPoints(),EPSILON);
    assertEquals(50, getTargetAlpaca().getCurrentHitPoints(),EPSILON);
  }
}