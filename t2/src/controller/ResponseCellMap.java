package controller;

import model.map.Location;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

public class ResponseCellMap implements PropertyChangeListener {
    private Location cellSource;
    private List<Boolean> change;

    /**
     * This method gets called when a bound property is changed.
     *
     * @param evt A PropertyChangeEvent object describing the event source
     *            and the property that has changed.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        cellSource = (Location) evt.getSource();
        setChange((List<Boolean>) evt.getNewValue());
    }

    public List<Boolean> getChange() {
        return change;
    }

    public Location getCell() {
        return cellSource;
    }

    public void setChange(List<Boolean> change) {
        this.change = change;
    }
}
