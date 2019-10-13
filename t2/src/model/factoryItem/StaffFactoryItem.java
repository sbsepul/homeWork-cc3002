package model.factoryItem;

import model.items.Staff;

public class StaffFactoryItem extends AbstractFactoryItem {
    /**
     * Setters the parameters for default to each items
     *
     * @param name
     */
    public StaffFactoryItem(String name) {
        super(name);
    }

    @Override
    public Staff createItem() {
        return new Staff(super.name, super.power, super.minRange, super.maxRange);
    }
}
