package model.factoryItem;

import model.items.IEquipableItem;
import model.items.magic.Darkness;

public class DarknessFactory extends AbstractItemFactory {
    public DarknessFactory(String name) {
        super(name);
    }

    @Override
    public Darkness createItem() {
        return new Darkness(super.name, super.power, super.minRange, super.maxRange);
    }
}
