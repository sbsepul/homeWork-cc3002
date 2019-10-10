package model.items;

import model.units.IUnit;

public class NullItem extends AbstractItem {

    public NullItem() {
        super(" ", 0, -1, -1);
    }

    /**
     * Equips this item to a unit.
     *
     * @param unit the unit that will be quipped with the item
     */
    @Override
    public void equipTo(IUnit unit) {
    }
}
