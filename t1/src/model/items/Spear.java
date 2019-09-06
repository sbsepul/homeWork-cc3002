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
      this.setOwner(unit);
  }

  @Override
  public void receiveBowAttack(Bow attackBow) {
      super.receiveBowAttack(attackBow);
  }

  @Override
  public void receiveSpearsAttack(Spear attackSpears) {
    super.receiveSpearsAttack(attackSpears);
  }

  @Override
  public void receiveSwordsAttack(Sword attackSword) {
    this.receiveResistantAttack(attackSword);
    if(this.getOwner().getCurrentHitPoints()>0){
      if(attackSword.getOwner().getCurrentHitPoints()>0){
        attackSword.getOwner().receiveAttackWeakness(this);
      }
    }
  }

  @Override
  public void receiveAxeAttack(Axe attackAxe) {
    this.receiveWeaknessAttack(attackAxe);
    if(attackAxe.getOwner().getCurrentHitPoints()>0){
      attackAxe.getOwner().receiveAttackResistant(this);
    }
  }

  @Override
  public void receiveSoulAttack(Soul attackSoul) { super.receiveSoulAttack(attackSoul); }


  @Override
  public void receiveLightAttack(Light attackLight) { super.receiveLightAttack(attackLight); }

  @Override
  public void receiveDarknessAttack(Darkness attackDarkness) { super.receiveDarknessAttack(attackDarkness); }

  @Override
  public boolean equals(Object obj) {
    return obj instanceof Spear && super.equals(obj);
  }

}
