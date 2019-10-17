package model.items.factoryItem;

import model.items.magic.Light;

public class LightFactoryItem extends AbstractFactoryItem {
    public LightFactoryItem(String name) {
        super(name);
    }
    public LightFactoryItem(String name, int power){ super(name,power); }
    @Override
    public Light createItem() {
        return new Light(super.name, super.power, super.minRange, super.maxRange);
    }
}
