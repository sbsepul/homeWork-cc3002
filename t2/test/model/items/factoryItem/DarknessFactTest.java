package model.items.factoryItem;

public class DarknessFactTest extends AbstractFactoryItemTest {
    @Override
    protected void setFactoryItem() {
        expectedName = "darkness";
        factory = new DarknessFactoryItem();
    }

    @Override
    protected IFactoryItem getFactoryItem() {
        return factory;
    }
}
