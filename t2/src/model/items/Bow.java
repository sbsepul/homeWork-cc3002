package model.items;

import model.items.magic.Darkness;
import model.items.magic.Light;
import model.items.magic.Soul;
import model.units.IUnit;

/**
 * This class represents an <i>Bow</i>.
 * <p>
 * Bow don't have strong and weak
 * @author Sebastian Sepulveda
 * @since
 */
public class Bow extends AbstractAttack implements IAttack {

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
    this.setOwner(unit);
  }

  @Override
  public void receiveBowAttack(Bow attackBow) {
    super.receiveAttackNormal(attackBow);
  }

  @Override
  public void receiveAxeAttack(Axe attackAxe) {
    super.receiveAttackNormal(attackAxe);
  }
  @Override
  public void receiveSwordsAttack(Sword attackSword) {
    super.receiveAttackNormal(attackSword);
  }
  @Override
  public void receiveSpearsAttack(Spear attackSpears) {
    super.receiveAttackNormal(attackSpears);
  }

  @Override
  public void receiveDarknessAttack(Darkness attackDarkness) {
    super.receiveMagicAttack(attackDarkness);
  }

  @Override
  public void receiveLightAttack(Light attackLight) {
    super.receiveMagicAttack(attackLight);
  }

  @Override
  public void receiveSoulAttack(Soul attackSoul) {
    super.receiveMagicAttack(attackSoul);
  }

  @Override
  public boolean equals(Object obj) {
    return obj instanceof Bow && super.equals(obj);
  }
}
