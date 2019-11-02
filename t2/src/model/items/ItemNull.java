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

import model.items.magic.Darkness;
import model.items.magic.Light;
import model.items.magic.Soul;
import model.units.IUnit;

public class ItemNull implements IEquipableItem {

    /**
     * Equips this item to a unit.
     * @param unit the unit that will be quipped with the item
     */
    @Override
    public void equipTo(IUnit unit) {

    }

    /**
     * Set the owner of the army
     *
     * @param unit
     */
    @Override
    public void setOwner(IUnit unit) {

    }

    @Override
    public boolean inRangeItem() {
        return false;
    }

    /**
     * @return the unit that has currently equipped this item
     */
    @Override
    public IUnit getOwner() {
        return null;
    }

    /**
     * @return the name of the item
     */
    @Override
    public String getName() {
        return "";
    }

    /**
     * @return the power of the item
     */
    @Override
    public int getPower() {
        return 0;
    }

    /**
     * @return the minimum range of the item
     */
    @Override
    public int getMinRange() {
        return 0;
    }

    /**
     * @return the maximum range of the item
     */
    @Override
    public int getMaxRange() {
        return 0;
    }

    /**
     * @param itemEnemy is the unitEnemy's item
     * @return true if the unit can attack to other
     */
    @Override
    public boolean canAttack(IEquipableItem itemEnemy) {
        return false;
    }

    /**
     * @param attackBow is the type of damage received for a Bow
     */
    @Override
    public void receiveBowAttack(Bow attackBow) {

    }

    /**
     * @param attackAxe is the type of damage received for a Axe
     */
    @Override
    public void receiveAxeAttack(Axe attackAxe) {

    }

    /**
     * @param attackSword is the type of damage received for a Sword
     */
    @Override
    public void receiveSwordsAttack(Sword attackSword) {

    }

    /**
     * @param attackSpears is the type of damage received for a Spear
     */
    @Override
    public void receiveSpearsAttack(Spear attackSpears) {

    }

    /**
     * Receives an attack to which this Unit is weak.
     *
     * @param attack Received attack.
     */
    @Override
    public void receiveUnitWeaknessAttack(IEquipableItem attack) {

    }

    /**
     * Receives an attack to which this Unit is resistant.
     *
     * @param attack Received attack.
     */
    @Override
    public void receiveUnitResistantAttack(IEquipableItem attack) {

    }

    /**
     * A item can receive a Attack without damage additional
     *
     * @param attack
     */
    @Override
    public void receiveUnitAttack(IEquipableItem attack) {

    }

    /**
     * @param attackDarkness is the type of damage received for a Darkness
     */
    @Override
    public void receiveDarknessAttack(Darkness attackDarkness) {

    }

    /**
     * @param attackLight is the type of damage received for a Light
     */
    @Override
    public void receiveLightAttack(Light attackLight) {

    }

    /**
     * @param attackSoul is the type of damage received for a Soul
     */
    @Override
    public void receiveSoulAttack(Soul attackSoul) {

    }

    /**
     * Attack special to items magics
     * if the item is not magic, does not apply
     *
     * @param enemyAttack is the enemy's item
     */
    @Override
    public void giveMagicAttack(IEquipableItem enemyAttack) {

    }

    @Override
    public int getDistance() {
        return 0;
    }

    @Override
    public void setDistance(int distance) {

    }
}
