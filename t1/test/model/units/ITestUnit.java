package model.units;


import model.items.*;
import model.items.Axe;
import model.items.Bow;
import model.items.Spear;
import model.items.Staff;
import model.items.Sword;
import model.items.magic.Darkness;
import model.items.magic.Light;
import model.items.magic.Soul;
import model.map.Field;
import org.junit.jupiter.api.Test;

/**
 * Interface that defines the common behaviour of all the test for the units classes
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public interface ITestUnit {

  /**
   * Set up the game field
   */
  void setField();

  /**
   * Set up the main unit that's going to be tested in the test set
   */
  void setTestUnit();

  void setTargets();

  /**
   * Creates a set of testing weapons
   */
  void setWeapons();

  /**
   * Checks that the constructor works properly.
   */
  @Test
  void constructorTest();

  /**
   * @return the current unit being tested
   */
  IUnit getTestUnit();

  /**
   * Checks if the axe is equipped correctly to the unit
   */
  @Test
  void equipAxeTest();

  /**
   * Tries to equip a weapon to the alpaca and verifies that it was not equipped
   *
   * @param item
   *     to be equipped
   */
  void checkEquippedItem(IEquipableItem item);

  /**
   * @return the test axe
   */
  Axe getAxe();

  @Test
  void equipSwordTest();

  /**
   * @return the test sword
   */
  Sword getSword();

  @Test
  void equipSpearTest();

  /**
   * @return the test spear
   */
  Spear getSpear();

  @Test
  void equipStaffTest();

  /**
   * @return the test staff
   */
  Staff getStaff();

  @Test
  void equipBowTest();

  /**
   * @return the test bow
   */
  Bow getBow();

  @Test
  void equipDarknessTest();

  /**
   * @return the item darkness
   */
  Darkness getDarkness();

  @Test
  void equipLightTest();

  /**
   * @return the item Light
   */
  Light getLight();

  @Test
  void equipSoulTest();

  /**
   * @return the item soul
   */
  Soul getSoul();


  /**
   * Checks if the unit moves correctly
   */
  @Test
  void testMovement();


  /**
   * Checks if the unit combat correctly
   */
  @Test
  void testCombat();

  /**
   * @return the test field
   */
  Field getField();

  /**
   * @return the target Alpaca
   */
  Alpaca getTargetAlpaca();

  Archer getTargetArcher();

  Cleric getTargetCleric();

  Fighter getTargetFighter();

  Hero getTargetHero();

  Sorcerer getTargetSorcerer();

  SwordMaster getTargetSwordMaster();

  /**
   * This check simulate a attack of a unitA counter a unitB, where
   * unitA is <b>this</b>  and unitB is <s>unit</s>,
   * itemA is for unitA and itemB is for unitB, and
   * unitA is weak to unitB and therefore unitB is resistant to unitA
   *
   *
   * @param unit that will be attacked
   * @param itemA is the attacker's item
   * @param itemB is the defender's item
   */

  void checkWeaknessAttackTest(IUnit unit, IEquipableItem itemA, IEquipableItem itemB);

  @Test
  void weaknessAttack();

  /**
   * This check simulate a attack of a unitA counter a unitB, where
   * unitA is <b>this</b>  and unitB is <s>unit</s>,
   * itemA is for unitA and itemB is for unitB, and
   * unitA is <b>resistant</b> to unitB and therefore unitB is resistant to unitA
   *
   *
   * @param unit that will be attacked
   * @param itemA is the attacker's item
   * @param itemB is the defender's item
   */

  void checkResistantAttackTest(IUnit unit, IEquipableItem itemA, IEquipableItem itemB);

  @Test
  void resistantAttack();

}
