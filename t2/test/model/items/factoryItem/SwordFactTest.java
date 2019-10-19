package model.items.factoryItem;

public class SwordFactTest extends AbstractFactoryItemTest{
    @Override
    protected void setFactoryItem() {
        expectedName = "sword";
        factory = new SwordFactoryItem();
    }

    @Override
    protected IFactoryItem getFactoryItem() {
        return factory;
    }
}
