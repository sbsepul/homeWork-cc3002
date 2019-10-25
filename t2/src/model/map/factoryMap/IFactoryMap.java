package model.map.factoryMap;

import model.map.Field;

/**
 * Generate a Normal Map square of size n x n with random connection
 * the dimensions are determinate for the GameController
 *
 * The format of the map is the next:
 *
 * (n,0)  ...  (n,n)
 * .             .
 * .             .
 * (0,0)  ...  (0,n)
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

    /**
     * Setter the seed for the random number
     * @param numLong
     */
    void setNumLong(long numLong);

    /**
     * @return long number of seed. 0 if the seed was not modified
     */
    long getLong();
}
