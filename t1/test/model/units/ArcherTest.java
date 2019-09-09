package model.units;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import model.items.Bow;
import model.items.IEquipableItem;
import org.junit.jupiter.api.Test;

/**
 * Test set for the Archer unit.
 *
 * @author Sebastian Sepulveda
 * @since 1.0
 */
public class ArcherTest extends AbstractTestUnit {

  private Archer archer;
  private Bow bow_p;

  /**
   * Set up the main unit that's going to be tested in the test set
   */
  @Override
  public void setTestUnit() {
    archer = new Archer(50, 2, field.getCell(0, 0));
    bow_p = new Bow("Bow_private", 20,2,3);
  }

  /**
   * @return the current unit being tested
   */
  @Override
  public IUnit getTestUnit() {
    return archer;
  }

  /**
   * Checks if the bow is equipped correctly to the unit
   * Besides, verify that a item added isn't equipped
   *
   */
  @Test
  @Override
  public void equipBowTest() {
    assertNull(archer.getEquippedItem());
    archer.equipItem(bow);
    assertNull(archer.getEquippedItem());
    archer.addItem(bow);
    archer.equipItem(bow);
    assertEquals(bow, archer.getEquippedItem());
    assertEquals(1, archer.getItems().size());
    archer.addItem(bow_p);
    assertEquals(2 , archer.getItems().size());
    archer.equipItem(sword);
    assertEquals(bow, archer.getEquippedItem());
    archer.addItem(sword);
    assertEquals(3 , archer.getItems().size());
    archer.addItem(sword);
    assertEquals(3 , archer.getItems().size());
  }

  @Test
  @Override
  public void giveToUnitArcherTest() {
    assertNull(archer.getEquippedItem());
    assertEquals(0, archer.getItems().size());
    archer.giveItem(getTargetArcherTrade(), bow_p);
    assertEquals(2, getTargetArcherTrade().getItems().size());
    assertEquals(false, getTargetArcherTrade().getItems().contains(bow_p));
    archer.addItem(bow_p);
    assertNull(archer.getEquippedItem());
    assertEquals(1, archer.getItems().size());
    assertEquals(true, archer.getItems().contains(bow_p));
    archer.equipItem(bow_p);
    assertEquals(bow_p, archer.getEquippedItem());
    archer.giveItem(getTargetArcherTrade(),bow_p);
    assertEquals(0,archer.getItems().size());
    //verify that bow_p equipped isn't
    assertNull(archer.getEquippedItem());
    assertEquals(3,getTargetArcherTrade().getItems().size());
    assertEquals(true, getTargetArcherTrade().getItems().contains(bow_p));
    archer.addItem(getAxeTrade());
    assertEquals(true, archer.getItems().contains(getAxeTrade()));
    archer.giveItem(getTargetArcherTrade(),getAxeTrade());
    assertEquals(true, archer.getItems().contains(getAxeTrade()));
    assertEquals(false,getTargetArcherTrade().getItems().contains(getAxeTrade()));
  }

  /**
   * This test use a archer, a bow, a axe
   * @Test Archer's equipped item, the attack, and the currentPoints
   */
  @Test
  @Override
  public void testCombat() {
    getTestUnit().addItem(bow_p);
    getTestUnit().equipItem(bow_p);
    getTargetFighter().addItem(getAxe());
    getTargetFighter().equipItem(getAxe());
    getTargetHero().addItem(getSpear());
    getTargetHero().equipItem(getSpear());
    getTargetSwordMaster().addItem(getSword());
    getTargetSwordMaster().equipItem(getSword());
    //combat Fighter
    getTargetFighter().attack(getTestUnit());
    assertEquals(50, getTargetFighter().getCurrentHitPoints());
    assertEquals(30,getTestUnit().getCurrentHitPoints());
    getTargetArcher().addItem(getBow());
    getTargetArcher().equipItem(getBow());
    getTargetFighter().attack(getTargetArcher());
    assertEquals(30, getTargetFighter().getCurrentHitPoints());
    assertEquals(30,getTargetArcher().getCurrentHitPoints());
    getTestUnit().receiveRecovery(getStaff_normal());
    //combat SwordMaster
    getTargetSwordMaster().attack(getTestUnit());
    assertEquals(50, getTargetSwordMaster().getCurrentHitPoints());
    assertEquals(30,getTestUnit().getCurrentHitPoints());
    getTargetSwordMaster().attack(getTargetArcher());
    assertEquals(30, getTargetSwordMaster().getCurrentHitPoints());
    assertEquals(10,getTargetArcher().getCurrentHitPoints());
    //combat Hero
    getTargetArcher().receiveRecovery(getStaff_normal());
    getTargetHero().attack(getTestUnit());
    assertEquals(50,getTargetHero().getCurrentHitPoints());
    assertEquals(10,getTestUnit().getCurrentHitPoints());
    getTargetHero().attack(getTargetArcher());
    assertEquals(30, getTargetHero().getCurrentHitPoints());
    assertEquals(10,getTargetArcher().getCurrentHitPoints());

  }

