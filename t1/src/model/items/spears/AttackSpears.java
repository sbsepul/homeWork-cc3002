package model.items.spears;

import model.items.AbstractAttack;
import model.items.IAttack;
import model.units.IUnit;

public class AttackSpears extends AbstractAttack {
    protected AttackSpears(String name, int damage) {
        super(name, damage);
    }

    @Override
    public void attack(IUnit unit) {
        unit.receiveSpearsAttack(this);
    }
}
