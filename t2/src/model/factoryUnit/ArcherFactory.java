package model.factoryUnit;

import model.units.Archer;
import model.units.IUnit;

public class ArcherFactory implements IFactoryUnit{
    @Override
    public Archer create() {
        return new Archer(50,2,null);
    }
}
