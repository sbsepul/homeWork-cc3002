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
 * @author Sebastian Sepulveda
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

  /**
   * Set up the units that will be used for test with others units
   */
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

  /**
   * @return the test axe for trade
   */
  Axe getAxeTrade();

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

  /**
   * @return the target Alpaca to Trade
   */
  Alpaca getTargetAlpacaTrade();

  /**
   * @return the target Archer
   */
  Archer getTargetArcher();

  /**
   * @return the target Archer to Trade
   */
  Archer getTargetArcherTrade();

  /**
   * @return the target Cleric
   */
  Cleric getTargetCleric();

  /**
   * @return the target Cleric to Trade
   */
  Cleric getTargetClericTrade();

  /**
   * @return the target Fighter
   */
  Fighter getTargetFighter();

  /**
   * @return the target Hero
   */
  Hero getTargetHero();

  /**
   * @return the target Sorcerer
   */
  Sorcerer getTargetSorcerer();

  /**
   * @return the target Sorcerer to trade
   */
  Sorcerer getTargetSorcerer_withItems();

  /**
   * @return the target SwordMaster
   */
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

  void checkWeaknessAttack(IUnit unit, IEquipableItem itemA, IEquipableItem itemB);

  /**
   * Test simulate a attack of a unit's item that is resistant to the test unit's item
   *
   * Ex: Hero's item is resistant to SwordMaster's item, then SwordMaster implement this method
   *      attacking to Hero
   * Alpaca, Archer and Cleric haven't weakness, therefore don't implement this method
   *
   */
  @Test
  void weaknessAttackTest();

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

  void checkResistantAttack(IUnit unit, IEquipableItem itemA, IEquipableItem itemB);

  /**
   * Test simulate a attack of a unit's item that is weak to the test unit's item
   *
   * Ex: Fighter's item is weak to SwordMaster's item, then Fighter implement this method
   *      attacking to SwordMaster
   * Alpaca, Archer and Cleric haven't resistant, therefore don't implement this method
   *
   */
  @Test
  void resistantAttackTest();

  /**
   * This check simulate a attack of a unitA counter a unitB, where
   * unitA is <b>this</b>  and unitB is <s>unit</s>,
   * itemA is for unitA and itemB is for unitB, and
   * unitA is <b>the same type of unit</b> to unitB. The attack don't change
   *
   * @param unit
   * @param itemA
   * @param itemB
   */
  void checkSameTypeUnitAttack(IUnit unit, IEquipableItem itemA, IEquipableItem itemB);

  /**
   * This simulate the attack on a unit of same type, where
   * the attack's damage is normal
   *
   */
  @Test
  void sameTypeUnitAttackTest();

  /**
   * This check simulate the attack of a remote Archer to a unit normal that can't counterattack.
   *
   */
  void checkArcherAttack(IEquipableItem itemA);

  /**
   * This check simulate the attack of a remote Archer to a unit magic that can't counterattack.
   *
   * @param itemA is the item that can to be equipped for the test unit
   */
  void checkArcherAttackToMagic(IEquipableItem itemA);

  /**
   * Check that a item is given to the test unit
   *
   * @param unit is the unit that receive the item
   */
  void checkGiveItem(IUnit unit);

  @Test
  /**
   * Test to give item to Archer
   */
  void giveToUnitArcherTest();

  @Test
  /**
   * Test to give item to Cleric
   */
  void giveToUnitClericTest();

  @Test
  /**
   * Test to give item to Hero
   */
  void giveToUnitHeroTest();

  @Test
  /**
   * Test to give item to Fighter
   */
  void giveToUnitFighterTest();

  @Test
  /**
   * Test to give item to SwordMaster
   */
  void giveToUnitSwordMasterTest();

  @Test
  /**
   * Test to give item to Sorcerer
   */
  void giveToUnitSorcererTest();

  @Test
  /**
   * Test to give item to Alpaca
   */
  void giveToUnitAlpacaTest();

  @Test
  /**
   * Test to attack to archer
   */
  void archerAttackTest();

  /**
   * Check attack of Sorcerer with item equipped is strong counter all the units excepted the sorcerer equipped
   */
  void checkSorcererAttack(IEquipableItem item);

  @Test
  /**
   * Test Sorcerer's attack
   */
  void sorcererAttackTest();

  /**
   * Check attack of Cleric with Staff cure others units and does not exceed the maximum
   */
  void checkClericAttack(IEquipableItem item);

  @Test
  /**
   * Test Cleric's attack
   */
  void clericAttackTest();

  /**
   * Test alpaca receive a attack of each unit
   */
  @Test
  void alpacaReceiveAttack();

  /**
   * Test when a unit receive a attack normal
   */
  @Test
  void receiveNormalAttackTest();

  /**
   * Test for attack resistant to unit
   */
  @Test
  void receiveWeaknessAttackTest();

  /**
   * Test for attack weak to unit
   */
  @Test
  void receiveResistantAttackTest();

  /**
   * Test for attack that recovery to unit
   */
  @Test
  void receiveRecoveryAttackTest();

  /**
   * @return unit test's item
   */
  IEquipableItem getTestItem();

}
