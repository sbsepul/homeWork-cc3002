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
 * This interface represent the <i>attack weapon</i>.
 * All this items can attack when a unit equip them.
 *
 * @author Sebastian Sepulveda
 * @version 1.0
 * @since 2.0
 */
public interface IAttack extends IEquipableItem{

    /**
     * Simulate a attack normal to the owner unit
     * @param itemNormal of the enemy unit
     */
    public void receiveAttackNormal(IAttack itemNormal);

    /**
     * Simulate a attack strong to the owner unit
     * @param attackStrong of the enemy unit
     */
    public void receiveStrongAttack(IAttack attackStrong);

    /**
     * Simulate a attack weak to the owner unit
     * @param attackSoft of the enemy unit
     */
    public void receiveSoftAttack(IAttack attackSoft);

    /**
     * Simulate a attack magic to the owner unit
     * @param attackMagic of the enemy unit
     */
    public void receiveMagicAttack(IAttack attackMagic);
}
