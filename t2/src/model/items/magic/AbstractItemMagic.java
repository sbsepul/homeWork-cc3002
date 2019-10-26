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

package model.items.magic;

import model.items.*;

/**
 * Abstract class that defines some common information
 * and behaviour between all items magics.
 *
 * @author Sebastian Sepulveda
 * @since 1.0
 */

public abstract class AbstractItemMagic extends AbstractAttack implements IAttack{
    /**
     * Constructor for a default item magic
     *
     * @param name     the name of the item
     * @param power    the power of the item
     * @param minRange the minimum range of the item
     * @param maxRange the maximum range of the item
     */
    public AbstractItemMagic(String name, int power, int minRange, int maxRange) {
        super(name, power, minRange, maxRange);
    }

    @Override
    public void receiveAxeAttack(Axe attackAxe) {
        super.receiveMagicAttack(attackAxe);
    }

    @Override
    public void receiveBowAttack(Bow attackBow) {
        super.receiveMagicAttack(attackBow);
    }

    @Override
    public void receiveSpearsAttack(Spear attackSpears) {
        super.receiveMagicAttack(attackSpears);
    }

    @Override
    public void receiveSwordsAttack(Sword attackSword) {
        super.receiveMagicAttack(attackSword);
    }

}
