package model.items;

import model.units.IUnit;

/**
 * This interface represents the <i>weapons</i> that the units of the game can use.
 * <p>
 * The signature for all the common methods of the weapons are defined here. Every weapon have a
 * base damage and is strong or weak against other type of weapons.<p/>
 *
 * @author Sebastian Sepulveda
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

  //ETAPA DE COMBATE

  /**
   *
   * @param unit that will be attacked
   */
  public void attack(IUnit unit);
  /**
   *
   * @param attackBow is the type of damage received when a Archer attack
   */
  void receiveBowAttack(Bow attackBow);

  /**
   *
   * @param attackAxe is the type of damage received when a Fighter attack
   */
  void receiveAxeAttack(Axe attackAxe);

  /**
   *
   * @param attackSword is the type of damage received when a SwordMaster attack
   */
  void receiveSwordsAttack(Sword attackSword);

  /**
   *
   * @param attackSpears is the type of damage received when a Hero attack
   */
  void receiveSpearsAttack(Spear attackSpears);

  /**
   *
   * @param attackStaff is the type of cure received when a Staff use his power
   */
  void receiveStaffAttack(Staff attackStaff);
}
