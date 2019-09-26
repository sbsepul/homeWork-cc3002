package model.items;

import model.items.magic.Darkness;
import model.items.magic.Light;
import model.items.magic.Soul;
import model.units.IUnit;

/**
 * Abstract class that defines some common information and behaviour between all items normals.
 *
 * @author Sebastian Sepulveda
 * @version 2.0
 * @since v1.0
 */
public abstract class AbstractItem implements IEquipableItem {

  private final String name;
  private final int power;
  protected int maxRange;
  protected int minRange;
  private IUnit owner;

  /**
   * Constructor for a default item without any special behaviour.
   *
   * @param name
   *     the name of the item
   * @param power
   *     the power of the item (this could be the amount of damage or healing the item does)
   * @param minRange
   *     the minimum range of the item
   * @param maxRange
   *     the maximum range of the item
   */
  public AbstractItem(final String name, final int power, final int minRange, final int maxRange) {
    this.name = name;
    this.power = power;
    this.minRange = Math.max(minRange, 1);
    this.maxRange = Math.max(maxRange, this.minRange);
  }


  @Override
  public boolean canAttack(IEquipableItem itemEnemy) {
    if(itemEnemy.getOwner()!=null){
      return this.getOwner().getCurrentHitPoints()>0 && this.getOwner().isInRange(itemEnemy.getOwner())
              && itemEnemy.getOwner().getCurrentHitPoints()>0;
    }
    return false;
  }

  @Override
  public IUnit getOwner() {
    return owner;
  }

  @Override
  public void setOwner(IUnit owner) {
    this.owner = owner;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public int getPower() {
    return power;
  }

  @Override
  public int getMinRange() {
    return minRange;
  }

  @Override
  public int getMaxRange() {
    return maxRange;
  }

  //COMBAT

  /**
   * Receives an attack to which this Unit is weak.
   *
   * @param attack
   *     Received attack.
   */
  protected void receiveWeaknessAttack(IEquipableItem attack){
    this.getOwner().receiveAttackWeakness(attack);
  }


  /**
   * Receives an attack to which this Unit is resistant.
   *
   * @param attack
   *     Received attack.
   */
  protected void receiveResistantAttack(IEquipableItem attack) {
    this.getOwner().receiveAttackResistant(attack);
  }

  /**
   * A item can receive a Attack without damage additional
   * @param attack
   */
  protected void receiveAttack(IEquipableItem attack) {
    this.getOwner().receiveAttack(attack);
  }

  @Override
  public void receiveDarknessAttack(Darkness attackDarkness) {
    this.receiveWeaknessAttack(attackDarkness);
    if(this.canAttack(attackDarkness)){
      attackDarkness.getOwner().receiveAttackWeakness(this);
    }
  }

  @Override
  public void receiveLightAttack(Light attackLight) {
    this.receiveWeaknessAttack(attackLight);
    if(this.canAttack(attackLight)){
      attackLight.getOwner().receiveAttackWeakness(this);
    }
  }

  @Override
  public void receiveSoulAttack(Soul attackSoul) {
    this.receiveWeaknessAttack(attackSoul);
    if(this.canAttack(attackSoul)){
      attackSoul.getOwner().receiveAttackWeakness(this);
    }
  }

  @Override
  public void receiveAxeAttack(Axe attackAxe) {
    this.receiveAttack(attackAxe);
    if(this.canAttack(attackAxe)){
      if(attackAxe.getOwner().getCurrentHitPoints()>0){
        attackAxe.getOwner().receiveAttack(this);
      }
    }
  }

  @Override
  public void receiveSpearsAttack(Spear attackSpears) {
    this.receiveAttack(attackSpears);
    if(this.canAttack(attackSpears)){
      attackSpears.getOwner().receiveAttack(this);
    }
  }

  @Override
  public void receiveSwordsAttack(Sword attackSword) {
    this.receiveAttack(attackSword);
    if(this.canAttack(attackSword)){
      if(attackSword.getOwner().getCurrentHitPoints()>0){
        attackSword.getOwner().receiveAttack(this);
      }
    }
  }

  @Override
  public void receiveBowAttack(Bow attackBow) {
    receiveAttack(attackBow);
  }

  @Override
  public void receiveMagicAttack(IEquipableItem enemyAttack){ }

  //END COMBAT

  @Override
  public boolean equals(Object obj){
    return obj instanceof IEquipableItem && ((IEquipableItem) obj).getPower()==this.getPower()
            && ((IEquipableItem) obj).getMinRange() == this.getMinRange()
            && ((IEquipableItem) obj).getMaxRange() == this.getMaxRange()
            && ((IEquipableItem) obj).getName() == this.getName()
            && ((IEquipableItem) obj).getOwner() == this.getOwner();
  }

}
