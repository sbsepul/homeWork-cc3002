package model;

import model.items.IEquipableItem;

public interface IFactoryItem {
    IEquipableItem makeItem(ItemType typeItem);
}
