package model.items.factoryItem;

import model.items.magic.Soul;

public class SoulFactoryItem extends AbstractFactoryItem {
    public SoulFactoryItem(String name) {
        super(name);
    }
    public SoulFactoryItem(String name, int power){ super(name,power); }
    @Override
    public Soul createItem() {
        return new Soul(super.name, super.power, super.minRange, super.maxRange);
    }
}
