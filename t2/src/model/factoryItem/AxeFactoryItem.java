package model.factoryItem;

import model.items.Axe;

public class AxeFactoryItem extends AbstractFactoryItem {
    /**
     * @param name
     */
    public AxeFactoryItem(String name) {
        super(name);
    }

    @Override
    public Axe createItem() {
        return new Axe(super.name, super.power, super.minRange, super.maxRange);
    }
}
