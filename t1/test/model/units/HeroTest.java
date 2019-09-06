package model.units;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import model.items.IEquipableItem;
import model.items.Spear;
import org.junit.jupiter.api.Test;

/**
 * Test set for the Hero unit
 *
 * @author Sebastian Sepulveda
 */
public class HeroTest extends AbstractTestUnit {

  private Hero hero;
  private Spear spear_p;

  /**
   * Set up the main unit that's going to be tested in the test set
   */
  @Override
  public void setTestUnit() {
    hero = new Hero(50, 2, field.getCell(0, 0));
    spear_p = new Spear("Spear_private", 20, 1,2);
  }

  /**
   * @return the current unit being tested
   */
  @Override
  public IUnit getTestUnit() {
    return hero;
  }

  @Test
  @Override
  public void equipSpearTest() {
    assertNull(hero.getEquippedItem());
    hero.equipItem(spear);
    assertNull(hero.getEquippedItem());
    hero.addItem(spear);
    hero.equipItem(spear);
    assertEquals(spear, hero.getEquippedItem());
    assertEquals(1, hero.getItems().size());
    hero.addItem(sword);
    assertEquals(2 , hero.getItems().size());
    hero.equipItem(axe);
    assertEquals(spear, hero.getEquippedItem());
    hero.addItem(staff);
    assertEquals(3 , hero.getItems().size());
  }

  @Override
  public void testCombat() {

  }

  @Test
  @Override
  public void weaknessAttackTest() {
    checkWeaknessAttack(getTargetFighter(),getSpear(),getAxe());
  }

  @Test
  @Override
  public void resistantAttackTest() {
    checkResistantAttack(getTargetSwordMaster(),getSpear(),getSword());
  }

  @Test
  @Override
  public void sameTypeUnitAttackTest() {
    checkSameTypeUnitAttack(getTargetHero(), spear_p, getSpear());
  }

}