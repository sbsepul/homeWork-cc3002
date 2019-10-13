package model.factoryItem;

import model.items.magic.Light;

public class LightFactoryItem extends AbstractFactoryItem {
    public LightFactoryItem(String name) {
        super(name);
    }

    @Override
    public Light createItem() {
        return new Light(super.name, super.power, super.minRange, super.maxRange);
    }
}
