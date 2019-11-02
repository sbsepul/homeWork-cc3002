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

import static java.lang.Math.abs;
import static java.lang.Math.min;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import controller.Tactician;
import model.items.*;
import model.items.magic.Darkness;
import model.items.magic.Light;
import model.items.magic.Soul;
import model.map.Location;

/**
 * This class represents an abstract unit.
 * <p>
 * An abstract unit is a unit that cannot be used in the
 * game, but that contains the implementation of some of the methods that are common for most
 * units.
 *
 * @author Sebastian Sepulveda
 * @version 2.0
 * @since 1.0
 */
public abstract class AbstractUnit implements IUnit{

  protected final List<IEquipableItem> items = new ArrayList<>();
  protected IEquipableItem equippedItem;
  private Location location;
  private double currentHitPoints;
  private final int movement;
  private final int maxItems;
  private final double maxHitPoints;
  private static final double EPSILON=1e-7;
  protected PropertyChangeSupport
          changeSupport= new PropertyChangeSupport(this),
          changeMovedSupport = new PropertyChangeSupport(this);
  protected Tactician tacticianOwner;

  /**
   * Creates a new Unit.
   *
   * @param hitPoints
   *     the maximum amount of damage a unit can sustain
   * @param movement
   *     the number of panels a unit can move
   * @param location
   *     the current position of this unit on the map
   * @param maxItems
   *     maximum amount of items this unit can carry
   * @param items
   *     items that the unit not use but can carry
   */
  protected AbstractUnit(final int hitPoints, final int movement,
      final Location location, final int maxItems, final IEquipableItem... items) {
    this.maxHitPoints = hitPoints;
    this.currentHitPoints = hitPoints;
    this.movement = movement;
    this.maxItems = maxItems;
    this.location = location;
    if(location!=null) location.setUnit(this);
    this.items.addAll(Arrays.asList(items).subList(0, min(maxItems, items.length)));
  }

  @Override
  public void equipItem(IEquipableItem item){
    if(item!=null){
      if(items.contains(item)){
        item.equipTo(this);
      }
    }
  }

  @Override
  public boolean initCombat(IUnit unitEnemy) {
    return this.getCurrentHitPoints()>0 && unitEnemy.getCurrentHitPoints()>0
            && this.getEquippedItem()!=null && isInRange(unitEnemy);
  }

  @Override
  public double getCurrentHitPoints() {
    return currentHitPoints;
  }

  @Override
  public double getMaxCurrentHitPoints() {
    return this.maxHitPoints;
  }

  /**
   * Add a element to list of items's unit
   * @param item
   */
  @Override
  public void addItem(IEquipableItem item) {
    int n = items.size();
    if(n<this.maxItems){
      if(item!=null){
        if(item.getOwner()==null){
          this.items.add(item);
          item.setOwner(this);
        }
      }
    }
  }

  @Override
  public void removeItem(IEquipableItem item) {
    if(!items.isEmpty() && this.items.contains(item)){
      if(item.equals(this.getEquippedItem())) this.setEquippedItem(null);
      items.remove(item);
      item.setOwner(null);
    }
  }

  @Override
  public boolean isItemFull() {
    return this.maxItems==items.size();
  }

  @Override
  public void changeEquippedItem(IEquipableItem item) {
    if(this.getItems().contains(item)){
      this.setEquippedItem(item);
    }
  }

  @Override
  public void receiveAttack(IEquipableItem attack) {
    double init = getCurrentHitPoints();
    this.currentHitPoints -= attack.getPower();
    changeSupport.firePropertyChange(
            new PropertyChangeEvent(this,"normal-attack", init, currentHitPoints));
  }

  @Override
  public void receiveAttackWeakness(IEquipableItem attack){
    double init = getCurrentHitPoints();
    this.currentHitPoints -= attack.getPower() *1.5;
    changeSupport.firePropertyChange(
            new PropertyChangeEvent(this, "critical-attack", init, currentHitPoints)
    );
  }

  @Override
  public void receiveAttackResistant(IEquipableItem attack) {
      double init = getCurrentHitPoints();
    double power = attack.getPower() - 20;
    if(power>=0){
      this.currentHitPoints -= attack.getPower() - 20;
    }
    changeSupport.firePropertyChange(
            new PropertyChangeEvent(this, "resistant-attack", init, currentHitPoints)
    );
  }

  @Override
  public void receiveRecovery(IEquipableItem recovery){
    this.currentHitPoints += recovery.getPower();
    if(this.currentHitPoints>this.maxHitPoints){
      this.currentHitPoints = this.maxHitPoints;
    }
  }

  @Override
  public boolean isInRange(IUnit unit) {
    int distance = (int) this.getLocation().distanceTo(unit.getLocation());
    this.equippedItem.setDistance(distance);
    return getEquippedItem().inRangeItem();
  }

  @Override
  public boolean canCounterAttack(int distance) {
    if(this.equippedItem!=null){
      equippedItem.setDistance(distance);
      return true;
    }
    return false;
  }

  @Override
  public List<IEquipableItem> getItems() {
    return List.copyOf(items);
  }

  @Override
  public IEquipableItem getEquippedItem() { return equippedItem; }

  @Override
  public void setEquippedItem(IEquipableItem item) {
    if(item==null) {
      this.equippedItem = null;
    }
    else this.equipItem(item);
  }

  @Override
  public boolean canExchange(IUnit unit, IEquipableItem item) {
    return !this.getItems().isEmpty() && !unit.isItemFull()
            && this.getLocation().distanceTo(unit.getLocation())==1
            && this.items.contains(item);
  }

  @Override
  public void giveItem(IUnit unit, IEquipableItem item) {
    if(canExchange(unit,item)){
      this.removeItem(item);
      unit.addItem(item);
    }
  }

  @Override
  public Location getLocation() {
    return location;
  }
  @Override
  public void setLocation(final Location location) {
    if(location.getUnit()==null) location.setUnit(this);
    this.location = location;
  }

  @Override
  public int getMovement() {
    return movement;
  }

  @Override
  public void moveTo(final Location targetLocation) {
    Location oldLocation = getLocation();
    if (getLocation().distanceTo(targetLocation) <= getMovement()
        && targetLocation.getUnit() == null) {
      getLocation().setUnit(null);
      setLocation(targetLocation);
      //targetLocation.setUnit(this);
      this.changeMovedSupport.firePropertyChange(
              new PropertyChangeEvent(this, "unit-moved", oldLocation, targetLocation)
      );
    }
  }

  @Override
  public void addObserverMovement(PropertyChangeListener plc) {
    this.changeMovedSupport.addPropertyChangeListener(plc);
  }

  @Override
  public Tactician getTactician() {
    return this.tacticianOwner;
  }

  @Override
  public void setTactician(Tactician tactician) {
    this.tacticianOwner = tactician;
  }

  /**
   * {@inheritDoc}
   * <p>
   * The <i>Alpaca</i> cannot attack any unit.
   */
  @Override
  public void attack(IUnit enemy) { }

  @Override
  public void equipItemDarkness(Darkness item) { }
  @Override
  public void equipItemLight(Light item) { }
  @Override
  public void equipItemSoul(Soul item) { }
  @Override
  public void equipItemBow(Bow item) { }
  @Override
  public void equipItemAxe(Axe item) { }
  @Override
  public void equipItemSword(Sword item) { }
  @Override
  public void equipItemStaff(Staff item) { }
  @Override
  public void equipItemSpear(Spear item) { }

}

