package model.items;

import model.items.magic.Darkness;
import model.items.magic.Light;
import model.items.magic.Soul;
import model.units.IUnit;

/**
 * This class represents a <i>Sword</i> type item.
 * <p>
 * Swords are strong against axes and weak against spears.
 *
 * @author Sebastian Sepulveda
 * @since 1.0
 */
public class Sword extends AbstractAttack implements IAttack {

  /**
   * Creates a new Sword.
   *
   * @param name
   *     the name that identifies the weapon
   * @param power
   *     the base damage pf the weapon
   * @param minRange
   *     the minimum range of the weapon
   * @param maxRange
   *     the maximum range of the weapon
   */
  public Sword(final String name, final int power, final int minRange, final int maxRange) {
    super(name, power, minRange, maxRange);
  }

  @Override
  public void equipTo(IUnit unit) {
    unit.equipItemSword(this);
    this.setOwner(unit);
  }

  @Override
  public void receiveBowAttack(Bow attackBow) {
    receiveAttackNormal(attackBow);
  }
  @Override
  public void receiveSwordsAttack(Sword attackSword) {
    receiveAttackNormal(attackSword);
  }

  @Override
  public void receiveAxeAttack(Axe attackAxe) {
    receiveSoftAttack(attackAxe);
  }

  @Override
  public void receiveSpearsAttack(Spear attackSpears) {
    receiveWeakAttack(attackSpears);
  }

  @Override
  public void receiveSoulAttack(Soul attackSoul) { receiveMagicAttack(attackSoul); }

  @Override
  public void receiveLightAttack(Light attackLight) { receiveMagicAttack(attackLight); }

  @Override
  public void receiveDarknessAttack(Darkness attackDarkness) { receiveMagicAttack(attackDarkness); }

  @Override
  public boolean equals(Object obj) {
    return obj instanceof Sword && super.equals(obj);
  }

}
