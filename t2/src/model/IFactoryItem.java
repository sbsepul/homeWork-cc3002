package model;

import model.items.IEquipableItem;

enum ItemType{
    AXE, BOW, SPEAR, STAFF, SWORD, DARKNESS, LIGHT, SOUL
}

public interface IFactoryItem {
    IEquipableItem makeFactory(ItemType typeItem);
}
