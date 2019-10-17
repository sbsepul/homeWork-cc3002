package model.map.factoryMap;

import model.map.Field;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FactoryMapTest {
    IFactoryMap factory;

    @BeforeEach
    void setUp() {
        factory = new FactoryMap(7);
    }

    @Test
    void createMap() {
        assertEquals(factory.createMap().getClass(),Field.class);
    }
}