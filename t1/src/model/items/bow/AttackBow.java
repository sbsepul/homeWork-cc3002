package model.items.bow;

import model.items.AbstractAttack;
import model.items.IEquipableItem;

public class AttackBow extends AbstractAttack {
    /**
     * Creates a new attack.
     *
     * @param name
     *     Attack name
     * @param baseDamage
     *     Base damage of the attack
     */
    public AttackBow(String name, int baseDamage) { super(name, baseDamage); }

    @Override
    public void attack(IEquipableItem item) {
        item.receiveBowAttack(this);
    }
    @Override
    public boolean equals(Object obj){
        return obj instanceof AttackBow && super.equals(obj);
    }
}
