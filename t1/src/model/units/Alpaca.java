package model.units;

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
  }

  @Override
  protected void attack(IUnit enemy) {
  }

  @Override
  public void equipItemOther(IEquipableItem item) { }

  @Override
  public void equipItemDarkness(Darkness item) { }

  @Override
  public void equipItemLight(Light item) { }

  @Override
  public void equipItemSoul(Soul item) { }

  @Override
  public void equipItemBow(Bow item) { }

  @Override
  public void equipItemAxe(Axe item) {

  }

  @Override
  public void equipItemSword(Sword item) {

  }

  @Override
  public void equipItemStaff(Staff item) {
  }

  @Override
  public void equipItemSpear(Spear item) {
  }

  @Override
  public void receiveAttack(IEquipableItem attack) {
    super.receiveAttack(attack);
  }
}
