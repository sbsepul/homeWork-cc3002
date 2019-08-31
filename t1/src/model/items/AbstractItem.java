package model.items;

import model.items.axe.AttackAxe;
import model.items.bow.AttackBow;
import model.items.spears.AttackSpears;
import model.items.staff.AttackStaff;
import model.items.sword.AttackSword;
import model.units.IUnit;

/**
 * Abstract class that defines some common information and behaviour between all items.
 *
 * @author Sebastian Sepulveda
 * @since 1.0
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

  /**
   * Each unit will have a item different
   * the unit only can to change his item for other equals.
   * @param unit
   */
  @Override
  public void equipTo(final IUnit unit) {
    unit.equipItemOther(this);
    owner = unit;
  }

  @Override
  public IUnit getOwner() {
    return owner;
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
   * A item can receive a Attack without damage additional
   * @param attack
   */
  public void receiveAttack(IAttack attack) {
    this.getOwner().receiveAttack(attack);
  }

  @Override
  public void receiveAxeAttack(AttackAxe attackAxe) { receiveAttack(attackAxe); }

  @Override
  public void receiveBowAttack(AttackBow attackBow) {
    receiveAttack(attackBow);
  }

  @Override
  public void receiveStaffAttack(AttackStaff attackStaff) {
    receiveAttack(attackStaff);
  }

  @Override
  public void receiveSpearsAttack(AttackSpears attackSpears) {
    receiveAttack(attackSpears);
  }

  @Override
  public void receiveSwordsAttack(AttackSword attackSword) {
    receiveAttack(attackSword);
  }


  /**
   * Receives an attack to which this Pokémon is weak.
   *
   * @param attack
   *     Received attack.
   */
  protected void receiveWeaknessAttack(IAttack attack){
    int a = (int) (this.getOwner().getCurrentHitPoints() - attack.getBaseDamage() * 1.5);
    this.getOwner().setCurrentHitPoints(a);
  };


  /**
   * Receives an attack to which this Pokémon is resistant.
   *
   * @param attack
   *     Received attack.
   */
  protected void receiveResistantAttack(IAttack attack) {
    int a = (int) (this.getOwner().getCurrentHitPoints() - attack.getBaseDamage() + 20);
    this.getOwner().setCurrentHitPoints(a);
  }
  //END COMBAT
}
