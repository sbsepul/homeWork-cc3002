package model.units.factoryUnit;

import model.items.IEquipableItem;
import model.items.factoryItem.BowFactoryItem;
import model.items.factoryItem.ItemType;
import model.map.Location;
import model.units.Archer;

public class ArcherFactory extends AbstractFactoryUnit {
    public ArcherFactory(){
        super();
    }
    @Override
    public Archer createUnit() {
        return new Archer(super.hp, super.move, super.location, super.itemAll);
    }

    @Override
    public void addItemForDefault() {
        IEquipableItem item = new BowFactoryItem().createItem();
        super.setItems(item);
    }
}
