package model.units.factoryUnit;

import model.map.InvalidLocation;
import model.units.Hero;

public class HeroFactTest extends AbstractFactoryUnitTest {
    private IFactoryUnit factory;

    @Override
    protected void setFactory() {
        unitCreated = new Hero(50,2,new InvalidLocation());
        factory = new HeroFactory();
    }

    @Override
    protected IFactoryUnit getFactory() {
        return factory;
    }
}
