package model.items.factoryItem;

import model.items.Sword;

public class SwordFactoryItem extends AbstractFactoryItem {
    /**
     * Setters the parameters for default to each items
     *
     * @param name
     */
    public SwordFactoryItem(String name) {
        super(name);
    }
    public SwordFactoryItem(String name, int power){ super(name,power); }
    @Override
    public Sword createItem() {
        return new Sword(super.name, super.power, super.minRange, super.maxRange);
    }
}
