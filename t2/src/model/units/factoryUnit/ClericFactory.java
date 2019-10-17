package model.units.factoryUnit;

import model.map.Location;
import model.units.Cleric;

public class ClericFactory extends AbstractFactoryUnit {
    public ClericFactory(){
        super();
    }
    public ClericFactory(int hitPoints, int movement, int hp) {
        super(hitPoints,movement);
    }
    public ClericFactory(int hitPoints, int movement, Location location){
        super(hitPoints,movement,location);
    }
    @Override
    public Cleric createUnit() {
        return new Cleric(super.hp, super.move, super.location, super.itemAll);
    }
}
