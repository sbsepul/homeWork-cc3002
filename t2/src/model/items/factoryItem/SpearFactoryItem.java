package model.items.factoryItem;

import model.items.Spear;

public class SpearFactoryItem extends AbstractFactoryItem {

    public SpearFactoryItem() {
        super("spear");
    }
    @Override
    public Spear createItem() {
        return new Spear(super.name, super.power, super.minRange, super.maxRange);
    }
}
