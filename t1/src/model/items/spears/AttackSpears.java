package model.items.spears;

import model.items.AbstractAttack;
import model.items.IEquipableItem;

public class AttackSpears extends AbstractAttack {
    /**
     *
     * @param name
     * @param damage
     */
    protected AttackSpears(IEquipableItem name, int damage) {
        super(name, damage);
    }


    @Override
    public void attack(IEquipableItem item) {
        item.receiveSpearsAttack(this);
    }
    @Override
    public boolean equals(Object obj){
        return obj instanceof AttackSpears && super.equals(obj);
    }
}
