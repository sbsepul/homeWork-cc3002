package model.factoryItem;

import model.items.IEquipableItem;
import model.items.Spear;

public class SpearFactory extends AbstractItemFactory {
    /**
     * Setters the parameters for default to each items
     *
     * @param name
     */
    public SpearFactory(String name) {
        super(name);
    }

    @Override
    public Spear createItem() {
        return new Spear(super.name, super.power, super.minRange, super.maxRange);
    }
}
