package model.units;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import model.items.IEquipableItem;
import org.junit.jupiter.api.Test;

/**
 * Test set for the Cleric unit
 *
 * @author Sebastian Sepulveda
 */
public class ClericTest extends AbstractTestUnit {

  private Cleric cleric;

  /**
   * Set up the main unit that's going to be tested in the test set
   */
  @Override
  public void setTestUnit() {
    cleric = new Cleric(50, 2, field.getCell(0, 0));
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

  @Override
  public void testCombat() {

  }

  @Override
  public void weaknessAttackTest() {  }

  @Override
  public void resistantAttackTest() {

  }

}