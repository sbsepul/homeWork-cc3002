package model;

import model.units.*;

/**
 * Types of units
 *
 * @author Sebastian Sepulveda
 * @version 2.0
 * @since 2.0
 */
public class FactoryUnit implements IFactoryUnit {
    @Override
    public IUnit makeUnit(UnitType unitType){
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

