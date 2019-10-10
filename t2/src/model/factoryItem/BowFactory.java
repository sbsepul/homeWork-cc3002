package model.factoryItem;

import model.items.Bow;
import model.items.IEquipableItem;

public class BowFactory extends AbstractItemFactory {
    public BowFactory(String name, int power, int minRange, int maxRange) {
        super(name,power,minRange,maxRange);
    }

    @Override
    public Bow createItem() {
        return new Bow(super.name, super.power, super.minRange, super.maxRange);
    }
}
