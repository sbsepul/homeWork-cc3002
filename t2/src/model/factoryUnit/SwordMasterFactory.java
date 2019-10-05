package model.factoryUnit;

import model.items.Sword;
import model.units.IUnit;
import model.units.SwordMaster;

public class SwordMasterFactory implements IFactoryUnit {
    @Override
    public SwordMaster create() {
        return new SwordMaster(50,2,null);
    }
}
