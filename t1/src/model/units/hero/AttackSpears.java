package model.units.hero;

import model.units.AbstractAttack;
import model.units.IUnit;

public class AttackSpears extends AbstractAttack {
    protected AttackSpears(String name, int damage) {
        super(name, damage);
    }

    @Override
    public void attack(IUnit unit) {
        unit.receiveSpearsAttack(this);
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
