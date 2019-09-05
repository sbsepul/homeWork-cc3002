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
public class Axe extends AbstractItem {

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
    this.receiveAttack(attackBow);
  }

  @Override
  public void receiveAxeAttack(Axe attackAxe) { super.receiveAxeAttack(attackAxe); }

  @Override
  public void receiveSwordsAttack(Sword attackSword) {
    this.receiveWeaknessAttack(attackSword);
    //if this owner is life yet
    if(this.getOwner().getCurrentHitPoints()>0){
      if(attackSword.getOwner().getCurrentHitPoints()>0){
        attackSword.getOwner().receiveAttackResistant(this);
      }
    }
  }

  @Override
  public void receiveSpearsAttack(Spear attackSpears) {
    this.receiveResistantAttack(attackSpears);
    if(this.getOwner().getCurrentHitPoints()>0){
      if(attackSpears.getOwner().getCurrentHitPoints()>0){
        attackSpears.getOwner().receiveAttackWeakness(this);
      }
    }
  }

  @Override
  public void receiveSoulAttack(Soul attackSoul) { super.receiveSoulAttack(attackSoul); }

  @Override
  public void magicAttack(IEquipableItem enemyAttack) {

  }

  @Override
  public void receiveLightAttack(Light attackLight) { super.receiveLightAttack(attackLight); }

  @Override
  public void receiveDarknessAttack(Darkness attackDarkness) { super.receiveDarknessAttack(attackDarkness); }

  @Override
  public boolean equals(Object obj) {
    return obj instanceof Axe && super.equals(obj);
  }
}
