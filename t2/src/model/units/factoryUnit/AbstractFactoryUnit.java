package model.units.factoryUnit;

import model.items.IEquipableItem;
import model.map.InvalidLocation;
import model.map.Location;

public abstract class AbstractFactoryUnit implements IFactoryUnit {
    protected final int hp;
    protected final int move;
    protected Location location = new InvalidLocation();
    protected IEquipableItem[] itemAll = new IEquipableItem[0];

    public AbstractFactoryUnit(){
        this.hp = 50;
        this.move = 2;
    }

    @Override
    public int getHp() {
        return hp;
    }

    @Override
    public int getMove() {
        return move;
    }

    @Override
    public IEquipableItem[] getItemAll() {
        return itemAll;
    }

    @Override
    public Location getLocation() {
        return location;
    }


    @Override
    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public void setItems(IEquipableItem... item) {
        this.itemAll = item;
    }
}
