package model;

import model.map.Field;

public interface IFactoryMap {
    /**
     * Create a instance of Field
     * @return Field
     */
    public Field create();

}
