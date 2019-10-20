package controller;

import model.units.IUnit;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

/**
 * Listener of status of hero in the player
 *
 * @author Sebastian Sepulveda
 * @version 1.0
 * @since 2.0
 */
public class ResponseHeroes implements PropertyChangeListener {
    private List<IUnit> hero;
    /**
     * This method gets called when a bound property is changed.
     *
     * @param evt A PropertyChangeEvent object describing the event source
     *            and the property that has changed.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        this.setProperty((List<IUnit>) evt.getNewValue());
    }

    public List<IUnit> getHero(){
        return hero;
    }

    private void setProperty(List<IUnit> newValue) {
        this.hero = newValue;
    }
}
