package model.factoryItem;


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
                return new AxeFactoryItem("axe");
            case BOW:
                return new BowFactoryItem("bow");
            case SPEAR:
                return new SpearFactoryItem("spear");
            case SWORD:
                return new SwordFactoryItem("sword");
            case STAFF:
                return new StaffFactoryItem("staff");
            case SOUL:
                return new SoulFactoryItem("soul");
            case LIGHT:
                return new LightFactoryItem("light");
            case DARKNESS:
                return new DarknessFactoryItem("darkness");
            default:
                throw new IllegalArgumentException("Item not supported");
        }
    }

}
