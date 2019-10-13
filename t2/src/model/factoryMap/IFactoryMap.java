package model.factoryMap;

import model.map.Field;

/**
 * Interface of Factory Map: create a Field with size determinate for GameController
 *
 * @author Sebastian Sepulveda
 * @version 2.0
 * @since 2.0
 *
 */

public interface IFactoryMap {
    /**
     * Create a instance of Field
     * @return Field
     */
    public Field createMap();

}
