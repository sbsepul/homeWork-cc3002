package model.units.handlers;

import controller.Tactician;
import model.units.SpecialUnit;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Listener of the special unit's status in the player's inventory
 *
 * @author Sebastian Sepulveda
 * @version 1.0
 * @since 2.0
 *
 */
public class ResponseSpecialUnit implements PropertyChangeListener {
    private Tactician player;

    /**
     * @param tactician the special unit in the game.
     *             This listener observe to a special unit
     *             If this unit dies, the player loses
     */
    public ResponseSpecialUnit(Tactician tactician) {
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
        if((double) evt.getNewValue() <= 0) player.removeSpecialUnit((SpecialUnit) evt.getSource());
    }

}
