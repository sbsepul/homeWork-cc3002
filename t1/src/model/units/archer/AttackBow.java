package model.units.archer;

import model.units.AbstractAttack;
import model.units.IUnit;

import java.util.List;

public class AttackBow extends AbstractAttack{
    /**
     * Creates a new attack.
     *
     * @param name
     *     Attack name
     * @param baseDamage
     *     Base damage of the attack
     */
    public AttackBow(String name, int baseDamage) { super(name, baseDamage); }

    @Override
    public void attack(IUnit unit) {
        unit.receiveBowAttack(this);
    }

    @Override
    public int getBaseDamage() {
        return 0;
    }

    @Override
    public String getName() {
        return null;
    }
}
