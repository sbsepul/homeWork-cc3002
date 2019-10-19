package model.items.factoryItem;

public class StaffFactTest extends AbstractFactoryItemTest {
    @Override
    protected void setFactoryItem() {
        expectedName = "staff";
        factory = new StaffFactoryItem();
    }

    @Override
    protected IFactoryItem getFactoryItem() {
        return factory;
    }
}
