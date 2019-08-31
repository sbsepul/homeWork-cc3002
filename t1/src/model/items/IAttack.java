package model.items;

import model.units.IUnit;

/**
 * This interface represents the <i>attacks</i> that the units can use when have a weapon.
 *
 *
 * @author Sebastian Sepulveda
 * @since 1.0
 */
public interface IAttack {
    /**
     * Attack to unit
     * @param unit
     */
    void attack(IUnit unit);

    /**
     * Getter for the base damage.
     * @return Base damage of the attack.
     */
    int getBaseDamage();

    /**
     * Getter for recognise the item used for the unit
     * @return Item that realize the attack
     */
    IEquipableItem getTypeItem();

}
