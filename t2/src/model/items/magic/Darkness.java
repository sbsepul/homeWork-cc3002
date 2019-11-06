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

import model.items.IAttack;
import model.items.IEquipableItem;
import model.units.IUnit;
import org.jetbrains.annotations.NotNull;

/**
 * This class represents an <i>Darkness</i>.
 * <p>
 * Darkness is weakness counter Light
 * But resistant counter Soul
 *
 * @author Sebastian Sepulveda
 * @since 1.0
 */

public class Darkness extends AbstractItemMagic implements IAttack {
    /**
     * Create a new Darkness item
     *
     * @param name
     *      the name of the darkness
     * @param power
     *      the power of the darkness
     * @param minRange
     *      the minimum range of the item
     * @param maxRange
     *      the maximum range of the item
     */
    public Darkness(String name, int power, int minRange, int maxRange) {
        super(name, power, minRange, maxRange);
    }

    @Override
    public void equipTo(@NotNull IUnit unit) {
        unit.equipItemDarkness(this);
        this.setOwner(unit);
    }

    @Override
    public void giveMagicAttack(@NotNull IEquipableItem enemyAttack){
        enemyAttack.receiveDarknessAttack(this);
    }

    @Override
    public void receiveDarknessAttack(Darkness attackDarkness) {
        super.receiveAttackNormal(attackDarkness);
    }

    @Override
    public void receiveLightAttack(Light attackLight) {
        super.receiveStrongAttack(attackLight);
    }

    @Override
    public void receiveSoulAttack(Soul attackSoul) {
        super.receiveSoftAttack(attackSoul);
    }
}
