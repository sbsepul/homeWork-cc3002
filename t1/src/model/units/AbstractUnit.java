package model.units;

import static java.lang.Math.min;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.items.*;
import model.items.axe.AttackAxe;
import model.items.bow.AttackBow;
import model.items.spears.AttackSpears;
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
   */
  protected AbstractUnit(final int hitPoints, final int movement,
      final Location location, final int maxItems, final IEquipableItem... items) {
    this.currentHitPoints = hitPoints;
    this.movement = movement;
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
  public List<IEquipableItem> getItems() {
    return List.copyOf(items);
  }

  @Override
  public IEquipableItem getEquippedItem() { return equippedItem; }

  @Override
  public void setEquippedItem(final IEquipableItem item) {    this.equippedItem = item;  }

  /**
   *
   * @param attack
   */
  protected void receiveAttack(IAttack attack) { this.currentHitPoints -= attack.getBaseDamage(); }

  /**
   *
   * @param attack
   */
  protected void receiveCure(IAttack attack) { this.currentHitPoints += attack.getBaseDamage(); }
  //COMBAT

  @Override
  public void receiveAxeAttack(AttackAxe attackAxe) { receiveAttack(attackAxe); }

  @Override
  public void receiveBowAttack(AttackBow attackBow) {
    receiveAttack(attackBow);
  }

  @Override
  public void receiveSpearsAttack(AttackSpears attackSpears) {
    receiveAttack(attackSpears);
  }

  @Override
  public void receiveSwordsAttack(AttackSword attackSword) {
    receiveAttack(attackSword);
  }


  /**
   * Receives an attack to which this Pokémon is weak.
   *
   * @param attack
   *     Received attack.
   */
  protected void receiveWeaknessAttack(IAttack attack){
    this.currentHitPoints -= attack.getBaseDamage() * 1.5;
  };


  /**
   * Receives an attack to which this Pokémon is resistant.
   *
   * @param attack
   *     Received attack.
   */
  protected void receiveResistantAttack(IAttack attack) {
    this.currentHitPoints -= attack.getBaseDamage() - 20;
  }
  //END COMBAT

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
}

