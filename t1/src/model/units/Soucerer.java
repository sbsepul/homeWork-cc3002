package model.units;

import model.items.*;
import model.map.Location;

public class Soucerer extends AbstractUnit{

    /**
     * Creates a new Unit.
     *
     * @param hitPoints the maximum amount of damage a unit can sustain
     * @param movement  the number of panels a unit can move
     * @param location  the current position of this unit on the map
     * @param maxItems  maximum amount of items this unit can carry
     * @param items
     */
    protected Soucerer(int hitPoints, int movement, Location location, int maxItems, IEquipableItem... items) {
        super(hitPoints, movement, location, maxItems, items);
    }

    @Override
    protected void attack(IUnit enemy) {

    }


    @Override
    public void equipItemOther(IEquipableItem item) { }

    @Override
    public void equipItemBow(Bow item) {}

    @Override
    public void equipItemAxe(Axe item) {}

    @Override
    public void equipItemSword(Sword item) {}

    @Override
    public void equipItemStaff(Staff item) {}

    @Override
    public void equipItemSpear(Spear item) {}
}
