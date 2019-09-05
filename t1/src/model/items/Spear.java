package model.items;

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
      receiveAttack(attackBow);
  }

  @Override
  public void receiveSpearsAttack(Spear attackSpears) {
    this.receiveAttack(attackSpears);
    if(attackSpears.getOwner().getCurrentHitPoints()>0){
      attackSpears.getOwner().receiveAttack(this);
    }
  }

  @Override
  public void receiveSwordsAttack(Sword attackSword) {
    this.receiveWeaknessAttack(attackSword);
    if(this.getOwner().getCurrentHitPoints()>0){
      if(attackSword.getOwner().getCurrentHitPoints()>0){
        attackSword.getOwner().receiveAttackResistant(this);
      }
    }
  }

  @Override
  public void receiveAxeAttack(Axe attackAxe) {
    this.receiveResistantAttack(attackAxe);
    if(attackAxe.getOwner().getCurrentHitPoints()>0){
      attackAxe.getOwner().receiveAttackWeakness(this);
    }
  }

  @Override
  public boolean equals(Object obj) {
    return obj instanceof Spear && super.equals(obj);
  }

}
