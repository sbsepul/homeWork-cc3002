package model.items.factoryItem;

import model.items.Axe;

public class AxeFactoryItem extends AbstractFactoryItem {
    /**
     * @param name
     */
    public AxeFactoryItem(String name) {
        super(name);
    }

    public AxeFactoryItem(String name, int power){ super(name,power); }

    @Override
    public Axe createItem() {
        return new Axe(super.name, super.power, super.minRange, super.maxRange);
    }
}
