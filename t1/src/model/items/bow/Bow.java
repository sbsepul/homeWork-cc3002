package model.items.bow;

import model.items.AbstractItem;
import model.items.IEquipableItem;
import model.items.axe.AttackAxe;
import model.items.spears.AttackSpears;
import model.items.staff.AttackStaff;
import model.items.sword.AttackSword;
import model.units.IUnit;

/**
 * This class represents an <i>Bow</i>.
 * <p>
 * Bow don't have strong and weak
 * @author Ignacio Slater Muñoz
 * @since
 */
public class Bow extends AbstractItem {

  /**
   * Creates a new bow.
   * <p>
   * Bows are weapons that can't attack adjacent units, so it's minimum range must be greater than
   * one.
   *
   * @param name
   *     the name of the bow
   * @param power
   *     the damage power of the bow
   * @param minRange
   *     the minimum range of the bow
   * @param maxRange
   *     the maximum range of the bow
   */
  public Bow(final String name, final int power, final int minRange, final int maxRange) {
    super(name, power, minRange, maxRange);
    this.minRange = Math.max(minRange, 2);
    this.maxRange = Math.max(maxRange, this.minRange);
  }

  @Override
  public void equipTo(IUnit unit) {
    unit.equipItemBow(this);
  }

  @Override
  public void attack(IUnit item) {

  }

  @Override
  public void receiveBowAttack(AttackBow attackBow) {}
  @Override
  public void receiveAxeAttack(AttackAxe attackAxe) {}
  @Override
  public void receiveSwordsAttack(AttackSword attackSword) {}
  @Override
  public void receiveSpearsAttack(AttackSpears attackSpears) {

  }
}
