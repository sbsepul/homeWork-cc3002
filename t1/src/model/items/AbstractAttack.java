package model.items;

import model.items.IAttack;

public abstract class AbstractAttack implements IAttack {
    private String name;
    private int baseDamage;

    /**
     *
     * @param name
     * The attack's name
     * @param damage
     * The damage's points
     */
    protected AbstractAttack(String name, int damage){
        this.name = name;
        this.baseDamage = damage;
    }

    @Override
    public int getBaseDamage() {
        return baseDamage;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof IAttack && ((IAttack) obj).getBaseDamage() == baseDamage
                && ((IAttack) obj).getName().equals(name);
    }
}
