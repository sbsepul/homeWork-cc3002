/*
 * The MIT License
 *
 * Copyright (c) 2019 Google, Inc. http://angularjs.org
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

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
