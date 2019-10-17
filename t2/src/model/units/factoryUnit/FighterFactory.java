package model.units.factoryUnit;

import model.map.Location;
import model.units.Fighter;

public class FighterFactory extends AbstractFactoryUnit {
    public FighterFactory(){
        super();
    }
    public FighterFactory(int hitPoints, int movement, int hp) {
        super(hitPoints,movement);
    }
    public FighterFactory(int hitPoints, int movement, Location location){
        super(hitPoints,movement,location);
    }
    @Override
    public Fighter createUnit() {
        return new Fighter(super.hp, super.move, super.location, super.itemAll);
    }
}
