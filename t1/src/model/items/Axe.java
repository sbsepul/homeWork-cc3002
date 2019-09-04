package model.items;

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
    }

  @Override
  public void receiveBowAttack(Bow attackBow) {
    if(!this.getOwner().getLocation().isNeighbour(attackBow.getOwner().getLocation())) {
      receiveAttack(attackBow);
    }
  }

  @Override
  public void receiveAxeAttack(Axe attackAxe) {
    this.receiveAttack(attackAxe);
  }

  @Override
  public void receiveSwordsAttack(Sword attackSword) {
    this.receiveWeaknessAttack(attackSword);
  }

  @Override
  public void receiveSpearsAttack(Spear attackSpears) {
    this.receiveResistantAttack(attackSpears);
  }

  @Override
  public void receiveStaffAttack(Staff attackStaff) {
    receiveRecovery(attackStaff);
  }

  @Override
  public boolean equals(Object obj) {
    return obj instanceof Axe && super.equals(obj);
  }
}
