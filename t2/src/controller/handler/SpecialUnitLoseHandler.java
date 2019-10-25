package controller.handler;

import controller.GameController;
import controller.Tactician;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class SpecialUnitLoseHandler implements PropertyChangeListener {
    private GameController controller;

    public SpecialUnitLoseHandler(GameController game){
        this.controller = game;
    }

    /**
     * This method gets called when a bound property is changed.
     *
     * @param evt A PropertyChangeEvent object describing the event source
     *            and the property that has changed.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Tactician player = (Tactician) evt.getSource();
        controller.removeTactician(player.getName());
    }
}
