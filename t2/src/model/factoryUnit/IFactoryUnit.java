package model.factoryUnit;


import model.units.IUnit;

/**
 * Factory of units
 *
 * @author Sebastian Sepulveda
 * @version 2.0
 * @since 2.0
 */

public interface IFactoryUnit {
    /**
     * Create a unit
     * @return
     */
    public IUnit create();
}
