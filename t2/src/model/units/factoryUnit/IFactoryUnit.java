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

package model.units.factoryUnit;

import model.items.IEquipableItem;
import model.map.Location;
import model.units.IUnit;


/***
 * Interface of the factory of units.
 * A factory can create units according to the kind of unit.
 * The factory can to be configured to create to the unit
 *
 * @author Sebastian Sepulveda
 * @version 1.0
 * @since 2.0
 *
 */
public interface IFactoryUnit {
    /**
     * @return the unit created
     */
    public IUnit createUnit();

    /**
     * @return the hit points that will have the unit
     *         default: 50
     */
    public int getHp();

    /**
     * @return the movement that will have the unit
     *         default: 2
     */
    public int getMove();

    /**
     * @return the list of items that will have the unit
     *         default: null
     */
    public IEquipableItem[] getItemAll();

    /**
     * @return the location of a Field where will be the unit
     *         default: null
     */
    public Location getLocation();
    /**
     * Setter the default location of the unit
     * @param location new for the unit
     */
    public void setLocation(Location location);

    /**
     * Setter the default items of the unit
     * @param items news for the unit
     */
    public void setItems(IEquipableItem... items);

    /**
     * Add items that can be equipped in the unit
     * default: add a bow
     */
    public void addItemForDefault();

}
