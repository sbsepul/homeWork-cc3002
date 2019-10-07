package model;

import model.items.*;
import model.items.magic.Darkness;
import model.items.magic.Light;
import model.items.magic.Soul;

import java.util.Enumeration;


/**
 *
 *
 */
public class FactoryItem implements IFactoryItem{
    /**
     *
     * @param typeItem
     * @return
     */
    @Override
    public IEquipableItem makeFactory(ItemType typeItem){
        switch (typeItem){
            case AXE:
                return new Axe("axe", 10, 1, 2);
            case BOW:
                return new Bow("bow", 10,1,2);
            case SPEAR:
                return new Spear("spear", 10, 1,2);
            case SWORD:
                return new Sword("sword", 10, 1,2);
            case STAFF:
                return  new Staff("staff", 10, 1,2);
            case SOUL:
                return new Soul("soul", 10, 1,2);
            case LIGHT:
                return new Light("light", 10,1,2);
            case DARKNESS:
                return new Darkness("darkness", 10, 1, 2);
            default:
                throw new IllegalArgumentException("Item not supported");
        }
    }

}
