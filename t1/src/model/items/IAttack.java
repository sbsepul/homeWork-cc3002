package model.items;

import model.units.IUnit;

public interface IAttack {
    /**
     * An attack
     * @param unit
     */
    void attack(IUnit unit);

    /**
     * Getter for the base damage.
     * @return Base damage of the attack.
     */
    int getBaseDamage();

    /**
     *
     * @return
     */
    String getName();

    IAttack getSelectAttack();



}
