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

package model.units;

import model.items.*;
import model.items.magic.*;
import model.map.Location;

/**
 * A <i>Sorcerer</i> is a magic kind of unit, his item's attack always is weak to other item
 * <p>
 * This unit <b>can only use magic weapons</b>.
 * ie. Light - Darkness - Soul
 *
 * @author Sebastian Sepulveda
 * @since 1.0
 */

public class Sorcerer extends AbstractNormalUnit{

    /**
     * Creates a new Unit.
     *  @param hitPoints the maximum amount of damage a unit can sustain
     * @param movement  the number of panels a unit can move
     * @param location  the current position of this unit on the map
     */
    public Sorcerer(int hitPoints, int movement, Location location, IEquipableItem... items) {
        super(hitPoints, movement, location, 3, items);
    }

    @Override
    public void equipItemDarkness(Darkness item) {
        equippedItem = item;
    }
    @Override
    public void equipItemLight(Light item) {
        equippedItem = item;
    }
    @Override
    public void equipItemSoul(Soul item) {
        equippedItem = item;
    }
    @Override
    public void attack(IUnit enemy) {
        if (this.initCombat(enemy)){
            if(enemy.canCounterAttack(getEquippedItem().getDistance())) {
                this.getEquippedItem().giveMagicAttack(enemy.getEquippedItem());
            }
            else enemy.receiveAttack(this.getEquippedItem());
        }
    }
}
