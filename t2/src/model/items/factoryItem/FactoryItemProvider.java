package model.items.factoryItem;


import model.items.factoryItem.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

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

    public Map<String, IFactoryItem> createItemMap(){
        Map<String, IFactoryItem> mapItem = new HashMap<>();
        List<IFactoryItem> l = List.of(
                this.makeItem(ItemType.AXE),
                this.makeItem(ItemType.BOW),
                this.makeItem(ItemType.DARKNESS),
                this.makeItem(ItemType.LIGHT),
                this.makeItem(ItemType.SOUL),
                this.makeItem(ItemType.SPEAR),
                this.makeItem(ItemType.STAFF),
                this.makeItem(ItemType.SWORD)
        );
        IntStream.range(0,l.size()).forEach(
                i -> mapItem.put(l.get(i).getName(), l.get(i))
        );
        return mapItem;
    }

}
