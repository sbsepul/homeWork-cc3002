package model.items.sword;

import model.items.AbstractAttack;
import model.items.IEquipableItem;
import model.units.IUnit;

public class AttackSword extends AbstractAttack {
    /**
     *
     * @param name
     * @param damage
     */
    protected AttackSword(IEquipableItem name, int damage) {
        super(name, damage);
    }

    @Override
    public void attack(IEquipableItem item) {
        item.receiveSwordsAttack(this);
    }

    @Override
    public boolean equals(Object obj){
        return obj instanceof AttackSword && super.equals(obj);
    }

}
