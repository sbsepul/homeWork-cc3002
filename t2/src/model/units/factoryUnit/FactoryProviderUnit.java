package model.units.factoryUnit;

import java.util.List;

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

    public List<IFactoryUnit> createUnitPack(){
        //factoryUnit.makeUnit(UnitType.HERO)
        return List.of(
                this.makeUnit(UnitType.ALPACA),
                this.makeUnit(UnitType.ARCHER),
                this.makeUnit(UnitType.CLERIC),
                this.makeUnit(UnitType.FIGHTER),
                this.makeUnit(UnitType.SORCERER),
                this.makeUnit(UnitType.SWORDMASTER)
        );
    }
}

