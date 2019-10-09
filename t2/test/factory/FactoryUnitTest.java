package factory;

import model.FactoryUnit;
import model.UnitType;
import model.units.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class FactoryUnitTest {
    private FactoryUnit factory;

    @BeforeEach
    public void setUp() {
        factory = new FactoryUnit();
    }

    @Test
    public void makeUnitAlpaca(){
        IUnit alpaca = factory.makeUnit(UnitType.ALPACA);
        assertTrue(alpaca instanceof Alpaca);
    }

    @Test
    public void makeUnitArcher(){
        IUnit archer = factory.makeUnit(UnitType.ARCHER);
        assertTrue(archer instanceof Archer);
    }

    @Test
    public void makeUnitCleric(){
        IUnit cleric = factory.makeUnit(UnitType.CLERIC);
        assertTrue(cleric instanceof Cleric);
    }

    @Test
    public void makeUnitFighter(){
        IUnit fighter = factory.makeUnit(UnitType.FIGHTER);
        assertTrue(fighter instanceof Fighter);
    }

    @Test
    public void makeUnitHero(){
        IUnit hero = factory.makeUnit(UnitType.HERO);
        assertTrue(hero instanceof Hero);
    }
    @Test
    public void makeUnitSorcerer(){
        IUnit sorcerer = factory.makeUnit(UnitType.SORCERER);
        assertTrue(sorcerer instanceof Sorcerer);
    }
    @Test
    public void makeUnitSwordMaster(){
        IUnit swordmaster = factory.makeUnit(UnitType.SWORDMASTER);
        assertTrue(swordmaster instanceof SwordMaster);
    }
}
