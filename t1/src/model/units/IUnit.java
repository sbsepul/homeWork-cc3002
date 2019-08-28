package model.units;

import java.util.List;
import model.items.IEquipableItem;
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

  /**
   *
   * @param item
   *     other item to equip (*****)
   */
  void equipItem(IEquipableItem item);
  /**
   *
   * @param item
   *     the item to equip
   */
  void equipItemBow(IEquipableItem item);

  /**
   *
   * @param item
   *     the item to equip
   */
  void equipItemAxes(IEquipableItem item);
  /**
   *
   * @param item
   *     the item to equip
   */
  void equipItemSword(IEquipableItem item);
  /**
   *
   * @param item
   *     the item to equip
   */
  void equipItemStaffs(IEquipableItem item);
  /**
   *
   * @param item
   *     the item to equip
   */
  void equipItemSpears(IEquipableItem item);

  /**
   * @return hit points of the unit
   */
  int getCurrentHitPoints();


    /**
     * @param remove
     * the hit points to be remove
     */
  void setRemoveHitPoints(int remove);

  /**
   * @return the items carried by this unit
   */
  List<IEquipableItem> getItems();

  /**
   * @return the currently equipped item
   */
  IEquipableItem getEquippedItem();

  /**
   * @param item
   *     the item to be equipped
   */
  //void setEquippedItem(IEquipableItem item);

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
}

