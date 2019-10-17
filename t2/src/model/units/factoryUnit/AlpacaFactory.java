package model.units.factoryUnit;

import model.map.Location;
import model.units.Alpaca;


public class AlpacaFactory extends AbstractFactoryUnit {
    public AlpacaFactory(){
        super();
    }
    public AlpacaFactory(int hitPoints, int movement, int hp) {
        super(hitPoints,movement);
    }
    public AlpacaFactory(int hitPoints, int movement, Location location){
        super(hitPoints,movement,location);
    }
    @Override
    public Alpaca createUnit() {
        return new Alpaca(super.hp, super.move, super.location, super.itemAll);
    }


}
