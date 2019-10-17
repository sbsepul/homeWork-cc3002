package model.units;

import static java.lang.Math.abs;
import static java.lang.Math.min;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    this.items.addAll(Arrays.asList(items).subList(0, min(maxItems, items.length)));
    this.equippedItem = new NullItem();
  }

  @Override
  public void equipItem(IEquipableItem item){
    if(!item.isEmpty()){
      if(items.contains(item)){
        item.equipTo(this);
      }
    }
  }

  @Override
  public boolean isNull() {
    return false;
  }

  @Override
  public boolean initCombat(IUnit unitEnemy) {
    return this.getCurrentHitPoints()>0 && unitEnemy.getCurrentHitPoints()>0
            && !this.getEquippedItem().isEmpty() && this.isInRange(unitEnemy);
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
      if(!item.isEmpty()){
        if(item.getOwner().isNull()){
          this.items.add(item);
          item.setOwner(this);
        }
      }
    }
  }

  @Override
  public IEquipableItem removeItem(IEquipableItem item) {
    if(!items.isEmpty()){
      if(this.items.contains(item)) {
        for(int i=0; i<this.getItems().size(); i++){
          if(item.equals(this.getItems().get(i))){
            if(item.equals(this.getEquippedItem())){
              this.setEquippedItem(new NullItem());
            }
            item.setOwner(new NullUnit());
            return this.items.remove(i);
          }
        }
      }
    }
    return null;
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
  public void receiveAttack(IEquipableItem attack){
    this.currentHitPoints -= attack.getPower();
  }

  @Override
  public void receiveAttackWeakness(IEquipableItem attack){
    this.currentHitPoints -= (int) (attack.getPower() *1.5);
  }

  @Override
  public void receiveAttackResistant(IEquipableItem attack) {
    double power = attack.getPower() - 20;
    if(power>=0){
      this.currentHitPoints -= attack.getPower() - 20;
    }
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
    int maxRange = this.getEquippedItem().getMaxRange();
    int minRange = this.getEquippedItem().getMinRange();
    double distance = this.getLocation().distanceTo(unit.getLocation());
    if(minRange <= distance  &&  maxRange >= distance){
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
  public int setEquippedItem(IEquipableItem item) {
    if(item.isEmpty()){
      this.equippedItem = new NullItem();
      return 0;
    }
    this.equipItem(item);
    if(!this.getEquippedItem().isEmpty()){
      if(this.getEquippedItem().equals(item)){
        return 0;
      }
    }
    return -1;
  }

  @Override
  public boolean canExchange(IUnit unit, IEquipableItem item) {
    return !this.getItems().isEmpty() && !unit.isItemFull()
            && this.getLocation().distanceTo(unit.getLocation())==1
            && this.getItems().contains(item);
  }

  @Override
  public void giveItem(IUnit unit, IEquipableItem item) {
    if(canExchange(unit,item)){
      IEquipableItem itemA = this.removeItem(item);
      unit.addItem(itemA);
    }
  }

  @Override
  public Location getLocation() {
    return location;
  }
  @Override
  public void setLocation(final Location location) {
    this.location = location;
  }

  @Override
  public int getMovement() {
    return movement;
  }

  @Override
  public void moveTo(final Location targetLocation) {
    if (getLocation().distanceTo(targetLocation) <= getMovement()
        && targetLocation.getUnit() == null) {
      setLocation(targetLocation);
    }
  }
  @Override
  public boolean equals(Object obj) {
    return obj instanceof IUnit && ((IUnit) obj).getCurrentHitPoints() == currentHitPoints
            && ((IUnit) obj).getLocation().equals(location) && ((IUnit) obj).getEquippedItem().equals(equippedItem)
            && ((IUnit) obj).getMovement()==movement && ((IUnit) obj).getItems().equals(items);
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

