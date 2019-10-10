package model.factoryUnit;

import model.map.Location;
import model.units.Sorcerer;

public class SorcererFactory extends AbstractFactoryUnit{
    public SorcererFactory(){
        super();
    }
    public SorcererFactory(int hitPoints, int movement, int hp) {
        super(hitPoints,movement);
    }
    public SorcererFactory(int hitPoints, int movement, Location location){
        super(hitPoints,movement,location);
    }
    @Override
    public Sorcerer createUnit() {
        return new Sorcerer(super.hp, super.move, super.location, super.itemAll);
    }
}
