package model.units;

import model.items.IEquipableItem;
import org.junit.jupiter.api.Test;

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
  public Archer getTargetArcher() {
    return super.getTargetArcher();
  }

  @Test
  @Override
  public void testCombat() {
    assertEquals(50, getTargetArcher().getCurrentHitPoints());
    assertEquals(50, alpaca.getCurrentHitPoints());
    getTargetArcher().addItem(bow);
    getTargetArcher().equipItem(bow);
    alpaca.addItem(axe);
    alpaca.equipItem(axe);
    assertNull(alpaca.getEquippedItem());
    getTargetArcher().attack(alpaca);
    assertEquals(40, alpaca.getCurrentHitPoints());
    assertEquals(50,getTargetArcher().getCurrentHitPoints());
    alpaca.attack(getTargetArcher());
    assertEquals(50,getTargetArcher().getCurrentHitPoints());

  }


}