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
  }


  /**
   * Receive a Bow attack if the other unit not is Neighbour (2 spaces, review)
   * @param attackBow
   */
  @Override
  public void receiveBowAttack(Bow attackBow) {
    if(!this.getOwner().getLocation().isNeighbour(attackBow.getOwner().getLocation())) {
      receiveAttack(attackBow);
    }

  }

  /**
   * if the attacker is Neighbour, then receive attack
   * @param attackAxe
   */
  @Override
  public void receiveAxeAttack(Axe attackAxe) {
    if(this.getOwner().getLocation().isNeighbour(attackAxe.getOwner().getLocation())) {
      receiveAttack(attackAxe);
    }
  }
  @Override
  public void receiveSwordsAttack(Sword attackSword) {
    if(this.getOwner().getLocation().isNeighbour(attackSword.getOwner().getLocation())) {
      receiveAttack(attackSword);
    }
  }
  @Override
  public void receiveSpearsAttack(Spear attackSpears) {
    if(this.getOwner().getLocation().isNeighbour(attackSpears.getOwner().getLocation())) {
      receiveAttack(attackSpears);
    }
  }


  @Override
  public boolean equals(Object obj) {
    return obj instanceof Bow && super.equals(obj);
  }
}
