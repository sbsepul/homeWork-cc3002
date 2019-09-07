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
    cleric.addItem(staff);
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

  @Override
  public void testCombat() { }

  @Override
  public void weaknessAttackTest() { }

  @Override
  public void resistantAttackTest() { }

  @Override
  public void sameTypeUnitAttackTest() { }

  @Override
  public void archerAttackTest() { }

  @Test
  @Override
  public void sorcererAttackTest() {
    //hp normal
    assertEquals(50, getTestUnit().getCurrentHitPoints());
    assertEquals(50, getTargetCleric().getCurrentHitPoints());
    //test unit with inventory
    getTestUnit().addItem(staff_p);
    getTestUnit().equipItem(staff_p);
    getTargetSorcerer().addItem(getLight());
    getTargetSorcerer().equipItem(getLight());
    assertEquals(staff_p, getTestUnit().getEquippedItem());
    assertEquals(getLight(),getTargetSorcerer().getEquippedItem());
    getTargetSorcerer().attack(getTestUnit());
    assertEquals(20, getTestUnit().getCurrentHitPoints());
    assertEquals(50, getTargetSorcerer().getCurrentHitPoints());
  }

  @Test
  @Override
  public void clericAttackTest() {
    checkClericAttack(staff_p);
  }
}