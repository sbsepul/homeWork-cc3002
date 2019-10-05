package model.factoryUnit;

import model.units.Cleric;
import model.units.IUnit;

public class ClericFactory implements IFactoryUnit {
    @Override
    public Cleric create() {
        return new Cleric(50,2,null);
    }
}
