package model.items.factoryItem;

import model.items.Bow;

public class BowFactoryItem extends AbstractFactoryItem {
    public BowFactoryItem(String name) {
        super(name,10,2,3);
    }
    public BowFactoryItem(String name, int power){ super(name,power); }
    @Override
    public Bow createItem() {
        return new Bow(super.name, super.power, super.minRange,super.maxRange);
    }
}
