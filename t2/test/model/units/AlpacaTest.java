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
    assertEquals(50, getTargetFighter().getCurrentHitPoints(),EPSILON);
    assertEquals(50, alpaca.getCurrentHitPoints(),EPSILON);
    getTargetFighter().addItem(axe);
    getTargetFighter().equipItem(axe);
    assertEquals(axe,getTargetFighter().getEquippedItem());
    alpaca.addItem(sword);
    assertEquals(1, alpaca.getItems().size());
    alpaca.equipItem(sword);
    assertNull(alpaca.getEquippedItem());
    getTargetFighter().attack(alpaca);
    assertEquals(30, alpaca.getCurrentHitPoints(),EPSILON);
    assertEquals(50,getTargetFighter().getCurrentHitPoints(),EPSILON);
    alpaca.attack(getTargetFighter());
    assertEquals(50,getTargetFighter().getCurrentHitPoints(),EPSILON);
  }

  @Override
  public void weaknessAttackTest() { }

  @Override
  public void resistantAttackTest() { }

  @Override
  public void sameTypeUnitAttackTest() { }

  @Override
  public void archerAttackTest() { }

  @Test
  @Override
  public void sorcererAttackTest() {
    //hp normal
    assertEquals(50, getTestUnit().getCurrentHitPoints(),EPSILON);
    assertEquals(50, getTargetCleric().getCurrentHitPoints(),EPSILON);
    //test unit with inventory
    alpaca.addItem(getAxe());
    alpaca.equipItem(getAxe());
    assertNull(alpaca.getEquippedItem());
    getTargetSorcerer().addItem(getLight());
    getTargetSorcerer().equipItem(getLight());
    assertEquals(getLight(),getTargetSorcerer().getEquippedItem());
    getTargetSorcerer().attack(alpaca);
    assertEquals(30, alpaca.getCurrentHitPoints(),EPSILON);
    assertEquals(50, getTargetSorcerer().getCurrentHitPoints(),EPSILON);
  }

  @Override
  public void clericAttackTest() { }

  @Override
  public IEquipableItem getTestItem() {
    return null;
  }

  @Test
  public void equipItemBow(){
    getTestUnit().addItem(getBow());
    assertEquals(1,getTestUnit().getItems().size());
    assertNull(getTestUnit().getEquippedItem());
    getTestUnit().equipItemBow(getBow());
    assertNull(getTestUnit().getEquippedItem());
  }
  @Test
  public void equipItemAxe(){
    getTestUnit().addItem(getAxe());
    assertEquals(1,getTestUnit().getItems().size());
    assertNull(getTestUnit().getEquippedItem());
    getTestUnit().equipItemAxe(getAxe());
    assertNull(getTestUnit().getEquippedItem());
  }
  @Test
  public void equipItemLight(){
    getTestUnit().addItem(getLight());
    assertEquals(1,getTestUnit().getItems().size());
    assertNull(getTestUnit().getEquippedItem());
    getTestUnit().equipItemLight(getLight());
    assertNull(getTestUnit().getEquippedItem());
  }
  @Test
  public void equipItemDarkness(){
    getTestUnit().addItem(getDarkness());
    assertEquals(1,getTestUnit().getItems().size());
    assertNull(getTestUnit().getEquippedItem());
    getTestUnit().equipItemDarkness(getDarkness());
    assertNull(getTestUnit().getEquippedItem());
  }
  @Test
  public void equipItemSoul(){
    getTestUnit().addItem(getSoul());
    assertEquals(1,getTestUnit().getItems().size());
    assertNull(getTestUnit().getEquippedItem());
    getTestUnit().equipItemSoul(getSoul());
    assertNull(getTestUnit().getEquippedItem());
  }
  @Test
  public void equipItemSword(){
    getTestUnit().addItem(getSword());
    assertEquals(1,getTestUnit().getItems().size());
    assertNull(getTestUnit().getEquippedItem());
    getTestUnit().equipItemSword(getSword());
    assertNull(getTestUnit().getEquippedItem());
  }

  @Test
  public void equipItemStaff(){
    getTestUnit().addItem(getStaff());
    assertEquals(1,getTestUnit().getItems().size());
    assertNull(getTestUnit().getEquippedItem());
    getTestUnit().equipItemStaff(getStaff());
    assertNull(getTestUnit().getEquippedItem());
  }

  @Test
  public void equipItemSpear(){
    getTestUnit().addItem(getSpear());
    assertEquals(1,getTestUnit().getItems().size());
    assertNull(getTestUnit().getEquippedItem());
    getTestUnit().equipItemSpear(getSpear());
    assertNull(getTestUnit().getEquippedItem());
  }

  @Test
  @Override
  public void alpacaReceiveAttack() {
    assertEquals(50, getTestUnit().getCurrentHitPoints(),EPSILON);
    assertEquals(50, getTargetAlpaca().getCurrentHitPoints(),EPSILON);
    getTestUnit().attack(getTargetAlpaca());
    assertEquals(50, getTestUnit().getCurrentHitPoints(),EPSILON);
    assertEquals(50, getTargetAlpaca().getCurrentHitPoints(),EPSILON);
  }
}