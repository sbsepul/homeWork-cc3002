package model.factoryUnit;

import model.items.IEquipableItem;
import model.map.Location;
import model.units.Hero;


public class HeroFactory extends AbstractFactoryUnit {
    public HeroFactory(){
        super();
    }
    public HeroFactory(int hitPoints, int movement, int hp) {
        super(hitPoints,movement);
    }
    public HeroFactory(int hitPoints, int movement, Location location){
        super(hitPoints,movement,location);
    }
    @Override
    public Hero createUnit() {
        return new Hero(super.hp, super.move, super.location, super.itemAll);
    }
}
