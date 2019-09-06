package model.units;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import model.items.Axe;
import org.junit.jupiter.api.Test;

/**
 * Test set for the Fighter unit
 *
 * @author Sebastian Sepulveda
 */
public class FighterTest extends AbstractTestUnit {

  private Fighter fighter;
  private Axe axe_p;

  /**
   * Set up the main unit that's going to be tested in the test set
   */
  @Override
  public void setTestUnit() {
    fighter = new Fighter(50, 2, field.getCell(0, 0));
    axe_p = new Axe("Axe_private",20,1,2);
  }

  /**
   * @return the current unit being tested
   */
  @Override
  public IUnit getTestUnit() {
    return fighter;
  }

  /**
   * Checks if the axe is equipped correctly to the unit
   */
  @Test
  @Override
  public void equipAxeTest() {
    assertNull(fighter.getEquippedItem());
    fighter.equipItem(axe);
    assertNull(fighter.getEquippedItem());
    fighter.addItem(axe);
    fighter.equipItem(axe);
    assertEquals(axe, fighter.getEquippedItem());
    assertEquals(1, fighter.getItems().size());
    fighter.addItem(staff);
    assertEquals(2 , fighter.getItems().size());
    fighter.equipItem(spear);
    assertEquals(axe, fighter.getEquippedItem());
    fighter.addItem(sword);
    assertEquals(3 , fighter.getItems().size());
  }

  @Override
  public void testCombat() {

  }

  @Test
  @Override
  public void weaknessAttackTest() {
    checkWeaknessAttack(getTargetSwordMaster(),getAxe(),getSword());
  }

  @Test
  @Override
  public void resistantAttackTest() {
    checkResistantAttack(getTargetHero(), getAxe(), getSpear());
  }

  @Test
  @Override
  public void sameTypeUnitAttackTest() {
    checkSameTypeUnitAttack(getTargetFighter(),axe_p, getAxe());
  }
}