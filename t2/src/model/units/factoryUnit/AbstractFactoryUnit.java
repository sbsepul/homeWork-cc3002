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
import model.items.factoryItem.FactoryItemProvider;
import model.items.factoryItem.IFactoryItem;
import model.items.factoryItem.ItemType;
import model.map.InvalidLocation;
import model.map.Location;

import java.util.List;
import java.util.Map;

public abstract class AbstractFactoryUnit implements IFactoryUnit {
    protected final int hp;
    protected final int move;
    protected Location location = new InvalidLocation();
    protected IEquipableItem[] itemAll = new IEquipableItem[0];
    protected FactoryItemProvider factoryItemProvider = new FactoryItemProvider();

    public AbstractFactoryUnit(){
        this.hp = 50;
        this.move = 2;
    }

    @Override
    public int getHp() {
        return hp;
    }

    @Override
    public int getMove() {
        return move;
    }

    @Override
    public IEquipableItem[] getItemAll() {
        return itemAll;
    }

    @Override
    public Location getLocation() {
        return location;
    }


    @Override
    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public void setItems(IEquipableItem... item) {
        this.itemAll = item;
    }

    @Override
    public void addItemForDefault() {
        Map<String, IFactoryItem> factoryItemMap = factoryItemProvider.createItemMap();
        this.itemAll = new IEquipableItem[]{
                factoryItemMap.get("bow").createItem(),
                factoryItemMap.get("axe").createItem()
        };
    }
}
