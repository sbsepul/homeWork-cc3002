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
  }

  @Override
  public void receiveSwordsAttack(Sword attackSword) { receiveAttack(attackSword);
  }

  @Override
  public void receiveBowAttack(Bow attackBow) {
    if(this.getOwner().getLocation().isNeighbour(attackBow.getOwner().getLocation())) {
      receiveAttack(attackBow);
    }
  }

  @Override
  public void receiveSpearsAttack(Spear attackSpears) {
    super.receiveWeaknessAttack(attackSpears);
  }

  @Override
  public void receiveAxeAttack(Axe attackAxe) {
    super.receiveResistantAttack(attackAxe);
  }

  @Override
  public void receiveStaffAttack(Staff attackStaff) {
    receiveRecovery(attackStaff);
  }

  @Override
  public boolean equals(Object obj) {
    return obj instanceof Spear && super.equals(obj);
  }

}
