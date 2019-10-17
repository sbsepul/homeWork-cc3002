package model.items;

import model.items.magic.Darkness;
import model.items.magic.Light;
import model.items.magic.Soul;
import model.units.IUnit;

/**
 * This class represents an Axe.
 * <p>
 * Axes are strong against spears but weak against swords.
 *
 * @author Sebastian Sepulveda
 * @since 1.0
 */
public class Axe extends AbstractAttack implements IAttack{

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
  public Axe(final String name, final int power, final int minRange, final int maxRange) {
    super(name, power, minRange, maxRange);
  }

  @Override
    public void equipTo(IUnit unit) {
      unit.equipItemAxe(this);
      this.setOwner(unit);
    }

  @Override
  public void receiveBowAttack(Bow attackBow) {
    super.receiveAttackNormal(attackBow);
  }

  @Override
  public void receiveAxeAttack(Axe attackAxe) { super.receiveAttackNormal(attackAxe); }

  @Override
  public void receiveSwordsAttack(Sword attackSword) { super.receiveWeakAttack(attackSword);  }

  @Override
  public void receiveSpearsAttack(Spear attackSpears) { super.receiveSoftAttack(attackSpears);  }

  @Override
  public void receiveSoulAttack(Soul attackSoul) { super.receiveMagicAttack(attackSoul); }

  @Override
  public void receiveLightAttack(Light attackLight) { super.receiveMagicAttack(attackLight); }

  @Override
  public void receiveDarknessAttack(Darkness attackDarkness) { super.receiveMagicAttack(attackDarkness); }

  @Override
  public boolean equals(Object obj) {
    return obj instanceof Axe && super.equals(obj);
  }

}
