package model.factoryItem;


/**
 *
 *
 */
public class FactoryProviderItem {
    /**
     *
     * @param typeItem
     * @return
     */
    public IFactoryItem makeItem(ItemType typeItem){
        switch (typeItem){
            case AXE:
                return new AxeFactory("axe");
            case BOW:
                return new BowFactory("bow",10,2,3);
            case SPEAR:
                return new SpearFactory("spear");
            case SWORD:
                return new SwordFactory("sword");
            case STAFF:
                return new StaffFactory("staff");
            case SOUL:
                return new SoulFactory("soul");
            case LIGHT:
                return new LightFactory("light");
            case DARKNESS:
                return new DarknessFactory("darkness");
            default:
                throw new IllegalArgumentException("Item not supported");
        }
    }

}
