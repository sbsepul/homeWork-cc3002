package model.units.fighter;

import model.units.AbstractAttack;
import model.units.IUnit;

public class AttackAxe extends AbstractAttack {
    protected AttackAxe(String name, int damage) {
        super(name, damage);
    }

    @Override
    public void attack(IUnit unit) {
        unit.receiveAxeAttack(this);
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
