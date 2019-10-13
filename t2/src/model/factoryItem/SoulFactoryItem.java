package model.factoryItem;

import model.items.magic.Soul;

public class SoulFactoryItem extends AbstractFactoryItem {
    public SoulFactoryItem(String name) {
        super(name);
    }

    @Override
    public Soul createItem() {
        return new Soul(super.name, super.power, super.minRange, super.maxRange);
    }
}
