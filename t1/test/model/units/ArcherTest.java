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
    archer.addItem(bow);
    assertEquals(2 , archer.getItems().size());
    archer.equipItem(sword);
    assertEquals(bow, archer.getEquippedItem());
    archer.addItem(sword);
    assertEquals(3 , archer.getItems().size());
    archer.addItem(sword);
    assertEquals(3 , archer.getItems().size());
  }

  /**
   * This test use a archer, a bow, a axe
   * @Test Archer's equipped item, the attack, and the currentPoints
   */
  @Test
  @Override
  public void testCombat() { }

  @Override
  public void weaknessAttackTest() { }

  @Override
  public void resistantAttackTest() { }

  @Test
  @Override
  public void sameTypeUnitAttackTest() {
    checkSameTypeUnitAttack(getTargetArcher(),bow_p,getBow());
  }

  @Override
  public void archerAttackTest() { }

}