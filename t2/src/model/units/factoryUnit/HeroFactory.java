package model.units.factoryUnit;

import model.items.IEquipableItem;
import model.items.factoryItem.SpearFactoryItem;
import model.map.Location;
import model.units.Hero;


public class HeroFactory extends AbstractFactoryUnit {
    public HeroFactory(){
        super();
    }
    @Override
    public Hero createUnit() {
        return new Hero(super.hp, super.move, super.location, super.itemAll);
    }

    @Override
    public void addItemForDefault() {
        IEquipableItem item = new SpearFactoryItem().createItem();
        super.setItems(item);
    }
}
