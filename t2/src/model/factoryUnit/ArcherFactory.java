package model.factoryUnit;

import model.map.Location;
import model.units.Archer;

public class ArcherFactory extends AbstractFactoryUnit {
    public ArcherFactory(){
        super();
    }
    public ArcherFactory(int hitPoints, int movement, int hp) {
        super(hitPoints,movement);
    }
    public ArcherFactory(int hitPoints, int movement, Location location){
        super(hitPoints,movement,location);
    }
    @Override
    public Archer createUnit() {
        return new Archer(super.hp, super.move, super.location, super.itemAll);
    }
}
