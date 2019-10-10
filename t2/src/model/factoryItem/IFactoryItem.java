package model.factoryItem;

import model.ItemType;
import model.items.IEquipableItem;

public interface IFactoryItem {
    public IEquipableItem createItem();
}
