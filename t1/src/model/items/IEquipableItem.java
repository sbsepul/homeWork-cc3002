package model.items;

import model.map.Location;
import model.units.IUnit;

import java.util.List;

/**
 * This interface represents the <i>weapons</i> that the units of the game can use.
 * <p>
 * The signature for all the common methods of the weapons are defined here. Every weapon have a
 * base damage and is strong or weak against other type of weapons.
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public interface IEquipableItem {

  /**
   * Equips this item to a unit.
   *
   * @param unit
   *     the unit that will be quipped with the item
   */
  public void equipTo(IUnit unit);

  /**
   * @return the unit that has currently equipped this item
   */
  public IUnit getOwner();

  /**
   * @return the name of the item
   */
  public String getName();

  /**
   * @return the power of the item
   */
  public int getPower();

  /**
   * @return the minimum range of the item
   */
  public int getMinRange();

  /**
   * @return the maximum range of the item
   */
  public int getMaxRange();
}
