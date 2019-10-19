package model.items.factoryItem;


import model.items.factoryItem.*;

/**
 *
 *
 */
public class FactoryItemProvider {
    /**
     *
     * @param typeItem
     * @return
     */
    public IFactoryItem makeItem(ItemType typeItem){
        switch (typeItem){
            case AXE:
                return new AxeFactoryItem();
            case BOW:
                return new BowFactoryItem();
            case SPEAR:
                return new SpearFactoryItem();
            case SWORD:
                return new SwordFactoryItem();
            case STAFF:
                return new StaffFactoryItem();
            case SOUL:
                return new SoulFactoryItem();
            case LIGHT:
                return new LightFactoryItem();
            case DARKNESS:
                return new DarknessFactoryItem();
            default:
                throw new IllegalArgumentException("Item not supported");
        }
    }

}
