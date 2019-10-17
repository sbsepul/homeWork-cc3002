package model.items.magic;

import model.items.AbstractItem;
import model.items.IAttack;
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

public class Soul extends AbstractItemMagic implements IAttack {
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
    public void giveMagicAttack(IEquipableItem enemyAttack){
        enemyAttack.receiveSoulAttack(this);
    }

    @Override
    public void receiveDarknessAttack(Darkness attackDarkness) { super.receiveWeakAttack(attackDarkness); }

    @Override
    public void receiveLightAttack(Light attackLight) { super.receiveSoftAttack(attackLight); }

    @Override
    public void receiveSoulAttack(Soul attackSoul) { super.receiveAttackNormal(attackSoul); }
}
