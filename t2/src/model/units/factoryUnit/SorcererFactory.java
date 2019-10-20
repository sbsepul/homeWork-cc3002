package model.units.factoryUnit;

import model.items.IEquipableItem;
import model.items.factoryItem.DarknessFactoryItem;
import model.items.magic.Darkness;
import model.map.Location;
import model.units.Sorcerer;

public class SorcererFactory extends AbstractFactoryUnit{
    public SorcererFactory(){
        super();
    }
    @Override
    public Sorcerer createUnit() {
        return new Sorcerer(super.hp, super.move, super.location, super.itemAll);
    }

    @Override
    public void addItemForDefault() {
        IEquipableItem item = new DarknessFactoryItem().createItem();
        super.setItems(item);
    }
}
