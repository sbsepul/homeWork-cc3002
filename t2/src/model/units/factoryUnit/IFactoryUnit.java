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

}
