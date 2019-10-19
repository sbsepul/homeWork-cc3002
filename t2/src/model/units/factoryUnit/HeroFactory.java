package model.units.factoryUnit;

import model.map.Location;
import model.units.Hero;


public class HeroFactory extends AbstractFactoryUnit {
    public HeroFactory(){
        super();
    }
    @Override
    public Hero createUnit() {
        return new Hero(super.hp, super.move, super.location, super.itemAll);
    }
}
