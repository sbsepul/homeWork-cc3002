package model.items;

import model.items.magic.Darkness;
import model.items.magic.Light;
import model.items.magic.Soul;
import model.units.IUnit;

/**
 * This class represents a <i>spear</i>.
 * <p>
 * Spears are strong against swords and weak against axes
 *
 * @author Sebastian Sepulveda
 * @since 1.0
 */
public class Spear extends AbstractAttack implements IAttack {

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
      this.setOwner(unit);
  }

  @Override
  public void receiveBowAttack(Bow attackBow) { super.receiveAttackNormal(attackBow); }

  @Override
  public void receiveSpearsAttack(Spear attackSpears) {
    super.receiveAttackNormal(attackSpears);
  }

  @Override
  public void receiveSwordsAttack(Sword attackSword) {  super.receiveSoftAttack(attackSword);  }

  @Override
  public void receiveAxeAttack(Axe attackAxe) {  super.receiveWeakAttack(attackAxe);  }

  @Override
  public void receiveSoulAttack(Soul attackSoul) { super.receiveMagicAttack(attackSoul); }

  @Override
  public void receiveLightAttack(Light attackLight) { super.receiveMagicAttack(attackLight); }

  @Override
  public void receiveDarknessAttack(Darkness attackDarkness) { super.receiveMagicAttack(attackDarkness); }

  @Override
  public boolean equals(Object obj) {
    return obj instanceof Spear && super.equals(obj);
  }

}
