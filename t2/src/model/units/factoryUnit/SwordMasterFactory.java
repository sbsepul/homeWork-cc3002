package model.units.factoryUnit;

import model.map.Location;
import model.units.SwordMaster;

public class SwordMasterFactory extends AbstractFactoryUnit {
    public SwordMasterFactory(){
        super();
    }
    @Override
    public SwordMaster createUnit() {
        return new SwordMaster(super.hp, super.move, super.location, super.itemAll);
    }
}
