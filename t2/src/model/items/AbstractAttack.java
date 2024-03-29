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

package model.items;

/**
 * Abstract class that define common information
 * between the items that can attack
 *
 * @author Sebastian Sepulveda
 * @version 1.0
 * @since 2.0
 */
public abstract class AbstractAttack extends AbstractItem implements IAttack {
    /**
     * Constructor for a default item without any special behaviour.
     *
     * @param name     the name of the item
     * @param power    the power of the item (this could be the amount of damage or healing the item does)
     * @param minRange the minimum range of the item
     * @param maxRange
     */
    public AbstractAttack(String name, int power, int minRange, int maxRange) {
        super(name, power, minRange, maxRange);
    }
    @Override
    public void receiveAttackNormal(IAttack itemNormal){
        this.receiveUnitAttack(itemNormal);
        if(this.canAttack(itemNormal)){
            itemNormal.receiveUnitAttack(this);
        }
    }

    @Override
    public void receiveStrongAttack(IAttack attackStrong) {
        this.receiveUnitWeaknessAttack(attackStrong);
        if(this.canAttack(attackStrong)) {
            attackStrong.receiveUnitResistantAttack(this);
        }
    }

    @Override
    public void receiveSoftAttack(IAttack attackSoft) {
        this.receiveUnitResistantAttack(attackSoft);
        if(this.canAttack(attackSoft)){
            attackSoft.receiveUnitWeaknessAttack(this);
        }
    }

    @Override
    public void receiveMagicAttack(IAttack attackMagic) {
        this.receiveUnitWeaknessAttack(attackMagic);
        if(this.canAttack(attackMagic)){
            attackMagic.receiveUnitWeaknessAttack(this);
        }
    }

}