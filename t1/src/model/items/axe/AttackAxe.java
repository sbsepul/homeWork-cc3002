package model.items.axe;

import model.items.AbstractAttack;
import model.items.IEquipableItem;
import model.units.IUnit;

/**
 * This class ...
 */
public class AttackAxe extends AbstractAttack {

    /**
     *
     * @param item
     * @param damage
     */
    protected AttackAxe(IEquipableItem item, int damage) {
        super(item, damage);
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
