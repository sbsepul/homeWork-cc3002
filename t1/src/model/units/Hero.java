package model.units;

import model.items.*;
import model.items.Axe;
import model.items.Bow;
import model.items.Spear;
import model.items.Staff;
import model.items.Sword;
import model.items.magic.Darkness;
import model.items.magic.Light;
import model.items.magic.Soul;
import model.map.Location;

/**
 * A <i>Hero</i> is a special kind of unit, the player that defeats this unit wins the game.
 * <p>
 * This unit <b>can only use spear weapons</b>.
 *
 * @author Sebastian Sepulveda
 * @since 1.0
 */
public class Hero extends AbstractUnit {

  /**
   * Creates a new Unit.
   *
   * @param hitPoints
   *     the maximum amount of damage a unit can sustain
   * @param movement
   *     the number of panels a unit can move
   */
  public Hero(final int hitPoints, final int movement, final Location location,
      IEquipableItem... items) {
    super(hitPoints, movement, location, 3, items);
  }

  @Override
  public void equipItemSpear(Spear item) {
    equippedItem = item;
  }

  @Override
  protected void attack(IUnit enemy) {
    if (this.getCurrentHitPoints()>0 && enemy.getCurrentHitPoints()>0) {
      if (this.getEquippedItem() != null) {
        if(this.isInRange(enemy)){
          if(enemy.getEquippedItem()!=null){
            enemy.getEquippedItem().receiveSpearsAttack((Spear) this.getEquippedItem());
          }
          else{
            enemy.receiveAttack(this.getEquippedItem());
          }
        }
        //this unit isn't in his army's range
      }
      // this unit haven't army
    }
    // this unit or enemy rip
  }

  @Override
  public IEquipableItem getEquippedItem() {
    return equippedItem;
  }

  @Override
  public void equipItemOther(IEquipableItem item) { }

  @Override
  public void equipItemDarkness(Darkness item) {  }

  @Override
  public void equipItemLight(Light item) {  }

  @Override
  public void equipItemSoul(Soul item) {  }

  @Override
  public void equipItemBow(Bow item) { }

  @Override
  public void equipItemAxe(Axe item) { }

  @Override
  public void equipItemSword(Sword item) { }

  @Override
  public void equipItemStaff(Staff item) { }
}
