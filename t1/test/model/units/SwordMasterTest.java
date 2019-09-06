package model.units;

import model.items.IEquipableItem;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * @author Sebastian Sepulveda
 */
public class SwordMasterTest extends AbstractTestUnit {

  private SwordMaster swordMaster;

  /**
   * Set up the main unit that's going to be tested in the test set
   */
  @Override
  public void setTestUnit() {
    swordMaster = new SwordMaster(50, 2, field.getCell(0, 0));
  }

  /**
   * @return the current unit being tested
   */
  @Override
  public IUnit getTestUnit() {
    return swordMaster;
  }

  @Test
  @Override
  public void equipSwordTest() {
    assertNull(swordMaster.getEquippedItem());
    swordMaster.equipItem(sword);
    assertNull(swordMaster.getEquippedItem());
    swordMaster.addItem(sword);
    swordMaster.equipItem(sword);
    assertEquals(sword, swordMaster.getEquippedItem());
    assertEquals(1, swordMaster.getItems().size());
    swordMaster.addItem(sword);
    assertEquals(2 , swordMaster.getItems().size());
    swordMaster.equipItem(axe);
    assertEquals(sword, swordMaster.getEquippedItem());
    swordMaster.addItem(sword);
    assertEquals(3 , swordMaster.getItems().size());
  }

  @Override
  public void testCombat() {

  }

  @Test
  @Override
  public void weaknessAttack() {
    checkWeaknessAttackTest(getTargetHero(), getSword(), getSpear());
  }

  @Override
  public void resistantAttack() {
    
  }
}