package model.items.factoryItem;

public class AxeFactTest extends AbstractFactoryItemTest {
    @Override
    protected void setFactoryItem() {
        expectedName = "axe";
        factory = new AxeFactoryItem();
    }

    @Override
    protected IFactoryItem getFactoryItem() {
        return factory;
    }
}
