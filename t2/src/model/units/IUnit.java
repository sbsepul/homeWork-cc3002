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

import java.beans.PropertyChangeListener;
import java.util.List;

import model.items.*;
import model.items.Axe;
import model.items.Bow;
import model.items.Spear;
import model.items.Staff;
import model.items.Sword;
import model.items.magic.Darkness;
import model.items.magic.Light;
import model.items.magic.Soul;
import model.map.Location;

/**
 * This interface represents all units in the game.
 * <p>
 * The signature of all the common methods that a unit can execute are defined here. All units
 * except some special ones can carry at most 3 weapons.
 *
 * @author Sebastian Sepulveda
 * @since 1.0
 */
public interface IUnit {

  /* BEGIN ITEM SECTION */

  /**
   *
   * @param item
   * to equip a unit with a item
   */
  void equipItem(IEquipableItem item);


  /**
   *
   * @param item
   * to equip a Darkness to Sorcerer, item magic
   */
  void equipItemDarkness(Darkness item);

  /**
   *
   * @param item
   * to equip a Light to Sorcerer, item magic
   */
  void equipItemLight(Light item);

  /**
   *
   * @param item
   * to equip a Soul to Sorcerer, item magic
   */
  void equipItemSoul(Soul item);

  /**
   * @param item
   * to equip a Bow to Archer
   */
  void equipItemBow(Bow item);

  /**
   *
   * @param item
   * to equip Axe to Fighter
   */
  void equipItemAxe(Axe item);
  /**
   *
   * @param item
   * to equip Sword to SwordMaster
   */
  void equipItemSword(Sword item);
  /**
   *
   * @param item
   * to equip Staff to Cleric
   */
  void equipItemStaff(Staff item);
  /**
   *
   * @param item
   * to equip Spear to Hero
   */
  void equipItemSpear(Spear item);

  /**
   * @return the items carried by this unit
   */
  List<IEquipableItem> getItems();

  /**
   * @return the currently equipped item
   */
  IEquipableItem getEquippedItem();

  /**
   * @return true if the inventory of items is full, false otherwise.
   */
  boolean isItemFull();

  /**
   * add a item to the unit
   * @param item
   */
  void addItem(IEquipableItem item);


  /**
   * get a item of the inventory
   * @param item
   * @return
   */
  void removeItem(IEquipableItem item);

  /* END ITEM SECTION */

  /* BEGIN LOCATION SECTION */
  /**
   * @return the current location of the unit
   */
  Location getLocation();

  /**
   * Sets a new location for this unit,
   */
  void setLocation(final Location location);

  /**
   * @return the number of cells this unit can move
   */
  int getMovement();

  /**
   * Moves this unit to another location.
   * <p>
   * If the other location is out of this unit's movement range, the unit doesn't move.
   */
  void moveTo(Location targetLocation);

  /**
   * Add a Listener that review a change in
   * @param plc
   */
  void addObserverMovement(PropertyChangeListener plc);

  /* END LOCATION SECTION */

  /* BEGIN HIT POINTS SECTION */
  /**
   * @return hit points of the unit
   */
  double getCurrentHitPoints();

  /**
   * @return maximum hit points of the unit
   */
  double getMaxCurrentHitPoints();

  /* END HIT POINTS SECTION */

  /* BEGIN COMBAT SECTION */

  /**
   * Is necessary for that a unit attack on other
   * @param unit is the enemy
   */
  void attack(IUnit unit);

  /**
   * A unit receive a Normal Attack, without modify damage
   * @param attack normal that receive this unit
   */
  void receiveAttack(IEquipableItem attack);

  /**
   * A unit receive a Attack that seriously affect it
   * @param attack's damage increase x1.5
   */
  void receiveAttackWeakness(IEquipableItem attack);

  /**
   * A unit receive a Attack that not affect it too much.
   * @param attack's damage reduce 20 points
   */
  void receiveAttackResistant(IEquipableItem attack);

  /**
   * A unit receive a Attack that recovers it
   * @param attack's damage increase the unit's hp that is attacked
   */
  void receiveRecovery(IEquipableItem attack);

  /**
   * Determine if the unit enemy is in the range of army's unit that attack
   * @param unit is the enemy
   * @return true if the enemy is between MinRange and MaxRange, false otherwise
   */
  boolean isInRange(IUnit unit);

  /* END COMBAT SECTION */

  /**
   * Give a item to other
   * @param unit who receive the item
   * @param item what is given
   */
  void giveItem(IUnit unit, IEquipableItem item);

  /**
   * set a item equipped for any other
   * @param item new that will be equipped
   * @return
   */
  int setEquippedItem(IEquipableItem item);

  /**
   * change the item equipped in the unit if this have,
   * for other item in the inventory
   * @param item new that will be equipped
   */
  void changeEquippedItem(IEquipableItem item);

  /**
   * A combat init when
   *      the two units is life
   *      this unit have a item equipped
   *      the enemy is in the equippedItem's range
   * @param unitEnemy that will be attacked
   * @return true if this unit can attack, false otherwise
   */
  boolean initCombat(IUnit unitEnemy);

  /**
   * Verify if a unit can exchange a item to other unit
   *
   * @param unit that receive the item
   * @param item that will be exchange
   * @return true if the two units can exchange a item
   */
  boolean canExchange(IUnit unit, IEquipableItem item);


  boolean canCounterAttack(int distance);
}

