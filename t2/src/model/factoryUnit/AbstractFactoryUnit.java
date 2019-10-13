package model.factoryUnit;

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

    public AbstractFactoryUnit(int hitPoints, int movement, Location location, IEquipableItem... itemsAll) {
        this.hp = hitPoints;
        this.move = movement;
        this.location = location;
        this.itemAll = itemsAll;
    }

    public AbstractFactoryUnit(int hitPoints, int movement) {
        this.hp = hitPoints;
        this.move = movement;
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
