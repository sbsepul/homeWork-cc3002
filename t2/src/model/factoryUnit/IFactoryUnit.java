package model.factoryUnit;

import model.items.IEquipableItem;
import model.map.Location;
import model.units.IUnit;

import java.util.List;

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
