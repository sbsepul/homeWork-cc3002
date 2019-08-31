package model.items.spears;

import model.items.AbstractItem;
import model.items.IEquipableItem;
import model.items.axe.AttackAxe;
import model.items.bow.AttackBow;
import model.items.staff.AttackStaff;
import model.items.sword.AttackSword;
import model.units.IUnit;

/**
 * This class represents a <i>spear</i>.
 * <p>
 * Spears are strong against swords and weak against axes
 *
 * @author Sebastian Sepulveda
 * @since 1.0
 */
public class Spear extends AbstractItem {

  /**
   * Creates a new Axe item
   *
   * @param name
   *     the name of the Axe
   * @param power
   *     the damage of the axe
   * @param minRange
   *     the minimum range of the axe
   * @param maxRange
   *     the maximum range of the axe
   */
  public Spear(final String name, final int power, final int minRange, final int maxRange) {
    super(name, power, minRange, maxRange);
  }


  @Override
  public void equipTo(IUnit unit) {
      unit.equipItemSpear(this);
  }

  @Override
  public void attack(IUnit item) {

  }

  @Override
  public void receiveSwordsAttack(AttackSword attackSword) {
    super.receiveSwordsAttack(attackSword);
  }

  @Override
  public void receiveBowAttack(AttackBow attackBow) {
    super.receiveBowAttack(attackBow);
  }

  @Override
  public void receiveSpearsAttack(AttackSpears attackSpears) {
    super.receiveSpearsAttack(attackSpears);
  }

  @Override
  public void receiveAxeAttack(AttackAxe attackAxe) {
    super.receiveAxeAttack(attackAxe);
  }

}
