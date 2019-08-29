package model.units.sword;

import model.units.AbstractAttack;
import model.units.IUnit;

public class AttackSword extends AbstractAttack {
    protected AttackSword(String name, int damage) {
        super(name, damage);
    }

    @Override
    public void attack(IUnit unit) {
        unit.receiveSwordsAttack(this);
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
