package controller;

import model.units.IUnit;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

public class ResponseNoLiveHero implements PropertyChangeListener {
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
