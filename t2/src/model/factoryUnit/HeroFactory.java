package model.factoryUnit;

import model.units.Hero;
import model.units.IUnit;

public class HeroFactory implements IFactoryUnit {
    @Override
    public Hero create() {
        return new Hero(50,2,null);
    }
}
