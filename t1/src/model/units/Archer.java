package model.units;

import model.items.*;
import model.items.axe.AttackAxe;
import model.items.axe.Axe;
import model.items.bow.AttackBow;
import model.items.bow.Bow;
import model.items.spears.AttackSpears;
import model.items.spears.Spear;
import model.items.staff.AttackStaff;
import model.items.staff.Staff;
import model.items.sword.AttackSword;
import model.items.sword.Sword;
import model.map.Location;

/**
 * This class represents an <i>Archer</i> type unit.
 * <p>
 * This kind of unit <b>can only use bows</b>.
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class Archer extends AbstractUnit {

  /**
   * Creates a new archer
   *
   * @param hitPoints
   *     maximum hit points of the unit
   * @param movement
   *     the amount of cells this unit can move
   * @param position
   *     the initial position of this unit
   * @param items
   *     the items carried by this unit
   */
  public Archer(final int hitPoints, final int movement, final Location position,
      final IEquipableItem... items) {
    super(hitPoints, movement, position, 3, items);
  }

  @Override
  protected void receiveRecovery(IAttack attack) {
    super.receiveRecovery(attack);
  }

  @Override
  public void equipItemBow(Bow item) { equippedItem = item; }
  @Override
  public void equipItemAxe(Axe item) { }
  @Override
  public void equipItemSword(Sword item) { }
  @Override
  public void equipItemStaff(Staff item) {  }
  @Override
  public void equipItemSpear(Spear item) {  }
  @Override
  public void equipItemOther(IEquipableItem item) {  }

  /**
   * Sets the currently equipped item of this unit.
   * <p>
   * The <i>Archer</i> can <b>only equip Bows</b>.
   *     the item to equip
   */

  @Override
  public IEquipableItem getEquippedItem() {
    return equippedItem;
  }

}
