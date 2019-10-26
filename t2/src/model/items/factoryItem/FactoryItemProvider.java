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

package model.items.factoryItem;


import model.items.factoryItem.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

/**
 *
 *
 */
public class FactoryItemProvider {
    /**
     *
     * @param typeItem
     * @return
     */
    public IFactoryItem makeItem(ItemType typeItem){
        switch (typeItem){
            case AXE:
                return new AxeFactoryItem();
            case BOW:
                return new BowFactoryItem();
            case SPEAR:
                return new SpearFactoryItem();
            case SWORD:
                return new SwordFactoryItem();
            case STAFF:
                return new StaffFactoryItem();
            case SOUL:
                return new SoulFactoryItem();
            case LIGHT:
                return new LightFactoryItem();
            case DARKNESS:
                return new DarknessFactoryItem();
            default:
                throw new IllegalArgumentException("Item not supported");
        }
    }

    public Map<String, IFactoryItem> createItemMap(){
        Map<String, IFactoryItem> mapItem = new HashMap<>();
        List<IFactoryItem> l = List.of(
                this.makeItem(ItemType.AXE),
                this.makeItem(ItemType.BOW),
                this.makeItem(ItemType.DARKNESS),
                this.makeItem(ItemType.LIGHT),
                this.makeItem(ItemType.SOUL),
                this.makeItem(ItemType.SPEAR),
                this.makeItem(ItemType.STAFF),
                this.makeItem(ItemType.SWORD)
        );
        IntStream.range(0,l.size()).forEach(
                i -> mapItem.put(l.get(i).getName(), l.get(i))
        );
        return mapItem;
    }

}
