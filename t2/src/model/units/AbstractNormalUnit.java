package model.units;

import model.units.handlers.ResponseNormalUnit;
import model.items.IEquipableItem;
import model.map.Location;

public abstract class AbstractNormalUnit extends AbstractUnit implements NormalUnit {
    /**
     * Creates a new Unit.
     *
     * @param hitPoints the maximum amount of damage a unit can sustain
     * @param movement  the number of panels a unit can move
     * @param location  the current position of this unit on the map
     * @param maxItems  maximum amount of items this unit can carry
     * @param items
     */
    protected AbstractNormalUnit(int hitPoints, int movement, Location location, int maxItems, IEquipableItem... items) {
        super(hitPoints, movement, location, maxItems, items);
    }

    public void addObserver(ResponseNormalUnit plc){
        changeSupport.addPropertyChangeListener(plc);
    }
}
