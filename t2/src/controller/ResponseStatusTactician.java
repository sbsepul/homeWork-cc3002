package controller;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ResponseStatusTactician implements PropertyChangeListener {
    private GameController controller;

    public ResponseStatusTactician(GameController game){
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
