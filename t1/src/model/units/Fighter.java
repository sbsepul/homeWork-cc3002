package model.units;

import model.items.*;
import model.items.Axe;
import model.items.Bow;
import model.items.Spear;
import model.items.Staff;
import model.items.Sword;
import model.map.Location;

/**
 * This class represents a fighter type unit.
 * A fighter is a unit that can only use axe type weapons.
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class Fighter extends AbstractUnit {

  public Fighter(final int hitPoints, final int movement, final Location location,
      IEquipableItem... items) {
    super(hitPoints, movement, location, 3, items);
  }
  @Override
  public void equipItemAxe(Axe item) { equippedItem = item;  }

  @Override
  protected void attack(IUnit enemy) {
    if (this.getCurrentHitPoints()>0 && enemy.getCurrentHitPoints()>0) {
      if (this.getEquippedItem() != null) {
        if(enemy.getEquippedItem()!=null){
          enemy.getEquippedItem().receiveAxeAttack((Axe) this.getEquippedItem());
        }
        else{
          enemy.receiveAttack(this.getEquippedItem());
        }
      }
      // this unit haven't army
    }
    // this unit or enemy rip
  }

  @Override
  public IEquipableItem getEquippedItem() { return equippedItem; }

  @Override
  public void equipItemBow(Bow item) { }

  @Override
  public void equipItemSword(Sword item) { }

  @Override
  public void equipItemStaff(Staff item) { }

  @Override
  public void equipItemSpear(Spear item) { }

  @Override
  public void equipItemOther(IEquipableItem item) { }
}
