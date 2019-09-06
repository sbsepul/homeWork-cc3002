package model.units;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

/**
 * @author Ignacio Slater Muñoz
 */
public class HeroTest extends AbstractTestUnit {

  private Hero hero;

  /**
   * Set up the main unit that's going to be tested in the test set
   */
  @Override
  public void setTestUnit() {
    hero = new Hero(50, 2, field.getCell(0, 0));
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
}