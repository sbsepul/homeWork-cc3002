package controller;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ResponseHandler implements PropertyChangeListener {
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println("\nReceived Response: "+ evt.getNewValue() );
    }
}
