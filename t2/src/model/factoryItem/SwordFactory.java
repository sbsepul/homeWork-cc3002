package model.factoryItem;

import model.items.IEquipableItem;
import model.items.Sword;

public class SwordFactory extends AbstractItemFactory {
    /**
     * Setters the parameters for default to each items
     *
     * @param name
     */
    public SwordFactory(String name) {
        super(name);
    }

    @Override
    public Sword createItem() {
        return new Sword(super.name, super.power, super.minRange, super.maxRange);
    }
}
