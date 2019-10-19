package model.items.factoryItem;

import model.items.IEquipableItem;

public interface IFactoryItem {
    public IEquipableItem createItem();

    String getName();

    int getMaxRange();

    int getMinRange();

    int getPower();

    void setMaxRange(int maxRange);

    void setMinRange(int minRange);

    void setName(String name);

    void setPower(int power);
}
