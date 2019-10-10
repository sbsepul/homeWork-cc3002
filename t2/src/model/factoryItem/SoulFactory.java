package model.factoryItem;

import model.items.IEquipableItem;
import model.items.magic.Soul;

public class SoulFactory extends AbstractItemFactory {
    public SoulFactory(String name) {
        super(name);
    }

    @Override
    public Soul createItem() {
        return new Soul(super.name, super.power, super.minRange, super.maxRange);
    }
}
