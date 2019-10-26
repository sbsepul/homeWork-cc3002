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

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

abstract class AbstractFactoryItemTest {
    protected IFactoryItem factory;
    protected String expectedName;
    protected int expectedPower;
    protected int expectedMinRange;
    protected int expectedMaxRange;

    @BeforeEach
    public void setUp(){
        setFactoryItem();
        expectedPower = 10;
        expectedMinRange = 1;
        expectedMaxRange = 2;
    }

    public IFactoryItem getFactory() {
        return factory;
    }

    public int getExpectedMaxRange() {
        return expectedMaxRange;
    }

    public int getExpectedMinRange() {
        return expectedMinRange;
    }

    public int getExpectedPower() {
        return expectedPower;
    }

    public String getExpectedName() {
        return expectedName;
    }

    protected abstract void setFactoryItem();

    protected abstract IFactoryItem getFactoryItem();

    @Test
    public void constructorTest(){
        assertEquals(getExpectedName(), getFactory().getName());
        assertEquals(getExpectedMaxRange(), getFactory().getMaxRange());
        assertEquals(getExpectedMinRange(),getFactory().getMinRange());
        assertEquals(getExpectedPower(),getFactory().getPower());
    }

    @Test
    public void setterTest(){

    }


}