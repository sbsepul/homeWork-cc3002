package model.factoryItem;

import model.items.IEquipableItem;
import model.items.Staff;

public class StaffFactory extends AbstractItemFactory {
    /**
     * Setters the parameters for default to each items
     *
     * @param name
     */
    public StaffFactory(String name) {
        super(name);
    }

    @Override
    public Staff createItem() {
        return new Staff(super.name, super.power, super.minRange, super.maxRange);
    }
}
