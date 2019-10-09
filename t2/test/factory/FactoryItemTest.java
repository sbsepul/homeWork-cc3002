package factory;

import model.FactoryItem;
import model.IFactoryItem;
import model.ItemType;
import model.items.*;
import model.items.IEquipableItem;
import model.items.Sword;
import model.items.magic.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class FactoryItemTest {
    private IFactoryItem factory;

    @BeforeEach
    public void setUp() {
        factory = new FactoryItem();
    }

    @Test
    public void makeItemBow(){
        IEquipableItem bow = factory.makeItem(ItemType.BOW);
        assertTrue(bow instanceof Bow);
    }

    @Test
    public void makeItemAxe(){
        IEquipableItem axe = factory.makeItem(ItemType.AXE);
        assertTrue(axe instanceof Axe);
    }

    @Test
    public void makeItemLight(){
        IEquipableItem light = factory.makeItem(ItemType.LIGHT);
        assertTrue(light instanceof Light);
    }

    @Test
    public void makeItemDarkness(){
        IEquipableItem darkness = factory.makeItem(ItemType.DARKNESS);
        assertTrue(darkness instanceof Darkness);
    }

    @Test
    public void makeItemSoul(){
        IEquipableItem soul = factory.makeItem(ItemType.SOUL);
        assertTrue(soul instanceof Soul);
    }
    @Test
    public void makeItemSpear(){
        IEquipableItem spear = factory.makeItem(ItemType.SPEAR);
        assertTrue(spear instanceof Spear);
    }
    @Test
    public void makeItemStaff(){
        IEquipableItem staff = factory.makeItem(ItemType.STAFF);
        assertTrue(staff instanceof Staff);
    }
    @Test
    public void makeItemSword(){
        IEquipableItem sword = factory.makeItem(ItemType.SWORD);
        assertTrue(sword instanceof Sword);
    }



}
