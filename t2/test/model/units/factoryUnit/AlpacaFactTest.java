package model.units.factoryUnit;

import model.map.InvalidLocation;
import model.units.Alpaca;
import model.units.IUnit;

public class AlpacaFactTest extends AbstractFactoryUnitTest {
    IFactoryUnit factory;

    @Override
    protected void setFactory() {
        unitCreated = new Alpaca(50,2,new InvalidLocation());
        factory = new AlpacaFactory();
    }

    @Override
    protected IFactoryUnit getFactory() {
        return factory;
    }
}
