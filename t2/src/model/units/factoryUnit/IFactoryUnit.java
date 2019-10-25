package model.units.factoryUnit;

import model.items.IEquipableItem;
import model.map.Location;
import model.units.IUnit;


/***
 *
 *
 * @author Sebastian Sepulveda
 * @version 1.0
 * @since 2.0
 *
 */
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
