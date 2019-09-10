package model.units;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import model.items.IEquipableItem;
import model.items.Staff;
import org.junit.jupiter.api.Test;

/**
 * Test set for the Cleric unit
 *
 * @author Sebastian Sepulveda
 */
public class ClericTest extends AbstractTestUnit {

  private Cleric cleric;
  private Staff staff_p;

  /**
   * Set up the main unit that's going to be tested in the test set
   */
  @Override
  public void setTestUnit() {
    cleric = new Cleric(50, 2, field.getCell(0, 0));
    staff_p = new Staff("Staff_private", 20,1,2);
  }

  /**
   * @return the current unit being tested
   */
  @Override
  public IUnit getTestUnit() {
    return cleric;
  }

  @Test
  @Override
  public void equipStaffTest() {
    assertNull(cleric.getEquippedItem());
    cleric.equipItem(staff);
    assertNull(cleric.getEquippedItem());
    cleric.addItem(staff);
    cleric.equipItem(staff);
    assertEquals(staff, cleric.getEquippedItem());
    assertEquals(1, cleric.getItems().size());
    cleric.addItem(staff_p);
    assertEquals(2 , cleric.getItems().size());
    cleric.equipItem(axe);
    assertEquals(staff, cleric.getEquippedItem());
    cleric.addItem(sword);
    assertEquals(3 , cleric.getItems().size());
  }

  @Test
  @Override
  public void giveToUnitClericTest() {
    assertNull(getTestUnit().getEquippedItem());
    assertEquals(0, getTestUnit().getItems().size());
    getTestUnit().giveItem(getTargetArcherTrade(), staff_p);
    assertEquals(2, getTargetArcherTrade().getItems().size());
    assertEquals(false, getTargetArcherTrade().getItems().contains(staff_p));
    getTestUnit().addItem(staff_p);
    assertNull(getTestUnit().getEquippedItem());
    assertEquals(1, getTestUnit().getItems().size());
    assertEquals(true, getTestUnit().getItems().contains(staff_p));
    getTestUnit().equipItem(staff_p);
    assertEquals(staff_p, getTestUnit().getEquippedItem());
    getTestUnit().giveItem(getTargetArcherTrade(),staff_p);
    assertEquals(0,getTestUnit().getItems().size());
    //verify that staff_p equipped isn't
    assertNull(getTestUnit().getEquippedItem());
    assertEquals(3,getTargetArcherTrade().getItems().size());
    assertEquals(true, getTargetArcherTrade().getItems().contains(staff_p));
    getTestUnit().addItem(getAxeTrade());
    assertEquals(true, getTestUnit().getItems().contains(getAxeTrade()));
    getTestUnit().giveItem(getTargetArcherTrade(),getAxeTrade());
    assertEquals(true, getTestUnit().getItems().contains(getAxeTrade()));
    assertEquals(false,getTargetArcherTrade().getItems().contains(getAxeTrade()));
  }

  @Test
  @Override
  public void testCombat() {
    assertEquals(50, getTestUnit().getCurrentHitPoints(),EPSILON);
    assertEquals(50 ,getTargetFighter().getCurrentHitPoints(),EPSILON);
    getTargetFighter().addItem(getAxe());
    getTestUnit().addItem(staff_p);
    getTargetFighter().equipItem(getAxe());
    getTestUnit().equipItem(staff_p);
    getTargetFighter().attack(getTestUnit());
    assertEquals(50, getTargetFighter().getCurrentHitPoints(),EPSILON);
    assertEquals(30,getTestUnit().getCurrentHitPoints(),EPSILON);
    getTestUnit().receiveRecovery(staff);
    assertEquals(50, getTestUnit().getCurrentHitPoints(),EPSILON);
    //combat hero
    getTargetHero().addItem(getSpear());
    getTargetHero().equipItem(getSpear());
    getTargetHero().attack(getTestUnit());
    assertEquals(50, getTargetHero().getCurrentHitPoints(),EPSILON);
    assertEquals(30,getTestUnit().getCurrentHitPoints(),EPSILON);
    getTestUnit().receiveRecovery(staff);
    assertEquals(50, getTestUnit().getCurrentHitPoints(),EPSILON);
    //combat swordmaster
    getTargetSwordMaster().addItem(getSword());
    getTargetSwordMaster().equipItem(getSword());
    getTargetSwordMaster().attack(getTestUnit());
    assertEquals(50, getTargetSwordMaster().getCurrentHitPoints(),EPSILON);
    assertEquals(30,getTestUnit().getCurrentHitPoints(),EPSILON);
    getTestUnit().receiveRecovery(staff);
    assertEquals(50, getTestUnit().getCurrentHitPoints(),EPSILON);
  }

