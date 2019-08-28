package model.units;

import model.items.IEquipableItem;
import model.map.Location;

/**
 * This class represents an <i>Alpaca</i> type unit.
 * <p>
 * This are a special kind of unit that can carry an unlimited amount of items but can't use any of
 * them.
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class Alpaca extends AbstractUnit {

  /**
   * Creates a new Alpaca.
   *
   * @param hitPoints
   *     the amount of damage this unit can receive
   * @param movement
   *     number of cells the unit can move
   * @param location
   *     current position of the unit
   */
  public Alpaca(final int hitPoints, final int movement, final Location location,
      final IEquipableItem... items) {
    super(hitPoints, movement, location, Integer.MAX_VALUE, items);
  }

  /**
   * {@inheritDoc}
   * <p>
   * The <i>Alpaca</i> cannot equip any item. (ver como hacer eso de que no puede equipar pero si llevar)
   */
  public void equipItem(final IEquipableItem item) {
    // Method body intentionally left empty
  }

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
