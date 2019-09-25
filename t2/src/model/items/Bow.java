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
public class Bow extends AbstractItem {

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
    this.receiveAttack(attackBow);
    if(this.canAttack(attackBow)){
      if(attackBow.getOwner().getCurrentHitPoints()>0){
          attackBow.getOwner().receiveAttack(attackBow);
      }
    }
    else; //can't attack
  }

  @Override
  public void receiveAxeAttack(Axe attackAxe) {
    this.receiveAttack(attackAxe);
    if(this.canAttack(attackAxe)){
      attackAxe.receiveAttack(this);
    }
  }
  @Override
  public void receiveSwordsAttack(Sword attackSword) {
    this.receiveAttack(attackSword);
    if(this.canAttack(attackSword)){
      attackSword.receiveAttack(this);
    }
  }
  @Override
  public void receiveSpearsAttack(Spear attackSpears) {
    this.receiveAttack(attackSpears);
    if(this.canAttack(attackSpears)){
      attackSpears.receiveAttack(this);
    }
  }

  @Override
  public void receiveDarknessAttack(Darkness attackDarkness) {
    this.receiveWeaknessAttack(attackDarkness);
    if(this.canAttack(attackDarkness)){
      attackDarkness.receiveWeaknessAttack(this);
    }
  }

  @Override
  public void receiveLightAttack(Light attackLight) {
    this.receiveWeaknessAttack(attackLight);
    if(this.canAttack(attackLight)){
      attackLight.receiveWeaknessAttack(this);
    }
  }

  @Override
  public void receiveSoulAttack(Soul attackSoul) {
    this.receiveWeaknessAttack(attackSoul);
    if(this.canAttack(attackSoul)){
      attackSoul.receiveWeaknessAttack(this);
    }
  }

  @Override
  public boolean equals(Object obj) {
    return obj instanceof Bow && super.equals(obj);
  }
}
