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

package model.items;

import model.items.magic.Darkness;
import model.items.magic.Light;
import model.items.magic.Soul;
import model.units.IUnit;

/**
 * This interface represents the <i>weapons</i> that the units of the game can use.
 * <p>
 * The signature for all the common methods of the weapons are defined here. Every weapon have a
 * base damage and is strong or weak against other type of weapons.<p/>
 *
 * @author Sebastian Sepulveda
 * @since 1.0
 */
public interface IEquipableItem {

  /**
   * Equips this item to a unit.
   *
   * @param unit
   *     the unit that will be quipped with the item
   */
  public void equipTo(IUnit unit);

  /**
   * @return true if the item is in range of attack, false otherwise
   */
  boolean inRangeItem();

  /**
   * @return the unit that has currently equipped this item
   */
  public IUnit getOwner();

  /**
   * Set the owner of the army
   * @param unit
   */
  public void setOwner(IUnit unit);

  /**
   * @return the name of the item
   */
  public String getName();

  /**
   * @return the power of the item
   */
  public int getPower();

  /**
   * @return the minimum range of the item
   */
  public int getMinRange();

  /**
   * @return the maximum range of the item
   */
  public int getMaxRange();

  /**
   *
   * @param itemEnemy is the unitEnemy's item
   * @return true if the unit can attack to other
   */
  public boolean canAttack(IEquipableItem itemEnemy);

  /* BEGIN COMBAT SECTION */

  /**
   * @param attackBow is the type of damage received for a Bow
   */
  void receiveBowAttack(Bow attackBow);

  /**
   * @param attackAxe is the type of damage received for a Axe
   */
  void receiveAxeAttack(Axe attackAxe);

  /**
   *
   * @param attackSword is the type of damage received for a Sword
   */
  void receiveSwordsAttack(Sword attackSword);

  /**
   * @param attackSpears is the type of damage received for a Spear
   */
  void receiveSpearsAttack(Spear attackSpears);

  /**
   * Receives an attack to which this Unit is weak.
   *
   * @param attack
   *     Received attack.
   */
  void receiveUnitWeaknessAttack(IEquipableItem attack);

  /**
   * Receives an attack to which this Unit is resistant.
   *
   * @param attack
   *     Received attack.
   */
  void receiveUnitResistantAttack(IEquipableItem attack);

  /**
   * A item can receive a Attack without damage additional
   * @param attack
   */
  void receiveUnitAttack(IEquipableItem attack);

  /**
   * @param attackDarkness is the type of damage received for a Darkness
   */
  void receiveDarknessAttack(Darkness attackDarkness);

  /**
   * @param attackLight is the type of damage received for a Light
   */
  void receiveLightAttack(Light attackLight);

  /**
   * @param attackSoul is the type of damage received for a Soul
   */
  void receiveSoulAttack(Soul attackSoul);

  /**
   * Attack special to items magics
   * if the item is not magic, does not apply
   *
   * @param enemyAttack is the enemy's item
   */
  void giveMagicAttack(IEquipableItem enemyAttack);

  /**
   * @return the distance to the target unit equipped
   */
  int getDistance();

  /**
   * @param distance new with the target unit equipped
   */
  void setDistance(int distance);
  /* END COMBAT SECTION */

  boolean isUtil();
}
