package model.units;

import model.items.*;
import model.items.Axe;
import model.items.Bow;
import model.items.Spear;
import model.items.Staff;
import model.items.Sword;
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


  public void attack(IUnit enemy){
    if (this.getCurrentHitPoints()>0){
      if (this.getEquippedItem()!=null){
        if(enemy.getEquippedItem()!=null){
          enemy.getEquippedItem().receiveAxeAttack((Axe) this.getEquippedItem());
          if(enemy.getCurrentHitPoints()>0){
            this.receiveAttack(enemy.getEquippedItem());
          }
          else{
            //enemy can't continue attacking
          }
        }
        else{
          enemy.receiveAttack(this.getEquippedItem());
          //enemy haven't weapon, then can't continue attacking.
        }
      }
      else{
        //do nothing
      }
    }
    else{
      //do nothing
    }
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
