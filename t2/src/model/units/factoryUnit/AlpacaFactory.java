package model.units.factoryUnit;

import model.map.Location;
import model.units.Alpaca;


public class AlpacaFactory extends AbstractFactoryUnit {
    public AlpacaFactory(){
        super();
    }
    @Override
    public Alpaca createUnit() {
        return new Alpaca(super.hp, super.move, super.location, super.itemAll);
    }
}
