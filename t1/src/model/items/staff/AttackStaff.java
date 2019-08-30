package model.items.staff;

import model.items.AbstractAttack;
import model.units.IUnit;

public class AttackStaff extends AbstractAttack {

    protected AttackStaff(String name, int damage) {
        super(name, damage);
    }
    @Override
    public void attack(IUnit unit) {
        unit.receiveStaffAttack(this);
    }

}
