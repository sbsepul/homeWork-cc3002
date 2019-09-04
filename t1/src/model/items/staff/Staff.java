package model.items.staff;

import model.items.AbstractItem;
import model.items.IEquipableItem;
import model.items.axe.AttackAxe;
import model.items.bow.AttackBow;
import model.items.spears.AttackSpears;
import model.items.sword.AttackSword;
import model.units.IUnit;

/**
 * This class represents a <i>Staff</i> type item.
 * <p>
 * A staff is an item that can heal other units but cannot counter any attack
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class Staff extends AbstractItem {

  /**
   * Creates a new Staff item.
   *
   * @param name
   *     the name of the staff
   * @param power
   *     the healing power of the staff
   * @param minRange
   *     the minimum range of the staff
   * @param maxRange
   *     the maximum range of the staff
   */
  public Staff(final String name, final int power, final int minRange, final int maxRange) {
    super(name, power, minRange, maxRange);
  }

  @Override
  public void equipTo(IUnit unit) {
    unit.equipItemStaff(this);
  }

  public void recovery(IUnit unit) {
    unit.receiveRecovery(this.getPower());
  }

  @Override
  public void receiveBowAttack(AttackBow attackBow) {
    this.receiveAttack(attackBow);
  }

  @Override
  public void receiveAxeAttack(AttackAxe attackAxe) {
    this.receiveAttack(attackAxe);
  }

  @Override
  public void receiveSwordsAttack(AttackSword attackSword) {
    this.receiveAttack(attackSword);
  }

  @Override
  public void receiveSpearsAttack(AttackSpears attackSpears) {
    this.receiveAttack(attackSpears);
  }

  @Override
  public void receiveStaffAttack(AttackStaff attackStaff) {
    //do nothing
  }

  @Override
  public boolean equals(Object obj) {
    return obj instanceof Staff && super.equals(obj);
  }
}
