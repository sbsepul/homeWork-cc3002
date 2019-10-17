package model.items.factoryItem;

import model.items.*;
import model.items.magic.Darkness;
import model.items.magic.Light;
import model.items.magic.Soul;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FactoryItemProviderTest {
    private FactoryItemProvider factory;

    @BeforeEach
    public void setUp() {
        factory = new FactoryItemProvider();
    }

    @Test
    public void makeItemBow(){
        IFactoryItem bow = factory.makeItem(ItemType.BOW);
        assertEquals(bow.createItem().getClass(), Bow.class);
    }

    @Test
    public void makeItemAxe(){
        IFactoryItem axe = factory.makeItem(ItemType.AXE);
        assertEquals(axe.createItem().getClass(), Axe.class);
    }

    @Test
    public void makeItemLight(){
        IFactoryItem light = factory.makeItem(ItemType.LIGHT);
        assertEquals(light.createItem().getClass(), Light.class);
    }

    @Test
    public void makeItemDarkness(){
        IFactoryItem darkness = factory.makeItem(ItemType.DARKNESS);
        assertEquals(darkness.createItem().getClass(), Darkness.class);
    }

    @Test
    public void makeItemSoul(){
        IFactoryItem soul = factory.makeItem(ItemType.SOUL);
        assertEquals(soul.createItem().getClass(), Soul.class);
    }
    @Test
    public void makeItemSpear(){
        IFactoryItem spear = factory.makeItem(ItemType.SPEAR);
        assertEquals(spear.createItem().getClass(), Spear.class);
    }
    @Test
    public void makeItemStaff(){
        IFactoryItem staff = factory.makeItem(ItemType.STAFF);
        assertEquals(staff.createItem().getClass(), Staff.class);
    }
    @Test
    public void makeItemSword(){
        IFactoryItem sword = factory.makeItem(ItemType.SWORD);
        assertEquals(sword.createItem().getClass(), Sword.class);
    }
    @Test
    public void illegalItemArgument() throws IllegalArgumentException{
        boolean thrown = false;
        try {
            IFactoryItem aItem = factory.makeItem(ItemType.OTHER);
        } catch (IllegalArgumentException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }
}