package model.units.factoryUnit;

import model.items.IEquipableItem;
import model.items.factoryItem.AxeFactoryItem;
import model.items.factoryItem.IFactoryItem;
import model.map.InvalidLocation;
import model.map.Location;
import model.units.Archer;
import model.units.IUnit;

public class ArcherFactTest extends AbstractFactoryUnitTest {
    private IFactoryUnit factory;

    @Override
    protected void setFactory() {
        unitCreated = new Archer(50,2, new InvalidLocation());
        factory = new ArcherFactory();
    }

    @Override
    protected IFactoryUnit getFactory() {
        return factory;
    }

}
