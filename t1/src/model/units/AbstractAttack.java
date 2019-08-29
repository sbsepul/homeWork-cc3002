package model.units;

public abstract class AbstractAttack implements IAttack {
    private String name;
    private int baseDamage;

    protected AbstractAttack(String name, int damage){
        this.name = name;
        this.baseDamage = damage;
    }


    @Override
    public boolean equals(Object obj) {
        return obj instanceof IAttack && ((IAttack) obj).getBaseDamage() == baseDamage
                && ((IAttack) obj).getName().equals(name);
    }
}
