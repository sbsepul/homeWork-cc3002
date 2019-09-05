package model.items;

import model.items.magic.Darkness;
import model.items.magic.Light;
import model.items.magic.Soul;
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
    this.setOwner(unit);
  }

  @Override
  public void receiveBowAttack(Bow attackBow) {
    this.receiveAttack(attackBow);
  }

  @Override
  public void receiveAxeAttack(Axe attackAxe) {
    this.receiveAttack(attackAxe);
  }

  @Override
  public void receiveSwordsAttack(Sword attackSword) {
    this.receiveAttack(attackSword);
  }

  @Override
  public void receiveSpearsAttack(Spear attackSpears) {
    this.receiveAttack(attackSpears);
  }

  @Override
  public void receiveDarknessAttack(Darkness attackDarkness) {
    this.receiveWeaknessAttack(attackDarkness);
  }

  @Override
  public void receiveLightAttack(Light attackLight) {
    this.receiveWeaknessAttack(attackLight);
  }

  @Override
  public void receiveSoulAttack(Soul attackSoul) {
    this.receiveWeaknessAttack(attackSoul);
  }

  @Override
  public void magicAttack(IEquipableItem enemyAttack) {

  }

  @Override
  public boolean equals(Object obj) {
    return obj instanceof Staff && super.equals(obj);
  }
}
