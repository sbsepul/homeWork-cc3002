package model.items.factoryItem;

import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

abstract class AbstractFactoryItemTest {
    private IFactoryItem factory;

    @BeforeEach
    public void setUp(){
        setFactoryItem();
    }

    protected abstract void setFactoryItem();

}