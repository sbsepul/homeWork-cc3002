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
import model.items.factoryItem.AxeFactoryItem;
import model.items.factoryItem.BowFactoryItem;
import model.items.factoryItem.IFactoryItem;
import model.map.InvalidLocation;
import model.map.Location;
import model.units.Archer;
import model.units.IUnit;

public class ArcherFactTest extends AbstractFactoryUnitTest {
    private IFactoryUnit factory;
    private IFactoryItem fabBow = new BowFactoryItem();
    private IEquipableItem bow;

    @Override
    public void setEquippedItem() {
        bow = fabBow.createItem();
    }

    @Override
    public IEquipableItem getArmyDefault() {
        return bow;
    }

    @Override
    protected void setFactory() {
        unitCreated = new Archer(50,2, new InvalidLocation());
        factory = new ArcherFactory();
    }

    @Override
    protected IFactoryUnit getFactory() {
        return factory;
    }

}