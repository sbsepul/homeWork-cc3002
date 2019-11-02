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

import controller.Tactician;
import model.items.*;
import model.items.magic.Darkness;
import model.items.magic.Light;
import model.items.magic.Soul;
import model.map.InvalidLocation;
import model.map.Location;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

public class UnitNull implements IUnit {
    /**
     * @param item to equip a unit with a item
     */
    @Override
    public void equipItem(IEquipableItem item) {

    }

    /**
     * @param item to equip a Darkness to Sorcerer, item magic
     */
    @Override
    public void equipItemDarkness(Darkness item) {

    }

    /**
     * @param item to equip a Light to Sorcerer, item magic
     */
    @Override
    public void equipItemLight(Light item) {

    }

    /**
     * @param item to equip a Soul to Sorcerer, item magic
     */
    @Override
    public void equipItemSoul(Soul item) {

    }

    /**
     * @param item to equip a Bow to Archer
     */
    @Override
    public void equipItemBow(Bow item) {

    }

    /**
     * @param item to equip Axe to Fighter
     */
    @Override
    public void equipItemAxe(Axe item) {

    }

    /**
     * @param item to equip Sword to SwordMaster
     */
    @Override
    public void equipItemSword(Sword item) {

    }

    /**
     * @param item to equip Staff to Cleric
     */
    @Override
    public void equipItemStaff(Staff item) {

    }

    /**
     * @param item to equip Spear to Hero
     */
    @Override
    public void equipItemSpear(Spear item) {

    }

    /**
     * @return the items carried by this unit
     */
    @Override
    public List<IEquipableItem> getItems() {
        return new ArrayList<>();
    }

    /**
     * @return the currently equipped item
     */
    @Override
    public IEquipableItem getEquippedItem() {
        return new ItemNull();
    }

    /**
     * @return true if the inventory of items is full, false otherwise.
     */
    @Override
    public boolean isItemFull() {
        return false;
    }

    /**
     * add a item to the unit
     *
     * @param item
     */
    @Override
    public void addItem(IEquipableItem item) {

    }

    /**
     * get a item of the inventory
     *
     * @param item
     * @return
     */
    @Override
    public void removeItem(IEquipableItem item) {

    }

    /**
     * @return the current location of the unit
     */
    @Override
    public Location getLocation() {
        return new InvalidLocation();
    }

    /**
     * Sets a new location for this unit,
     *
     * @param location
     */
    @Override
    public void setLocation(Location location) {

    }

    /**
     * @return the number of cells this unit can move
     */
    @Override
    public int getMovement() {
        return 0;
    }

    /**
     * Moves this unit to another location.
     * <p>
     * If the other location is out of this unit's movement range, the unit doesn't move.
     *
     * @param targetLocation
     */
    @Override
    public void moveTo(Location targetLocation) {

    }

    /**
     * Add a Listener that review a change in
     *
     * @param plc
     */
    @Override
    public void addObserverMovement(PropertyChangeListener plc) {

    }

    /**
     * @return hit points of the unit
     */
    @Override
    public double getCurrentHitPoints() {
        return 0;
    }

    /**
     * @return maximum hit points of the unit
     */
    @Override
    public double getMaxCurrentHitPoints() {
        return 0;
    }

    /**
     * Is necessary for that a unit attack on other
     *
     * @param unit is the enemy
     */
    @Override
    public void attack(IUnit unit) {

    }

    /**
     * A unit receive a Normal Attack, without modify damage
     *
     * @param attack normal that receive this unit
     */
    @Override
    public void receiveAttack(IEquipableItem attack) {

    }

    /**
     * A unit receive a Attack that seriously affect it
     *
     * @param attack 's damage increase x1.5
     */
    @Override
    public void receiveAttackWeakness(IEquipableItem attack) {

    }

    /**
     * A unit receive a Attack that not affect it too much.
     *
     * @param attack 's damage reduce 20 points
     */
    @Override
    public void receiveAttackResistant(IEquipableItem attack) {

    }

    /**
     * A unit receive a Attack that recovers it
     *
     * @param attack 's damage increase the unit's hp that is attacked
     */
    @Override
    public void receiveRecovery(IEquipableItem attack) {

    }

    /**
     * Determine if the unit enemy is in the range of army's unit that attack
     *
     * @param unit is the enemy
     * @return true if the enemy is between MinRange and MaxRange, false otherwise
     */
    @Override
    public boolean isInRange(IUnit unit) {
        return false;
    }

    /**
     * Give a item to other
     *
     * @param unit who receive the item
     * @param item what is given
     */
    @Override
    public void giveItem(IUnit unit, IEquipableItem item) {

    }

    /**
     * set a item equipped for any other
     *
     * @param item new that will be equipped
     * @return
     */
    @Override
    public void setEquippedItem(IEquipableItem item) {

    }

    /**
     * change the item equipped in the unit if this have,
     * for other item in the inventory
     *
     * @param item new that will be equipped
     */
    @Override
    public void changeEquippedItem(IEquipableItem item) {

    }

    /**
     * A combat init when
     * the two units is life
     * this unit have a item equipped
     * the enemy is in the equippedItem's range
     *
     * @param unitEnemy that will be attacked
     * @return true if this unit can attack, false otherwise
     */
    @Override
    public boolean initCombat(IUnit unitEnemy) {
        return false;
    }

    /**
     * Verify if a unit can exchange a item to other unit
     *
     * @param unit that receive the item
     * @param item that will be exchange
     * @return true if the two units can exchange a item
     */
    @Override
    public boolean canExchange(IUnit unit, IEquipableItem item) {
        return false;
    }

    /**
     * Revise if a unit can counterattack or not
     *
     * @param distance to the target unit
     * @return true if the target unit is in the range of the item equipped, false otherwise
     */
    @Override
    public boolean canCounterAttack(int distance) {
        return false;
    }

    /**
     * @return the tactician owner of the unit
     */
    @Override
    public Tactician getTactician() {
        return null;
    }

    /**
     * Setter the tactician owner of the unit
     *
     * @param tactician new
     */
    @Override
    public void setTactician(Tactician tactician) {

    }
}
