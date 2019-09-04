package model.units;

import model.items.*;
import model.items.Axe;
import model.items.Bow;
import model.items.Spear;
import model.items.Staff;
import model.items.Sword;
import model.map.Location;

/**
 * This class represents a cleric type unit. A cleric can only use staff type weapons, which means
 * that it can receive attacks but can't counter attack any of those.
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class Cleric extends AbstractUnit {

  /**
   * Creates a new Unit.
   *
   * @param hitPoints
   *     the maximum amount of damage a unit can sustain
   * @param movement
   *     the number of panels a unit can move
   */
  public Cleric(final int hitPoints, final int movement, final Location location,
      IEquipableItem... items) {
    super(hitPoints, movement, location, 3, items);
  }


  //cleric can't attack, but can recovery a unit
  public void attack(IUnit enemy){
    if (this.getCurrentHitPoints()>0 && enemy.getCurrentHitPoints()>0){
        enemy.receiveRecovery(this.getEquippedItem());
    }
    else{
      // this unit or enemy rip
    }
  }

  @Override
  public IEquipableItem getEquippedItem() {
    return equippedItem;
  }


  @Override
  public void equipItemStaff(Staff item) {
    equippedItem = item;
  }

  /**
   * equip with a item the unit
   * @param item
   */
  @Override
  public void equipItemSpear(Spear item) { }

  @Override
  public void equipItemSword(Sword item) {  }

  @Override
  public void equipItemOther(IEquipableItem item){ }

  @Override
  public void equipItemBow(Bow item) { }

  @Override
  public void equipItemAxe(Axe item) { }


}
