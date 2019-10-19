package model.items.factoryItem;

import model.items.Staff;

public class StaffFactoryItem extends AbstractFactoryItem {

    public StaffFactoryItem() {
        super("staff");
    }
    @Override
    public Staff createItem() {
        return new Staff(super.name, super.power, super.minRange, super.maxRange);
    }
}
