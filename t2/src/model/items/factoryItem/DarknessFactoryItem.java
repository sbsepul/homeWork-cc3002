package model.items.factoryItem;

import model.items.magic.Darkness;

public class DarknessFactoryItem extends AbstractFactoryItem {
    public DarknessFactoryItem(String name) {
        super(name);
    }
    public DarknessFactoryItem(String name, int power){ super(name,power); }
    @Override
    public Darkness createItem() {
        return new Darkness(super.name, super.power, super.minRange, super.maxRange);
    }
}
