package model.factoryItem;

import model.items.Axe;
import model.items.IEquipableItem;

public class AxeFactory extends AbstractItemFactory {
    /**
     * @param name
     */
    public AxeFactory(String name) {
        super(name);
    }

    @Override
    public Axe createItem() {
        return new Axe(super.name, super.power, super.minRange, super.maxRange);
    }
}
