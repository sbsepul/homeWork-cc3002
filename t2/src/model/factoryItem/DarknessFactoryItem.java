package model.factoryItem;

import model.items.magic.Darkness;

public class DarknessFactoryItem extends AbstractFactoryItem {
    public DarknessFactoryItem(String name) {
        super(name);
    }

    @Override
    public Darkness createItem() {
        return new Darkness(super.name, super.power, super.minRange, super.maxRange);
    }
}
