package model.items.factoryItem;

import model.items.Sword;

public class SwordFactoryItem extends AbstractFactoryItem {

    public SwordFactoryItem() {
        super("sword");
    }
    @Override
    public Sword createItem() {
        return new Sword(super.name, super.power, super.minRange, super.maxRange);
    }
}
