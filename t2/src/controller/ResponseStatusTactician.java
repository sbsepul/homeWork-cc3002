package controller;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ResponseStatusTactician implements PropertyChangeListener {
    private Boolean status;
    /**
     * This method gets called when a bound property is changed.
     *
     * @param evt A PropertyChangeEvent object describing the event source
     *            and the property that has changed.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        this.setProperty((Boolean) evt.getNewValue());
    }

    public void setProperty(Boolean status) {
        this.status = status;
    }

    public Boolean getProperty() {
        return status;
    }
}