  @Test
  @Override
  public void weaknessAttackTest() {
    assertEquals(50,getTestUnit().getCurrentHitPoints(),EPSILON);
    assertEquals(50, getTargetSorcerer().getCurrentHitPoints(),EPSILON);
    getTestUnit().addItem(staff_p);
    getTestUnit().equipItem(staff_p);
    getTargetSorcerer().addItem(getLight());
    getTargetSorcerer().addItem(getDarkness());
    getTargetSorcerer().addItem(getSoul());
    assertEquals(20, getLight().getPower());
    getTargetSorcerer().equipItem(getSoul());
    //receive soul's attack
    staff_p.receiveSoulAttack(getSoul());
    assertEquals(20,getTestUnit().getCurrentHitPoints(),EPSILON);
    getTargetCleric().addItem(getStaff_normal());
    getTargetCleric().equipItem(getStaff_normal());
    getTargetCleric().attack(getTestUnit());
    //staff cure staff
    assertEquals(40, getTestUnit().getCurrentHitPoints(),EPSILON);
    getTargetSorcerer().changeEquippedItem(getDarkness());
    staff_p.receiveDarknessAttack(getDarkness());
    assertEquals(10,getTestUnit().getCurrentHitPoints(),EPSILON);
    getTargetCleric().attack(getTestUnit());
    assertEquals(30, getTestUnit().getCurrentHitPoints(),EPSILON);
    getTargetSorcerer().changeEquippedItem(getLight());
    staff_p.receiveLightAttack(getLight());
    assertEquals(0,getTestUnit().getCurrentHitPoints(),EPSILON);
    assertEquals(false, getLight().canAttack(staff_p));
  }

  @Override
  public void resistantAttackTest() { }

  @Override
  public void sameTypeUnitAttackTest() { }

  @Test
  @Override
  public void archerAttackTest() {
    //hp normal
    assertEquals(50, getTestUnit().getCurrentHitPoints(),EPSILON);
    assertEquals(50, getTargetCleric().getCurrentHitPoints(),EPSILON);
    //test unit with inventory
    getTestUnit().addItem(staff_p);
    getTestUnit().equipItem(staff_p);
    getTargetArcher().addItem(getBow());
    getTargetArcher().equipItem(getBow());
    staff_p.receiveBowAttack(getBow());
    assertEquals(50, getTargetArcher().getCurrentHitPoints(),EPSILON);
    assertEquals(30,getTestUnit().getCurrentHitPoints(),EPSILON);
  }

  @Test
  @Override
  public void sorcererAttackTest() {
    //hp normal
    assertEquals(50, getTestUnit().getCurrentHitPoints(),EPSILON);
    assertEquals(50, getTargetCleric().getCurrentHitPoints(),EPSILON);
    //test unit with inventory
    getTestUnit().addItem(staff_p);
    getTestUnit().equipItem(staff_p);
    getTargetSorcerer().addItem(getLight());
    getTargetSorcerer().equipItem(getLight());
    assertEquals(staff_p, getTestUnit().getEquippedItem());
    assertEquals(getLight(),getTargetSorcerer().getEquippedItem());
    getTargetSorcerer().attack(getTestUnit());
    assertEquals(20, getTestUnit().getCurrentHitPoints(),EPSILON);
    assertEquals(50, getTargetSorcerer().getCurrentHitPoints(),EPSILON);
  }

  @Test
  @Override
  public void clericAttackTest() {
    checkClericAttack(staff_p);
  }

  @Override
  public IEquipableItem getTestItem() {
    return staff_p;
  }
  @Test
  @Override
  public void alpacaReceiveAttack() {
    assertEquals(50, getTestUnit().getCurrentHitPoints(),EPSILON);
    assertEquals(50, getTargetAlpaca().getCurrentHitPoints(),EPSILON);
    getTestUnit().attack(getTargetAlpaca());
    assertEquals(50, getTestUnit().getCurrentHitPoints(),EPSILON);
    assertEquals(50, getTargetAlpaca().getCurrentHitPoints(),EPSILON);
  }
}