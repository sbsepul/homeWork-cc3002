package model.factoryItem;

import model.items.Bow;

public class BowFactoryItem extends AbstractFactoryItem {
    public BowFactoryItem(String name) {
        super(name,10,2,3);
    }

    @Override
    public Bow createItem() {
        return new Bow(super.name, super.power, super.minRange,super.maxRange);
    }
}
