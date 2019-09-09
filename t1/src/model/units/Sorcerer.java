package model.units;

import model.items.*;
import model.items.magic.*;
import model.map.Location;

/**
 * A <i>Sorcerer</i> is a magic kind of unit, his item's attack always is weak to other item
 * <p>
 * This unit <b>can only use magic weapons</b>.
 * ie. Light - Darkness - Soul
 *
 * @author Sebastian Sepulveda
 * @since 1.0
 */

public class Sorcerer extends AbstractUnit{

    /**
     * Creates a new Unit.
     *  @param hitPoints the maximum amount of damage a unit can sustain
     * @param movement  the number of panels a unit can move
     * @param location  the current position of this unit on the map
     */
    public Sorcerer(int hitPoints, int movement, Location location, IEquipableItem... items) {
        super(hitPoints, movement, location, 3, items);
    }

    @Override
    public void equipItemDarkness(Darkness item) {
        equippedItem=item;
    }
    @Override
    public void equipItemLight(Light item) {
        equippedItem=item;
    }
    @Override
    public void equipItemSoul(Soul item) {
        equippedItem=item;
    }
    @Override
    public void equipItemBow(Bow item) {}
    @Override
    public void equipItemAxe(Axe item) {}
    @Override
    public void equipItemSword(Sword item) {}
    @Override
    public void equipItemStaff(Staff item) {}
    @Override
    public void equipItemSpear(Spear item) {}

    @Override
    public void attack(IUnit enemy) {
        if (this.getCurrentHitPoints()>0 && enemy.getCurrentHitPoints()>0) {
            if (this.getEquippedItem() != null) {
                if(this.isInRange(enemy)){
                    if(enemy.getEquippedItem()!=null){
                        this.getEquippedItem().magicAttack(enemy.getEquippedItem());
                        // en esta seccion hay que hacer un double dispatch que permita
                        // saber al contrincante qué tipo de ataque va a recibir.
                        // si este es mago o no  puede ser el DD
                    }
                    else{
                        enemy.receiveAttack(this.getEquippedItem());
                    }
                }
                // this unit isn't in the range
            }
            // this unit haven't army
        }
        // this unit or enemy rip
    }
}
