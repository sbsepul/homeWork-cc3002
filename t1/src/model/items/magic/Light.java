package model.items.magic;

import model.items.AbstractItem;
import model.items.IEquipableItem;
import model.units.IUnit;

/**
 * This class represents an <i>Light</i>.
 * <p>
 * Light is weakness counter Soul
 * But resistant counter Darkness
 *
 * @author Sebastian Sepulveda
 * @since 1.0
 */

public class Light extends AbstractItemMagic {
    /**
     * Constructor for a default item without any special behaviour.
     *
     * @param name     the name of the item
     * @param power    the power of the item (this could be the amount of damage or healing the item does)
     * @param minRange the minimum range of the item
     * @param maxRange
     */
    public Light(String name, int power, int minRange, int maxRange) {
        super(name, power, minRange, maxRange);
    }

    @Override
    public void equipTo(IUnit unit) {
        unit.equipItemLight(this);
        this.setOwner(unit);
    }

    @Override
    public void magicAttack(IEquipableItem enemyAttack){
        enemyAttack.receiveLightAttack(this);
    }

    @Override
    public void receiveLightAttack(Light attackLight) {
        this.receiveAttack(attackLight);
        if(attackLight.getOwner().getCurrentHitPoints()>0){
            attackLight.receiveAttack(this);
        }
    }

    @Override
    public void receiveDarknessAttack(Darkness attackDarkness) {
        this.receiveResistantAttack(attackDarkness);
        if(attackDarkness.getOwner().getCurrentHitPoints()>0){
            attackDarkness.getOwner().receiveAttackWeakness(this);
        }
    }

    @Override
    public void receiveSoulAttack(Soul attackSoul) {
        this.receiveWeaknessAttack(attackSoul);
        if(attackSoul.getOwner().getCurrentHitPoints()>0){
            attackSoul.getOwner().receiveAttackResistant(this);
        }
    }
}
