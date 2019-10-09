package model;

import model.units.IUnit;

public interface IFactoryUnit {
    /**
     *
     * @param unitType
     * @return
     */
    public IUnit makeUnit(UnitType unitType);
}
