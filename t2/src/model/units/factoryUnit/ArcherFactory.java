package model.units.factoryUnit;

import model.map.Location;
import model.units.Archer;

public class ArcherFactory extends AbstractFactoryUnit {
    public ArcherFactory(){
        super();
    }
    @Override
    public Archer createUnit() {
        return new Archer(super.hp, super.move, super.location, super.itemAll);
    }
}
