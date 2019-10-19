package model.items.factoryItem;

import model.items.Axe;

public class AxeFactoryItem extends AbstractFactoryItem {

    public AxeFactoryItem() {
        super("axe");
    }


    @Override
    public Axe createItem() {
        return new Axe(super.name, super.power, super.minRange, super.maxRange);
    }
}
