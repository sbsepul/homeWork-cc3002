package controller;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ResponseHandler implements PropertyChangeListener {
    private String news;

    public void propertyChange(PropertyChangeEvent evt) {
        this.setProperty((String) evt.getNewValue());
    }

    public void setProperty(String news) {
        this.news = news;
    }

    public String getProperty() {
        return news;
    }
}
