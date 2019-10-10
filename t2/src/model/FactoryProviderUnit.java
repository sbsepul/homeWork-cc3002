package model;

import model.factoryUnit.*;
import model.units.*;

/**
 * Types of units
 *
 * @author Sebastian Sepulveda
 * @version 2.0
 * @since 2.0
 */
public class FactoryProviderUnit {

    public IFactoryUnit makeUnit(UnitType unitType){
        switch (unitType){
            case HERO:
                return new HeroFactory();
            case ALPACA:
                return new AlpacaFactory();
            case SWORDMASTER:
                return new SwordMasterFactory();
            case ARCHER:
                return new ArcherFactory();
            case CLERIC:
                return new ClericFactory();
            case FIGHTER:
                return new FighterFactory();
            case SORCERER:
                return new SorcererFactory();
            default:
                throw new IllegalArgumentException("Unit not supported");
        }
    }
}

