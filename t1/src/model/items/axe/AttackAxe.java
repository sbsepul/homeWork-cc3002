package model.items.axe;

import model.items.AbstractAttack;
import model.items.IEquipableItem;

/**
 * This class ...
 */
public class AttackAxe extends AbstractAttack {

    /**
     *
     * @param name
     * @param damage
     */
    protected AttackAxe(String name, int damage) {
        super(name, damage);
    }

    @Override
    public void attack(IEquipableItem item) {
        item.receiveAxeAttack(this);
    }

    @Override
    public boolean equals(Object obj){
        return obj instanceof AttackAxe && super.equals(obj);
    }
}
