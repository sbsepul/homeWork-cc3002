package model.items.magic;

import model.items.AbstractItem;
import model.items.IEquipableItem;
import model.units.IUnit;

/**
 * This class represents an <i>Soul</i>.
 * <p>
 * Soul is weakness counter Darkness
 * But resistant counter Light
 *
 * @author Sebastian Sepulveda
 * @since 1.0
 */

public class Soul extends AbstractItemMagic {
    /**
     * Create a new Soul item
     *
     * @param name
     *      the name of the soul
     * @param power
     *      the power of the soul
     * @param minRange
     *      the minimum range of the item
     * @param maxRange
     *      the maximum range of the item
     */
    public Soul(String name, int power, int minRange, int maxRange) {
        super(name, power, minRange, maxRange);
    }

    @Override
    public void equipTo(IUnit unit) {
        unit.equipItemSoul(this);
        this.setOwner(unit);
    }

    @Override
    public void receiveMagicAttack(IEquipableItem enemyAttack){
        enemyAttack.receiveSoulAttack(this);
    }

    @Override
    public void receiveDarknessAttack(Darkness attackDarkness) {
        this.receiveWeaknessAttack(attackDarkness);
        if(this.canAttack(attackDarkness)){
            attackDarkness.getOwner().receiveAttackResistant(this);
        }
    }

    @Override
    public void receiveLightAttack(Light attackLight) {
        this.receiveResistantAttack(attackLight);
        if(this.canAttack(attackLight)){
            attackLight.getOwner().receiveAttackWeakness(this);
        }
    }

    @Override
    public void receiveSoulAttack(Soul attackSoul) {
        this.receiveAttack(attackSoul);
        if(this.canAttack(attackSoul)){
            attackSoul.getOwner().receiveAttack(this);
        }
    }
}
