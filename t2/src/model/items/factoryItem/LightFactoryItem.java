package model.items.factoryItem;

import model.items.magic.Light;

public class LightFactoryItem extends AbstractFactoryItem {
    public LightFactoryItem() {
        super("light");
    }
    @Override
    public Light createItem() {
        return new Light(super.name, super.power, super.minRange, super.maxRange);
    }
}
