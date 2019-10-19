package model.units.factoryUnit;

import model.map.InvalidLocation;
import model.units.Fighter;

public class FighterFactTest extends AbstractFactoryUnitTest {
    private IFactoryUnit factory;

    @Override
    protected void setFactory() {
        unitCreated = new Fighter(50,2,new InvalidLocation());
        factory = new FighterFactory();
    }

    @Override
    protected IFactoryUnit getFactory() {
        return factory;
    }
}
