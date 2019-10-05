package model.factoryUnit;

import model.units.IUnit;
import model.units.Sorcerer;

public class SorcererFactory implements IFactoryUnit {
    @Override
    public Sorcerer create() {
        return new Sorcerer(50,2,null);
    }
}
