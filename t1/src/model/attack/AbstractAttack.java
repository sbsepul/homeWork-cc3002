package model.attack;

import model.units.AbstractUnit;

public abstract class AbstractAttack implements IAttack{
    private String name;
    private int damage;

    protected AbstractAttack(String name, int damage){
        this.name = name;
        this.damage = damage;
    }
}
