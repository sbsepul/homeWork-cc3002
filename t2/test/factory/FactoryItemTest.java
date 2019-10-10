package factory;

import model.FactoryProviderItem;
import model.factoryItem.IFactoryItem;
import model.ItemType;
import model.items.*;
import model.items.Sword;
import model.items.magic.*;
import model.units.Sorcerer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class FactoryItemTest {
    private FactoryProviderItem factory;

    @BeforeEach
    public void setUp() {
        factory = new FactoryProviderItem();
    }

    @Test
    public void makeItemBow(){
        IFactoryItem bow = factory.makeItem(ItemType.BOW);
        assertEquals(bow.createItem().getClass(),Bow.class);
    }

    @Test
    public void makeItemAxe(){
        IFactoryItem axe = factory.makeItem(ItemType.AXE);
        assertEquals(axe.createItem().getClass(),Axe.class);
    }

    @Test
    public void makeItemLight(){
        IFactoryItem light = factory.makeItem(ItemType.LIGHT);
        assertEquals(light.createItem().getClass(),Light.class);
    }

    @Test
    public void makeItemDarkness(){
        IFactoryItem darkness = factory.makeItem(ItemType.DARKNESS);
        assertEquals(darkness.createItem().getClass(),Darkness.class);
    }

    @Test
    public void makeItemSoul(){
        IFactoryItem soul = factory.makeItem(ItemType.SOUL);
        assertEquals(soul.createItem().getClass(), Soul.class);
    }
    @Test
    public void makeItemSpear(){
        IFactoryItem spear = factory.makeItem(ItemType.SPEAR);
        assertEquals(spear.createItem().getClass(),Spear.class);
    }
    @Test
    public void makeItemStaff(){
        IFactoryItem staff = factory.makeItem(ItemType.STAFF);
        assertEquals(staff.createItem().getClass(),Staff.class);
    }
    @Test
    public void makeItemSword(){
        IFactoryItem sword = factory.makeItem(ItemType.SWORD);
        assertEquals(sword.createItem().getClass(),Sword.class);
    }



}
