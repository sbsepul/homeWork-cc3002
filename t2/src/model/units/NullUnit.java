package model.units;


import model.items.IEquipableItem;
import model.map.InvalidLocation;
import model.map.Location;

public class NullUnit extends AbstractUnit {
    public NullUnit() {
        super(0, 0, new InvalidLocation(), 0);
    }

    @Override
    public boolean isNull() {
        return true;
    }
}
