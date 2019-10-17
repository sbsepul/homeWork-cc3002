package model.items.magic;

import model.items.AbstractItem;
import model.items.IAttack;
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

public class Light extends AbstractItemMagic implements IAttack {
    /**
     * Create a new Light item
     *
     * @param name
     *      the name of the light
     * @param power
     *      the power of the light
     * @param minRange
     *      the minimum range of the item
     * @param maxRange
     *      the maximum range of the item
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
    public void giveMagicAttack(IEquipableItem enemyAttack){
        enemyAttack.receiveLightAttack(this);
    }

    @Override
    public void receiveLightAttack(Light attackLight) {
        super.receiveAttackNormal(attackLight);
    }

    @Override
    public void receiveDarknessAttack(Darkness attackDarkness) { super.receiveSoftAttack(attackDarkness); }

    @Override
    public void receiveSoulAttack(Soul attackSoul) { super.receiveWeakAttack(attackSoul);  }
}
