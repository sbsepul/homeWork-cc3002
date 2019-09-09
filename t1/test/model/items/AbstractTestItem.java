package model.items;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import model.map.Field;
import model.map.Location;
import model.units.IUnit;
import model.units.SwordMaster;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Defines some common methods for all the items tests
 *
 * @author Sebastian Sepulveda
 * @since 1.0
 */
public abstract class AbstractTestItem {

  protected String expectedName;
  protected int expectedPower;
  protected short expectedMinRange;
  protected short expectedMaxRange;
  protected SwordMaster swordMaster;
  protected SwordMaster wrongSwordMaster;
  protected SwordMaster wrongSecondSwordMaster;
  protected Sword sword_test;
  protected Sword sword_aw;
  protected Sword sword_bw;

  protected Field field;
  /**
   * Sets up the items to be tested
   */
  @BeforeEach
  public void setUp() {
    setField();
    setTestItem();
    setWrongRangeItem();
    setTestUnit();
    setTestEnemy();
  }

  /**
   * Sets up a correctly implemented item that's going to be tested
   */
  public abstract void setTestItem();

  /**
   * Sets up an item with wrong ranges setted.
   */
  public abstract void setWrongRangeItem();

  /**
   * Sets the unit that will be equipped with the test item
   */
  public abstract void setTestUnit();

  public void setTestEnemy(){
    swordMaster = new SwordMaster(10,1, field.getCell(2,0));
    wrongSwordMaster = new SwordMaster(0,1,field.getCell(0,1));
    wrongSecondSwordMaster = new SwordMaster(10,1,field.getCell(2,2));
    sword_test = new Sword("sword test", 10, 1,2);
    sword_aw = new Sword("sword wrong 1", 10, 1,2);
    sword_bw = new Sword("sword wrong 2", 10, 1,2);
    swordMaster.addItem(sword_test);
    swordMaster.equipItem(sword_test);
    wrongSwordMaster.addItem(sword_aw);
    wrongSecondSwordMaster.addItem(sword_bw);
    wrongSecondSwordMaster.equipItem(sword_bw);
    wrongSwordMaster.equipItem(sword_aw);
  }

  /**
   * Set up the game field
   */
  public void setField() {
    this.field = new Field();
    this.field.addCells(true, new Location(0, 0), new Location(0, 1), new Location(0, 2),
            new Location(1, 0), new Location(1, 1), new Location(1, 2), new Location(2, 0),
            new Location(2, 1), new Location(2, 2));
  }

  /**
   * Checks that the tested item cannot have ranges outside of certain bounds.
   */
  @Test
  public void incorrectRangeTest() {
    assertTrue(getWrongTestItem().getMinRange() >= 0);
    assertTrue(getWrongTestItem().getMaxRange() >= getWrongTestItem().getMinRange());
  }

  public abstract IEquipableItem getWrongTestItem();

  /**
   * Tests that the constructor for the tested item works properly
   */
  @Test
  public void constructorTest() {
    assertEquals(getExpectedName(), getTestItem().getName());
    assertEquals(getExpectedBasePower(), getTestItem().getPower());
    assertEquals(getExpectedMinRange(), getTestItem().getMinRange());
    assertEquals(getExpectedMaxRange(), getTestItem().getMaxRange());
  }

  /**
   * @return the expected name of the item
   */
  public String getExpectedName() {
    return expectedName;
  }

  /**
   * @return the item being tested
   */
  public abstract IEquipableItem getTestItem();

  /**
   * @return the expected power of the Item
   */
  public int getExpectedBasePower() {
    return expectedPower;
  }

  /**
   * @return the expected minimum range of the item
   */
  public int getExpectedMinRange() {
    return expectedMinRange;
  }

  /**
   * @return the expected maximum range of the item
   */
  public int getExpectedMaxRange() {
    return expectedMaxRange;
  }

  /**
   * Checks that the Item can be correctly equipped to a unit
   */
  @Test
  public void equippedToTest() {
    assertNull(getTestItem().getOwner());
    IUnit unit = getTestUnit();
    getTestItem().equipTo(unit);
    assertEquals(true , unit.equals(getTestItem().getOwner()));
  }

  public SwordMaster getTestEnemy(){
    return swordMaster;
  }

  public SwordMaster getTestFirstEnemyWrong(){
    return wrongSwordMaster;
  }

  public SwordMaster getTestSecondEnemyWrong(){
    return wrongSecondSwordMaster;
  }

  @Test
  public void magicAttackTest(){
    getTestUnit().addItem(getTestItem());
    getTestItem().equipTo(getTestUnit());
    assertEquals(10,getTestEnemy().getCurrentHitPoints());
    assertEquals(10,getTestUnit().getCurrentHitPoints());
    getTestItem().magicAttack(getTestEnemy().getEquippedItem());
    assertEquals(10,getTestEnemy().getCurrentHitPoints());
    assertEquals(10,getTestUnit().getCurrentHitPoints());
  }

  @Test
  public void canAttackTest(){
    assertEquals(false, getTestItem().canAttack(getTestItem()));
    assertNull(getTestItem().getOwner());
    IUnit unit = getTestUnit();
    getTestItem().equipTo(unit);
    assertEquals(false, getTestItem().canAttack(getTestFirstEnemyWrong().getEquippedItem()));
    assertEquals(false,getTestItem().canAttack(getTestSecondEnemyWrong().getEquippedItem()));
    assertEquals(2, getTestItem().getOwner().getLocation().distanceTo(getTestEnemy().getLocation()));
    assertEquals(true, getTestItem().canAttack(getTestEnemy().getEquippedItem()));
  }
  /**
   * @return a unit that can equip the item being tested
   */
  public abstract IUnit getTestUnit();

}
