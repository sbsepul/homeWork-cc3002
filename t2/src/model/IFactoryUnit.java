package model;

import model.units.IUnit;

enum UnitType {
    ALPACA, ARCHER, CLERIC, FIGHTER, HERO, SORCERER, SWORDMASTER;
}

public interface IFactoryUnit {
    public IUnit makeFactory(UnitType unitType);
}
