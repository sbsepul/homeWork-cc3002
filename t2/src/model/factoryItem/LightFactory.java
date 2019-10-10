package model.factoryItem;

import model.items.IEquipableItem;
import model.items.magic.Light;

public class LightFactory extends AbstractItemFactory {
    public LightFactory(String name) {
        super(name);
    }

    @Override
    public Light createItem() {
        return new Light(super.name, super.power, super.minRange, super.maxRange);
    }
}
