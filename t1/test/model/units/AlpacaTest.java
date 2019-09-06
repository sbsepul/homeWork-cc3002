package model.units;

import model.items.IEquipableItem;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Test set for the alpaca unit
 *
 * @author Ignacio Slater Muñoz
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


  @Override
  public void testCombat() {
    assertEquals(50,targetArcher.getCurrentHitPoints());
    assertEquals(50, alpaca.getCurrentHitPoints());
    targetArcher.addItem(bow);
    targetArcher.equipItem(bow);
  }


}