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
import model.map.Location;

import java.util.ArrayList;
import java.util.List;

public class FactoryMap implements IFactoryMap {
    private int tamMap;
    private long numLong;

    /**
     * Constructor of Factory Map, create a instance of Field with dimensions
     * of size x size, square for default
     *
     * @param size of the Map
     */
    public FactoryMap(int size){
        this.tamMap = size;
        numLong = 0;
    }

    @Override
    public Field createMap() {
        Field map = new Field();
        if(numLong!=0) map.setSeed(getLong());
        List<Location> locations = new ArrayList<>();
        int n = this.tamMap;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++) {
                locations.add(new Location(i, j));
            }
        }
        for(Location loc: locations){
            map.addCells(false,loc);
        }
        //Location[] loc = (Location[]) locations.toArray();
        //map.addCells(false, loc);
        return map;
    }

    @Override
    public void setNumLong(long numLong) {
        this.numLong = numLong;
    }

    @Override
    public long getLong(){
        return this.numLong;
    }
}
