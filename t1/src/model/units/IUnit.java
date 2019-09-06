package model.units;

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
   * @param item
   * Anything item that isn't in the list
   */
  void equipItemOther(IEquipableItem item);

  /**
   *
   * @param item
   * to equip a Darkness, item magic
   */
  void equipItemDarkness(Darkness item);

  /**
   *
   * @param item
   * to equip a Light, item magic
   */
  void equipItemLight(Light item);

  /**
   *
   * @param item
   * to equip a Soul, item magic
   */
  void equipItemSoul(Soul item);

  /**
   * @param item the item to equip
   */
  void equipItemBow(Bow item);

  /**
   *
   * @param item
   * to equip Axe
   */
  void equipItemAxe(Axe item);
  /**
   *
   * @param item
   * to equip Sword
   */
  void equipItemSword(Sword item);
  /**
   *
   * @param item
   * to equip Staff
   */
  void equipItemStaff(Staff item);
  /**
   *
   * @param item
   * to equip Spear
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
   *
   * @return
   */
  boolean isItemFull();

  /**
   * add a item to the unit
   * @param item
   */
  void addItem(IEquipableItem item);


  /**
   * get a item of the inventory
   * @param index
   * @return
   */
  IEquipableItem removeItem(IEquipableItem item);

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

  /* END LOCATION SECTION */

  /* BEGIN HIT POINTS SECTION */
  /**
   * @return hit points of the unit
   */
  int getCurrentHitPoints();

  /**
   * @param change
   * the hit points to be remove
   */
  void setCurrentHitPoints(int change);

  /* END HIT POINTS SECTION */

  /* BEGIN COMBAT SECTION */

  /**
   * A unit can receive a Attack normal
   * @param attack
   */
  void receiveAttack(IEquipableItem attack);

  /**
   * A unit receive a Attack that seriously affect it
   * @param attack
   */
  void receiveAttackWeakness(IEquipableItem attack);

  /**
   * A unit receive a Attack that not affect it too much.
   * @param attack
   */
  void receiveAttackResistant(IEquipableItem attack);

  /**
   * A unit receive a Attack that recovers it
   * @param attack
   */
  void receiveRecovery(IEquipableItem attack);

  /**
   * Determine if the unit enemy is in the range of army's unit that attack
   * @param unit
   * @return
   */
  boolean isInRange(IUnit unit);

  /* END COMBAT SECTION */

  /**
   * Give a item to other
   * @param unit
   * @param item
   */
  void giveItem(IUnit unit, IEquipableItem item);

  /**
   * Receive a item to other
   * @param unit
   * @param item
   */
  void receiveItem(IUnit unit, IEquipableItem item);
}

