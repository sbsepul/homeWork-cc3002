package model.items.staff;

import model.items.AbstractAttack;
import model.items.IEquipableItem;

public class AttackStaff extends AbstractAttack {

    /**
     *
     * @param name
     * @param damage
     */
    protected AttackStaff(String name, int damage) {
        super(name, damage);
    }


    @Override
    public void attack(IEquipableItem item) {
        item.receiveStaffAttack(this);
    }

    @Override
    public boolean equals(Object obj){
        return obj instanceof AttackStaff && super.equals(obj);
    }
}
