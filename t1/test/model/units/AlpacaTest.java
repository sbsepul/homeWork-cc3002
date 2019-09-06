package model.units;

import model.items.IEquipableItem;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Test set for the Alpaca unit
 *
 * @author Sebastian Sepulveda
 * @since 1.0
 */
public class AlpacaTest extends AbstractTestUnit {

  private Alpaca alpaca;

  @Override
  public void setTestUnit() {
    alpaca = new Alpaca(50, 2, field.getCell(0, 0));
  }

  @Override
  public Alpaca getTestUnit() {
    return alpaca;
  }

  @Test
  @Override
  public void testCombat() {
    assertEquals(50, getTargetFighter().getCurrentHitPoints());
    assertEquals(50, alpaca.getCurrentHitPoints());
    getTargetFighter().addItem(axe);
    getTargetFighter().equipItem(axe);
    assertEquals(axe,getTargetFighter().getEquippedItem());
    alpaca.addItem(sword);
    assertEquals(1, alpaca.getItems().size());
    alpaca.equipItem(sword);
    assertNull(alpaca.getEquippedItem());
    getTargetFighter().attack(alpaca);
    assertEquals(30, alpaca.getCurrentHitPoints());
    assertEquals(50,getTargetFighter().getCurrentHitPoints());
    alpaca.attack(getTargetFighter());
    assertEquals(50,getTargetFighter().getCurrentHitPoints());
  }

  @Override
  public void weaknessAttackTest() { }

  @Override
  public void resistantAttackTest() { }

  @Override
  public void sameTypeUnitAttackTest() { }

  @Override
  public void archerAttackTest() {

  }

}