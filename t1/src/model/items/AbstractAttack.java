package model.items;

import model.items.IAttack;

public abstract class AbstractAttack implements IAttack {
    private IEquipableItem nameItem ;
    private int baseDamage;

    /**
     *
     * @param item
     *      The attack's name
     * @param damage
     *      The damage's points
     */
    protected AbstractAttack(IEquipableItem item, int damage){
        this.nameItem = item;
        this.baseDamage = damage;
    }

    @Override
    public int getBaseDamage() {
        return baseDamage;
    }

    @Override
    public IEquipableItem getNameItem() {
        return nameItem;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof IAttack && ((IAttack) obj).getBaseDamage() == baseDamage
                && ((IAttack) obj).getNameItem().equals(nameItem);
    }
}
