package model.units.factoryUnit;

import model.map.InvalidLocation;
import model.units.Cleric;

public class ClericFactTest extends AbstractFactoryUnitTest {
    private IFactoryUnit factory;

    @Override
    protected void setFactory() {
        unitCreated = new Cleric(50,2,new InvalidLocation());
        factory = new ClericFactory();
    }
    @Override
    protected IFactoryUnit getFactory() {
        return factory;
    }
}
