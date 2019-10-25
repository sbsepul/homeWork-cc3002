package model.units;

import model.units.handlers.ResponseSpecialUnit;
import model.items.*;
import model.items.Spear;
import model.map.Location;

/**
 * A <i>Hero</i> is a special kind of unit, the player that defeats this unit wins the game.
 * <p>
 * This unit <b>can only use spear weapons</b>.
 *
 * @author Sebastian Sepulveda
 * @since 1.0
 */
public class Hero extends AbstractSpecialUnit{

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
    changeSupport.addPropertyChangeListener(new ResponseSpecialUnit(this));
  }

  @Override
  public void equipItemSpear(Spear item) {
    equippedItem = item;
  }
  @Override
  public void attack(IUnit enemy) {
    if (this.initCombat(enemy)){
      if(enemy.getEquippedItem()!=null){
        enemy.getEquippedItem().receiveSpearsAttack((Spear) this.getEquippedItem());
      }
      else enemy.receiveAttack(this.getEquippedItem());
    }
  }
}
