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

/**
 *
 */
public abstract class AbstractFactoryItem implements IFactoryItem {
    protected String name;
    protected int power;
    protected int minRange;
    protected int maxRange;

    /**
     * Setters the parameters for default to each items
     * @param name is the name of the item
     */
    public AbstractFactoryItem(String name){
        this.name = name;
        this.power = 10;
        this.minRange = 1;
        this.maxRange = 2;
    }

    /**
     * Setter the parameters for default in the creation
     * @param name is the name of the item
     * @param power power for default of the item
     * @param minRange for default of the item
     * @param maxRange for default of the item
     */
    public AbstractFactoryItem(String name, int power, int minRange, int maxRange){
        this.name = name;
        this.power = power;
        this.minRange = minRange;
        this.maxRange = maxRange;
    }
    @Override
    public String getName() {
        return name;
    }
    @Override
    public int getMaxRange() {
        return maxRange;
    }
    @Override
    public int getMinRange() {
        return minRange;
    }
    @Override
    public int getPower() {
        return power;
    }
}