  @Override
  public void weaknessAttackTest() {

  }

  @Override
  public void resistantAttackTest() { }

  @Test
  @Override
  public void sameTypeUnitAttackTest() {
    checkSameTypeUnitAttack(getTargetArcher(),bow_p,getBow());
  }

  @Override
  public void archerAttackTest() { }

  @Test
  @Override
  public void sorcererAttackTest() {
    //hp normal
    assertEquals(50, archer.getCurrentHitPoints());
    assertEquals(50, getTargetCleric().getCurrentHitPoints());
    //test unit with inventory
    archer.addItem(bow_p);
    archer.equipItem(bow_p);
    getTargetSorcerer().addItem(getLight());
    getTargetSorcerer().addItem(getDarkness());
    getTargetSorcerer().addItem(getSoul());
    getTargetSorcerer().equipItem(getLight());
    assertEquals(bow_p, archer.getEquippedItem());
    assertEquals(getLight(),getTargetSorcerer().getEquippedItem());
    //combat with light
    getTargetSorcerer().attack(archer);
    assertEquals(20, archer.getCurrentHitPoints());
    assertEquals(50, getTargetSorcerer().getCurrentHitPoints());
    getTargetArcher().addItem(getBow());
    getTargetArcher().equipItem(getBow());
    getTargetSorcerer().attack(getTargetArcher());
    assertEquals(20,getTargetArcher().getCurrentHitPoints());
    assertEquals(20, getTargetSorcerer().getCurrentHitPoints());
    archer.receiveRecovery(getStaff());
    getTargetSorcerer().receiveRecovery(getStaff());
    getTargetArcher().receiveRecovery(getStaff());
    assertEquals(50, archer.getCurrentHitPoints());
    assertEquals(50, getTargetSorcerer().getCurrentHitPoints());
    assertEquals(50, getTargetArcher().getCurrentHitPoints());
    //combat with darkness
    getTargetSorcerer().changeEquippedItem(getDarkness());
    getTargetSorcerer().attack(archer);
    assertEquals(20, archer.getCurrentHitPoints());
    assertEquals(50, getTargetSorcerer().getCurrentHitPoints());
    // with target archer
    getTargetSorcerer().attack(getTargetArcher());
    assertEquals(20,getTargetArcher().getCurrentHitPoints());
    assertEquals(20, getTargetSorcerer().getCurrentHitPoints());
    archer.receiveRecovery(getStaff());
    getTargetSorcerer().receiveRecovery(getStaff());
    getTargetArcher().receiveRecovery(getStaff());
    assertEquals(50, archer.getCurrentHitPoints());
    assertEquals(50, getTargetSorcerer().getCurrentHitPoints());
    assertEquals(50, getTargetArcher().getCurrentHitPoints());
    //combat with soul
    getTargetSorcerer().changeEquippedItem(getSoul());
    getTargetSorcerer().attack(archer);
    assertEquals(20, archer.getCurrentHitPoints());
    assertEquals(50, getTargetSorcerer().getCurrentHitPoints());
    //combat archer counterAttack to sorcerer
    assertEquals(2,getTargetArcher().getLocation().distanceTo(getTargetSorcerer().getLocation()));
    getTargetSorcerer().attack(getTargetArcher());
    assertEquals(20,getTargetSorcerer().getCurrentHitPoints());
    assertEquals(20,getTargetArcher().getCurrentHitPoints());
    getTargetSorcerer().receiveRecovery(getStaff());
    getTargetArcher().receiveRecovery(getStaff());
    assertEquals(50, getTargetSorcerer().getCurrentHitPoints());
    assertEquals(50, getTargetArcher().getCurrentHitPoints());
    getTargetArcher().attack(getTargetSorcerer());
    assertEquals(20,getTargetSorcerer().getCurrentHitPoints());
    assertEquals(20,getTargetArcher().getCurrentHitPoints());

  }

  @Test
  @Override
  public void clericAttackTest() {
    checkClericAttack(bow_p);
  }

  @Override
  public IEquipableItem getTestItem() {
    return bow_p;
  }

}