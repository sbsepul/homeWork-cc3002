package model.units;

import model.items.*;
import model.items.axe.AttackAxe;
import model.items.axe.Axe;
import model.items.bow.AttackBow;
import model.items.bow.Bow;
import model.items.spears.AttackSpears;
import model.items.spears.Spear;
import model.items.staff.AttackStaff;
import model.items.staff.Staff;
import model.items.sword.AttackSword;
import model.items.sword.Sword;
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
  public void equipItemOther(IEquipableItem item) {

  }

  @Override
  public void equipItemBow(Bow item) {

  }

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
    public IEquipableItem getEquippedItem() {
        return null;
    }

  @Override
  public void receiveBowAttack(AttackBow attackBow) {
    receiveAttack(attackBow);
  }

  @Override
  public void receiveAxeAttack(AttackAxe attackAxe) {
    receiveAttack(attackAxe);
  }

  @Override
  public void receiveSwordsAttack(AttackSword attackSword) {
  receiveAttack(attackSword);
  }

  @Override
  public void receiveSpearsAttack(AttackSpears attackSpears) {
  receiveAttack(attackSpears);
  }

  @Override
  public void receiveStaffAttack(AttackStaff attackStaff) {
    receiveCure(attackStaff);
  }
}
