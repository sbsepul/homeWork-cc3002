package model.units.handlers;

import controller.Tactician;
import model.units.IUnit;
import model.units.NormalUnit;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;


/**
 * Listener of the normal unit's status in the player's inventory
 *
 * @author Sebastian Sepulveda
 * @version 1.0
 * @since 2.0
 *
 */
public class ResponseNormalUnit implements PropertyChangeListener {
    private Tactician player;

    /**
     * @param tactician is the player in the game.
     *             This listener observe to a normal unit
     *             if a normal unit die, this unit is remove of the game
     */
    public ResponseNormalUnit(Tactician tactician){
        this.player = tactician;
    }

    /**
     * This method gets called when a bound property is changed.
     *
     * @param evt A PropertyChangeEvent object describing the event source
     *            and the property that has changed.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if((double) evt.getNewValue() <= 0) player.removeUnit((NormalUnit) evt.getNewValue());
    }

}
