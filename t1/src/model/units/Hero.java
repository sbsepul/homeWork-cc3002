package model.units;

import model.items.IEquipableItem;
import model.items.Spear;
import model.map.Location;

/**
 * A <i>Hero</i> is a special kind of unit, the player that defeats this unit wins the game.
 * <p>
 * This unit <b>can only use spear weapons</b>.
 *
 * @author Ignacio Slater Muñoz
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
