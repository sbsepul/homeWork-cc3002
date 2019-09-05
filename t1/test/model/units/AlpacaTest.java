package model.units;

import model.items.IEquipableItem;

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
  public void checkEquippedItem(IEquipableItem item) {
    super.checkEquippedItem(item);
    item = axe;
    super.checkEquippedItem(item);
  }

  @Override
  public void testCombat() {

  }


}