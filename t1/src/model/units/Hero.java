package model.units;

import model.items.*;
import model.items.axe.Axe;
import model.items.bow.Bow;
import model.items.spears.Spear;
import model.items.staff.Staff;
import model.items.sword.Sword;
import model.map.Location;

/**
 * A <i>Hero</i> is a special kind of unit, the player that defeats this unit wins the game.
 * <p>
 * This unit <b>can only use spear weapons</b>.
 *
 * @author Sebastian Sepulveda
 * @since 1.0
 */
public class Hero extends AbstractUnit {
  /*We need a name, hit points */

  /**
   * Creates a new Unit.
   *
   * @param hitPoints
   *     the maximum amount of damage a unit can sustain
   * @param movement
   *     the number of panels a unit can move
   */
  public Hero(final int hitPoints, final int movement, final Location location,
      IEquipableItem... items) {
    super(hitPoints, movement, location, 3, items);
  }

  @Override
  public void equipItemSpear(Spear item) {
    equippedItem = item;
  }

  @Override
  public IEquipableItem getEquippedItem() {
    return equippedItem;
  }

  @Override
  public void equipItemOther(IEquipableItem item) { }

  @Override
  public void equipItemBow(Bow item) { }

  @Override
  public void equipItemAxe(Axe item) { }

  @Override
  public void equipItemSword(Sword item) { }

  @Override
  public void equipItemStaff(Staff item) { }
}
