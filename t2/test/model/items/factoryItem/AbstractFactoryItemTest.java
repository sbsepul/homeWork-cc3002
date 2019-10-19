package model.items.factoryItem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

abstract class AbstractFactoryItemTest {
    protected IFactoryItem factory;
    protected String expectedName;
    protected int expectedPower;
    protected int expectedMinRange;
    protected int expectedMaxRange;

    @BeforeEach
    public void setUp(){
        setFactoryItem();
        expectedPower = 10;
        expectedMinRange = 1;
        expectedMaxRange = 2;
    }

    public IFactoryItem getFactory() {
        return factory;
    }

    public int getExpectedMaxRange() {
        return expectedMaxRange;
    }

    public int getExpectedMinRange() {
        return expectedMinRange;
    }

    public int getExpectedPower() {
        return expectedPower;
    }

    public String getExpectedName() {
        return expectedName;
    }

    protected abstract void setFactoryItem();

    protected abstract IFactoryItem getFactoryItem();

    @Test
    public void constructorTest(){
        assertEquals(getExpectedName(), getFactory().getName());
        assertEquals(getExpectedMaxRange(), getFactory().getMaxRange());
        assertEquals(getExpectedMinRange(),getFactory().getMinRange());
        assertEquals(getExpectedPower(),getFactory().getPower());
    }

    @Test
    public void setterTest(){

    }


}