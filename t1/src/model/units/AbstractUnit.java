package model.units;

import static java.lang.Math.min;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import model.items.*;
import model.items.axe.AttackAxe;
import model.items.bow.AttackBow;
import model.items.spears.AttackSpears;
import model.items.staff.Staff;
import model.items.sword.AttackSword;
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
  private int currentHitPoints;
  private final int movement;
  private final int maxItems;
  protected IEquipableItem equippedItem;
  private Location location;

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
    this.currentHitPoints = hitPoints;
    this.movement = movement;
    this.maxItems = maxItems;
    this.location = location;
    this.items.addAll(Arrays.asList(items).subList(0, min(maxItems, items.length)));
  }

  /**
   *
   * @param item
   */
  public void equipItem(IEquipableItem item){
      item.equipTo(this);
  }

  @Override
  public int getCurrentHitPoints() {
    return currentHitPoints;
  }

  @Override
  public void setCurrentHitPoints(int hp) {
    this.currentHitPoints = hp;
  }

  /**
   * Add a element to list of items's unit
   * @param item
   */
  @Override
  public void addItem(IEquipableItem item) {
    int n = items.size();
    if(n<this.maxItems){
      this.items.add(item);
    }
  }

  /**
   * Reduce hp in unit that receive a attack
   * @param attack
   */
  public void receiveAttack(IAttack attack){
    this.currentHitPoints -= attack.getBaseDamage();
  }

  /**
   * Increase hp in unit that receive a attack
   * @param recovery
   */
  public void receiveRecovery(int recovery){
    this.currentHitPoints += recovery;
  }

  @Override
  public List<IEquipableItem> getItems() {
    return List.copyOf(items);
  }

  @Override
  public IEquipableItem getEquippedItem() { return equippedItem; }

  @Override
  public void setEquippedItem(final IEquipableItem item) { this.equippedItem = item;  }

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
            && ((IUnit) obj).getLocation().equals(location) && ((IUnit) obj).getEquippedItem().equals(items)
            && ((IUnit) obj).getMovement()==movement && ((IUnit) obj).getItems().equals(equippedItem);
  }
}

