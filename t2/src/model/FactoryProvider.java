package model;

import model.factoryUnit.IFactoryUnit;
import model.units.*;

/**
 * Types of units
 *
 * @author Sebastian Sepulveda
 * @version 2.0
 * @since 2.0
 */
public class FactoryProvider{

    public IUnit createUnit(UnitType unitType){
        switch (unitType){
            case HERO:
                return new Hero(50,2,null);
            case ALPACA:
                return new Alpaca(50, 2,null);
            case SWORDMASTER:
                return new SwordMaster(50, 2,null);
            case ARCHER:
                return new Archer(50, 2,null);
            case CLERIC:
                return new Cleric(50, 2, null);
            case FIGHTER:
                return new Fighter(50, 2 ,null);
            case SORCERER:
                return new Sorcerer(50, 2, null);
            default:
                throw new IllegalArgumentException("Unit not supported");
        }
    }
}

