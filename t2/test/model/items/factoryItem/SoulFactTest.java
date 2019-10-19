package model.items.factoryItem;

public class SoulFactTest extends AbstractFactoryItemTest {
    @Override
    protected void setFactoryItem() {
        expectedName = "soul";
        factory = new SoulFactoryItem();
    }

    @Override
    protected IFactoryItem getFactoryItem() {
        return factory;
    }
}
