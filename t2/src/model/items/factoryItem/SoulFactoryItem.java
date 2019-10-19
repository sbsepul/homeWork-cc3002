package model.items.factoryItem;

import model.items.magic.Soul;

public class SoulFactoryItem extends AbstractFactoryItem {
    public SoulFactoryItem() {
        super("soul");
    }
    @Override
    public Soul createItem() {
        return new Soul(super.name, super.power, super.minRange, super.maxRange);
    }
}
