package model.units.factoryUnit;

import model.map.Location;
import model.units.Sorcerer;

public class SorcererFactory extends AbstractFactoryUnit{
    public SorcererFactory(){
        super();
    }
    @Override
    public Sorcerer createUnit() {
        return new Sorcerer(super.hp, super.move, super.location, super.itemAll);
    }
}
