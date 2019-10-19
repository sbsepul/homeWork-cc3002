package model.items.factoryItem;

import model.items.magic.Darkness;

public class DarknessFactoryItem extends AbstractFactoryItem {
    public DarknessFactoryItem() {
        super("darkness");
    }
    @Override
    public Darkness createItem() {
        return new Darkness(super.name, super.power, super.minRange, super.maxRange);
    }
}
