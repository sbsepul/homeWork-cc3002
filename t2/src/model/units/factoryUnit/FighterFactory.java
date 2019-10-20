package model.units.factoryUnit;

import model.items.IEquipableItem;
import model.items.factoryItem.AxeFactoryItem;
import model.map.Location;
import model.units.Fighter;

public class FighterFactory extends AbstractFactoryUnit {
    public FighterFactory(){
        super();
    }
    @Override
    public Fighter createUnit() {
        return new Fighter(super.hp, super.move, super.location, super.itemAll);
    }

    @Override
    public void addItemForDefault() {
        IEquipableItem item = new AxeFactoryItem().createItem();
        super.setItems(item);
    }
}
