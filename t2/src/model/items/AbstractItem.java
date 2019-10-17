package model.items;

import model.items.magic.Darkness;
import model.items.magic.Light;
import model.items.magic.Soul;
import model.units.IUnit;
import model.units.NullUnit;

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
    this.owner = new NullUnit();
  }


  @Override
  public boolean canAttack(IEquipableItem itemEnemy) {
    if(!itemEnemy.getOwner().isNull()){
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

  @Override
  public boolean isEmpty() {
    return false;
  }

  public boolean isEquipped(){
    return false;
  }

  @Override
  public void receiveUnitWeaknessAttack(IEquipableItem attack){
    this.getOwner().receiveAttackWeakness(attack);
  }


  @Override
  public void receiveUnitResistantAttack(IEquipableItem attack) {
    this.getOwner().receiveAttackResistant(attack);
  }


  @Override
  public void receiveUnitAttack(IEquipableItem attack) {
    this.getOwner().receiveAttack(attack);
  }

  @Override
  public void giveMagicAttack(IEquipableItem itemMagic){ }

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
