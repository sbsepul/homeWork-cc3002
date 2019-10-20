package model.units.factoryUnit;

import model.items.IEquipableItem;
import model.map.Location;
import model.units.IUnit;

public interface IFactoryUnit {
    /**
     *
     * @return
     */
    public IUnit createUnit();

    public int getHp();

    public int getMove();

    public IEquipableItem[] getItemAll();

    public Location getLocation();
    /**
     *
     * @param location
     */
    public void setLocation(Location location);

    /**
     *
     * @param items
     */
    public void setItems(IEquipableItem... items);

    public void addItemForDefault();

}
