package model.units;

import model.items.Axe;
import model.items.IEquipableItem;
import model.map.Location;

/**
 * This class represents a fighter type unit.
 * A fighter is a unit that can only use axe type weapons.
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class Fighter extends AbstractUnit {

  public Fighter(final int hitPoints, final int movement, final Location location,
      IEquipableItem... items) {
    super(hitPoints, movement, location, 3, items);
  }

  /**
   * Sets the currently equipped item of this unit.
   *
   * @param item
   *     the item to equip
   */

  @Override
  public void equipItemBow(IEquipableItem item) {

  }

  @Override
  public void equipItemAxes(IEquipableItem item) {

  }

  @Override
  public void equipItemSword(IEquipableItem item) {

  }

  @Override
  public void equipItemStaffs(IEquipableItem item) {

  }

  @Override
  public void equipItemSpears(IEquipableItem item) {

  }

  @Override
  public void equipItemOther(IEquipableItem item) {

  }

  @Override
  public IEquipableItem getEquippedItem() {
    return null;
  }
}
