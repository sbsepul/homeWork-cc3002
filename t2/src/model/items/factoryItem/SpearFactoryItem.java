package model.items.factoryItem;

import model.items.Spear;

public class SpearFactoryItem extends AbstractFactoryItem {
    /**
     * Setters the parameters for default to each items
     *
     * @param name
     */
    public SpearFactoryItem(String name) {
        super(name);
    }
    public SpearFactoryItem(String name, int power){ super(name,power); }
    @Override
    public Spear createItem() {
        return new Spear(super.name, super.power, super.minRange, super.maxRange);
    }
}
