package model.units.factoryUnit;

import model.map.Location;
import model.units.Cleric;

public class ClericFactory extends AbstractFactoryUnit {
    public ClericFactory(){
        super();
    }
    @Override
    public Cleric createUnit() {
        return new Cleric(super.hp, super.move, super.location, super.itemAll);
    }
}
