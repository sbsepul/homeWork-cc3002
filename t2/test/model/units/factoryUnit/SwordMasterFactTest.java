package model.units.factoryUnit;

import model.items.IEquipableItem;
import model.map.InvalidLocation;
import model.units.SwordMaster;

public class SwordMasterFactTest extends AbstractFactoryUnitTest {
    private IFactoryUnit factory;

    @Override
    protected void setFactory() {
        unitCreated = new SwordMaster(50,2, new InvalidLocation());
        factory = new SwordMasterFactory();
    }

    @Override
    protected IFactoryUnit getFactory() {
        return factory;
    }
}
