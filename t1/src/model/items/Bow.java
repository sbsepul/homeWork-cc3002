package model.items;

import model.units.IUnit;

/**
 * This class represents an <i>Bow</i>.
 * <p>
 * Bow don't have strong and weak
 * @author Ignacio Slater Muñoz
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


  /**
   * Receive a Bow attack if the other unit not is Neighbour (2 spaces, review)
   * @param attackBow
   */
  @Override
  public void receiveBowAttack(Bow attackBow) {
    this.receiveAttack(attackBow);
    if(this.getOwner().getCurrentHitPoints()>0){
      if(attackBow.getOwner().getCurrentHitPoints()>0){
        if(!this.getOwner().getLocation().isNeighbour(attackBow.getOwner().getLocation())) {
          attackBow.getOwner().receiveAttack(attackBow);
        }
        else; //do nothing
      }
      else; //can't counterattack
    }
    else; //can't attack
  }

  /**
   * if the attacker is Neighbour, then receive attack
   * @param attackAxe
   */
  @Override
  public void receiveAxeAttack(Axe attackAxe) {
    this.receiveAttack(attackAxe);
  }
  @Override
  public void receiveSwordsAttack(Sword attackSword) {
    this.receiveAttack(attackSword);
  }
  @Override
  public void receiveSpearsAttack(Spear attackSpears) {
    this.receiveAttack(attackSpears);
  }

  @Override
  public boolean equals(Object obj) {
    return obj instanceof Bow && super.equals(obj);
  }
}
