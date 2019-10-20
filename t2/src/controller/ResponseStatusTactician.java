package controller;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ResponseStatusTactician implements PropertyChangeListener {
    private Boolean status;
    private Tactician source;
    /**
     * This method gets called when a bound property is changed.
     *
     * @param evt A PropertyChangeEvent object describing the event source
     *            and the property that has changed.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        this.setSource((Tactician) evt.getSource());
        this.setProperty((Boolean) evt.getNewValue());
    }

    public void setSource(Tactician source) {
        this.source = source;
    }

    public Tactician getSource() {
        return source;
    }

    public void setProperty(Boolean status) {
        this.status = status;
    }

    public Boolean getProperty() {
        return status;
    }
}
