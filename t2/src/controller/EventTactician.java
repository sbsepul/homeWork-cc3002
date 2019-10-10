package controller;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class EventTactician {
    private String news;
    private PropertyChangeSupport changes;

    /**
     *
     */
    public EventTactician() {
        changes = new PropertyChangeSupport(this);
    }
    /**
     *
     * @param pcl
     */
    public void addObserver(PropertyChangeListener pcl) {
        changes.addPropertyChangeListener(pcl);
    }
    /**
     *
     * @param pcl
     */
    public void removePropertyChangeListener(PropertyChangeListener pcl){
        changes.removePropertyChangeListener(pcl);

    }
    /**
     *
     * @param value
     */
    public void setPropertyPrincipal(String value){
        changes.firePropertyChange("status", this.news, value);
        this.news = value;
    }


}
