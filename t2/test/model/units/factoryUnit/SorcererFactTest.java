package model.units.factoryUnit;

import model.map.InvalidLocation;
import model.units.Sorcerer;

public class SorcererFactTest extends AbstractFactoryUnitTest {
    private IFactoryUnit factory;

    @Override
    protected void setFactory() {
        unitCreated = new Sorcerer(50,2,new InvalidLocation());
        factory = new SorcererFactory();
    }

    @Override
    protected IFactoryUnit getFactory() {
        return factory;
    }
}
