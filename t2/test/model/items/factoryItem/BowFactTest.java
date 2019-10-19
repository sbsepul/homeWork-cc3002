package model.items.factoryItem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BowFactTest extends AbstractFactoryItemTest {
    @Override
    protected void setFactoryItem() {
        expectedName = "bow";
        expectedMaxRange = 3;
        expectedMinRange = 2;
        factory = new BowFactoryItem();
    }

    @Override
    protected IFactoryItem getFactoryItem() {
        return factory;
    }

    @Test
    @Override
    public void constructorTest(){
        assertEquals(getExpectedName(), getFactory().getName());
        assertEquals(3, getFactory().getMaxRange());
        assertEquals(2,getFactory().getMinRange());
        assertEquals(getExpectedPower(),getFactory().getPower());
    }

}
