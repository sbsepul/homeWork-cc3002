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

  @Test
  @Override
  public void giveToUnitFighterTest() {
    assertNull(getTestUnit().getEquippedItem());
    assertEquals(0, getTestUnit().getItems().size());
    getTestUnit().giveItem(getTargetClericTrade(), axe_p);
    assertEquals(2, getTargetClericTrade().getItems().size());
    assertEquals(false, getTargetClericTrade().getItems().contains(axe_p));
    getTestUnit().addItem(axe_p);
    assertNull(getTestUnit().getEquippedItem());
    assertEquals(1, getTestUnit().getItems().size());
    assertEquals(true, getTestUnit().getItems().contains(axe_p));
    getTestUnit().equipItem(axe_p);
    assertEquals(axe_p, getTestUnit().getEquippedItem());
    getTestUnit().giveItem(getTargetClericTrade(),axe_p);
    assertEquals(0,getTestUnit().getItems().size());
    //verify that axe_p equipped isn't
    assertNull(getTestUnit().getEquippedItem());
    assertEquals(3,getTargetClericTrade().getItems().size());
    assertEquals(true, getTargetClericTrade().getItems().contains(axe_p));
    getTestUnit().addItem(getAxeTrade());
    assertEquals(true, getTestUnit().getItems().contains(getAxeTrade()));
    getTestUnit().giveItem(getTargetClericTrade(),getAxeTrade());
    assertEquals(true, getTestUnit().getItems().contains(getAxeTrade()));
    assertEquals(false,getTargetClericTrade().getItems().contains(getAxeTrade()));
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

  @Test
  @Override
  public void archerAttackTest() {
    checkArcherAttack(axe_p);
  }

  @Test
  @Override
  public void sorcererAttackTest() {
    checkSorcererAttack(axe_p);
  }

  @Test
  @Override
  public void clericAttackTest() {
    checkClericAttack(axe_p);
  }
}