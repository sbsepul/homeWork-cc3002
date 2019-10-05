package model.factoryItem;

import model.items.IEquipableItem;

/**
 * Creates items
 *
 * @author Sebastian Sepulveda
 * @version 2.0
 * @since 2.0
 */

public interface IFactoryItem {
    /**
     * Create a unit
     * @return
     */
    public IEquipableItem create();

}
