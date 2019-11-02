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
import model.items.factoryItem.BowFactoryItem;
import model.items.factoryItem.IFactoryItem;
import model.map.InvalidLocation;
import model.units.Alpaca;
import model.units.IUnit;

public class AlpacaFactTest extends AbstractFactoryUnitTest {
    IFactoryUnit factory;
    IFactoryItem fabBow = new BowFactoryItem();

    @Override
    public void setEquippedItem() {
        super.setEquippedItem();
    }

    @Override
    public IEquipableItem getArmyDefault() {
        return super.getArmyDefault();
    }

    @Override
    protected void setFactory() {
        unitCreated = new Alpaca(50,2,new InvalidLocation());
        factory = new AlpacaFactory();
    }

    @Override
    protected IFactoryUnit getFactory() {
        return factory;
    }
}