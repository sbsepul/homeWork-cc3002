package model.factoryUnit;

import model.items.IEquipableItem;
import model.map.Location;
import model.units.SwordMaster;

public class SwordMasterFactory extends AbstractFactoryUnit {
    public SwordMasterFactory(){
        super();
    }
    public SwordMasterFactory(int hitPoints, int movement, int hp) {
        super(hitPoints,movement);
    }
    public SwordMasterFactory(int hitPoints, int movement, Location location){
        super(hitPoints,movement,location);
    }
    @Override
    public SwordMaster createUnit() {
        return new SwordMaster(super.hp, super.move, super.location, super.itemAll);
    }
}
