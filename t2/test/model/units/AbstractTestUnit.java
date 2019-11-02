/*
 * The MIT License
 *
 * Copyright (c) 2019 Google, Inc. http://angularjs.org
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package model.units;

import model.items.*;
import model.items.Axe;
import model.items.Bow;
import model.items.Spear;
import model.items.Staff;
import model.items.Sword;
import model.items.magic.Darkness;
import model.items.magic.Light;
import model.items.magic.Soul;
import model.map.Field;
import model.map.Location;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.print.attribute.standard.MediaSize;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Sebastian Sepulveda
 * @since 1.0
 */
public abstract class AbstractTestUnit implements ITestUnit {

  protected Alpaca targetAlpaca;
  protected Alpaca targetAlpacaTrade;
  protected Archer targetArcher;
  protected Archer targetArcherTrade;
  protected Cleric targetCleric;
  protected Cleric targetClericTrade;
  protected Fighter targetFighter;
  protected Hero targetHero;
  protected Sorcerer targetSorcerer;
  protected Sorcerer targetSorcerer_withItems;
  protected SwordMaster targetSwordMaster;
  /* ITEMS */
  protected Bow bow;
  protected Bow bow_trade;
  protected Axe axe;
  protected Axe axe_trade;
  protected Axe getAxe_trade;
  protected Sword sword;
  protected Staff staff;
  private Staff staff_normal;
  protected Spear spear;
  protected Darkness darkness;
  protected Light light;
  protected Soul soul;
  /* END ITEMS */
  protected Field field;
  protected static final double EPSILON = 1e-6;

  /**
   * Set up the units that help to prove attacks and exchanges
   */
  public void setTargets(){
    targetAlpaca = new Alpaca(50, 2, field.getCell(1, 1), axe_trade);
    targetArcher = new Archer(50,2,field.getCell(1,2),axe_trade,light);
    targetCleric = new Cleric(50,2,field.getCell(1,1),sword,darkness);
    targetFighter = new Fighter(50,2,field.getCell(1,0),bow_trade,soul);
    targetHero = new Hero(50,2,field.getCell(0,1),staff,soul);
    targetSorcerer = new Sorcerer(50,2,field.getCell(0,1));
    targetSorcerer_withItems = new Sorcerer(50,2,field.getCell(0,1),axe_trade,sword);
    targetSwordMaster = new SwordMaster(50,2,field.getCell(1,0),bow_trade,spear);
    targetClericTrade = new Cleric(50,2,field.getCell(0,1),sword,darkness);
    targetArcherTrade = new Archer(50,2,field.getCell(1,0),spear,staff);
    targetAlpacaTrade = new Alpaca(50,2,field.getCell(1,0),bow_trade,light);
  }

  /**
   * Sets up the units and weapons to be tested
   */
  @BeforeEach
  public void setUp() {
    setField();
    setTestUnit();
    setWeapons();
    setTargets();
  }

  /**
   * Set up the game field
   */
  @Override
  public void setField() {
    this.field = new Field();
    this.field.addCells(true, new Location(0, 0), new Location(0, 1), new Location(0, 2),
        new Location(1, 0), new Location(1, 1), new Location(1, 2), new Location(2, 0),
        new Location(2, 1), new Location(2, 2));
  }

  /**
   * Set up the main unit that's going to be tested in the test set
   */
  @Override
  public abstract void setTestUnit();

  /**
   * Creates a set of testing weapons
   */
  @Override
  public void setWeapons() {
    this.axe = new Axe("Axe", 20, 1, 2);
    this.axe_trade = new Axe("Axe_trade", 20, 1, 2);
    this.sword = new Sword("Sword", 20, 1, 2);
    this.spear = new Spear("Spear", 20, 1, 2);
    this.staff = new Staff("Staff", 30, 1, 2);
    this.staff_normal = new Staff("Staff_Normal", 20, 1, 2);
    this.bow = new Bow("Bow", 20, 2, 3);
    this.darkness = new Darkness( "Darkness", 20,1,2);
    this.light = new Light( "Light", 20,1,2);
    this.soul = new Soul( "Soul", 20,1,2);
    this.bow_trade = new Bow("Bow_trade",20,1,2);
    this.getAxe_trade = new Axe("Axe_give",20,1,2);
  }

  /**
   * Checks that the constructor works properly.
   */
  @Override
  @Test
  public void constructorTest() {
    assertEquals(50, getTestUnit().getCurrentHitPoints(),EPSILON);
    assertEquals(2, getTestUnit().getMovement());
    assertEquals(50, getTestUnit().getMaxCurrentHitPoints());
    assertEquals(new Location(0, 0), getTestUnit().getLocation());
    assertTrue(getTestUnit().getItems().isEmpty());
    assertNull(getTestUnit().getEquippedItem());
  }

  /**
   * @return the current unit being tested
   */
  @Override
  public abstract IUnit getTestUnit();

  /**
   * Tries to equip a weapon to anything unit and verifies that it was not equipped
   * Besides, verify that a item added isn't equipped, but is in the inventory
   *
   * @param item
   *     to be equipped
   */
  @Override
  public void checkEquippedItem(IEquipableItem item) {
    assertNull(getTestUnit().getEquippedItem());
    assertEquals(0, getTestUnit().getItems().size());

    getTestUnit().equipItem(item);
    assertEquals(0,getTestUnit().getItems().size());
    assertNull(getTestUnit().getEquippedItem());

    getTestUnit().addItem(item);
    assertEquals(1, getTestUnit().getItems().size());

    getTestUnit().equipItem(item);
    assertNull(getTestUnit().getEquippedItem());
  }

  /**
   * Check that a unit can give a item of his inventory
   * Verify
   *
   * @param unit
   */
  @Override
  public void checkGiveItem(IUnit unit) {
    assertNull(getTestUnit().getEquippedItem());
    assertEquals(0, getTestUnit().getItems().size());
    getTestUnit().giveItem(unit, getBow());
    assertEquals(2, unit.getItems().size());
    assertEquals(false, unit.getItems().contains(getBow()));
    getTestUnit().addItem(getBow());
    assertNull(getTestUnit().getEquippedItem());
    assertEquals(1, getTestUnit().getItems().size());
    assertEquals(true, getTestUnit().getItems().contains(getBow()));
    getTestUnit().giveItem(unit,getBow());
    assertEquals(0,getTestUnit().getItems().size());
    assertEquals(3,unit.getItems().size());
    assertEquals(true, unit.getItems().contains(getBow()));
    assertEquals(true, unit.isItemFull());
    getTestUnit().addItem(getAxeTrade());
    assertEquals(true, getTestUnit().getItems().contains(getAxeTrade()));
    getTestUnit().giveItem(unit,getAxeTrade());
    assertEquals(true, getTestUnit().getItems().contains(getAxeTrade()));
    assertEquals(false,unit.getItems().contains(getAxeTrade()));
  }

  @Test
  @Override
  public void giveToUnitArcherTest() {
    checkGiveItem(getTargetArcherTrade());
  }

  @Test
  @Override
  public void giveToUnitClericTest(){
    checkGiveItem(getTargetClericTrade());
  }

  @Test
  @Override
  public void giveToUnitHeroTest() {
    checkGiveItem(getTargetHero());
  }
  @Test
  @Override
  public void giveToUnitFighterTest() {
    checkGiveItem(getTargetFighter());
  }

  @Test
  @Override
  public void giveToUnitSorcererTest() {
    checkGiveItem(getTargetSorcerer_withItems());
  }

  @Test
  @Override
  public void giveToUnitSwordMasterTest() {
    checkGiveItem(getTargetSwordMaster());
  }

  @Test
  @Override
  public void giveToUnitAlpacaTest() {
    assertNull(getTestUnit().getEquippedItem());
    assertEquals(0, getTestUnit().getItems().size());
    getTestUnit().giveItem(getTargetAlpacaTrade(), getBow());
    assertEquals(2, getTargetAlpacaTrade().getItems().size());
    assertEquals(false, getTargetAlpacaTrade().getItems().contains(getBow()));
    getTestUnit().addItem(getBow());
    assertNull(getTestUnit().getEquippedItem());
    assertEquals(1, getTestUnit().getItems().size());
    assertEquals(true, getTestUnit().getItems().contains(getBow()));
    getTestUnit().giveItem(getTargetAlpacaTrade(),getBow());
    assertEquals(0,getTestUnit().getItems().size());
    assertEquals(3,getTargetAlpacaTrade().getItems().size());
    assertEquals(true, getTargetAlpacaTrade().getItems().contains(getBow()));
    assertEquals(false, getTargetAlpacaTrade().isItemFull());
    getTestUnit().addItem(getAxe());
    assertEquals(true, getTestUnit().getItems().contains(getAxe()));
    getTestUnit().giveItem(getTargetAlpacaTrade(),getAxe());
    assertEquals(false, getTestUnit().getItems().contains(getAxe()));
    assertEquals(true,getTargetAlpacaTrade().getItems().contains(getAxe()));
  }

  /**
   * Checks if the axe is equipped correctly to the unit
   */
  @Override
  @Test
  public void equipAxeTest() {
    checkEquippedItem(getAxe());
  }

  /**
   * @return the test axe
   */
  @Override
  public Axe getAxe() {
    return axe;
  }

  @Override
  public Axe getAxeTrade() {
    return getAxe_trade;
  }

  @Override
  @Test
  public void equipSwordTest() {
    checkEquippedItem(getSword());
  }

  /**
   * @return the test sword
   */
  @Override
  public Sword getSword() {
    return sword;
  }

  @Override
  @Test
  public void equipSpearTest() {
    checkEquippedItem(getSpear());
  }

  /**
   * @return the test spear
   */
  @Override
  public Spear getSpear() {
    return spear;
  }

  @Override
  @Test
  public void equipStaffTest() {
    checkEquippedItem(getStaff());
  }

  /**
   * @return the test staff
   */
  @Override
  public Staff getStaff() {
    return staff;
  }

  /**
   * @return the test staff normal
   */
  public Staff getStaff_normal() { return staff_normal; }

  @Override
  @Test
  public void equipBowTest() {
    checkEquippedItem(getBow());
  }

  /**
   * @return the test bow
   */
  @Override
  public Bow getBow() {
    return bow;
  }

  @Override
  @Test
  public void equipDarknessTest() {
    checkEquippedItem(getDarkness());
  }

  /**
   * @return the test darkness
   */
  @Override
  public Darkness getDarkness() {
    return darkness;
  }

  @Override
  @Test
  public void equipLightTest() {
    checkEquippedItem(getLight());
  }

  /**
   * @return the test light
   */
  @Override
  public Light getLight() {
    return light;
  }

  @Override
  @Test
  public void equipSoulTest() {
    checkEquippedItem(getSoul());
  }

  /**
   * @return the test soul
   */
  @Override
  public Soul getSoul() {
    return soul;
  }

  /**
   * Checks if the unit moves correctly
   */
  @Override
  @Test
  public void testMovement() {
    getTestUnit().moveTo(getField().getCell(2, 2));
    assertEquals(new Location(0, 0), getTestUnit().getLocation());

    getTestUnit().moveTo(getField().getCell(0, 2));
    assertEquals(new Location(0, 2), getTestUnit().getLocation());

    getField().getCell(0, 1).setUnit(getTargetAlpaca());
    getTestUnit().moveTo(getField().getCell(0, 1));
    assertEquals(new Location(0, 2), getTestUnit().getLocation());
  }

  /**
   * @return the test field
   */
  @Override
  public Field getField() {
    return field;
  }

  /**
   * @return the target Alpaca
   */
  @Override
  public Alpaca getTargetAlpaca() {
    return targetAlpaca;
  }

  /**
   * @return the target Alpaca for trade
   */
  @Override
  public Alpaca getTargetAlpacaTrade() {
    return targetAlpacaTrade;
  }

  /**
   * @return the target Archer
   */
  @Override
  public Archer getTargetArcher() {
    return targetArcher;
  }

  /**
   * @return the target Archer for exchange
   */
  @Override
  public Archer getTargetArcherTrade() {
    return targetArcherTrade;
  }

  /**
   * @return the target Cleric
   */
  @Override
  public Cleric getTargetCleric() {
    return targetCleric;
  }

  /**
   * @return the target Cleric for trade
   */
  @Override
  public Cleric getTargetClericTrade() {
    return targetClericTrade;
  }

  /**
   * @return the target Fighter
   */
  @Override
  public Fighter getTargetFighter() {
    return targetFighter;
  }

  /**
   * @return the target Hero
   */
  @Override
  public Hero getTargetHero() {
    return targetHero;
  }

  /**
   * @return the target Sorcerer
   */
  @Override
  public Sorcerer getTargetSorcerer() {
    return targetSorcerer;
  }

  /**
   * @return the target Sorcerer for exchange items
   */
  @Override
  public Sorcerer getTargetSorcerer_withItems() {
    return targetSorcerer_withItems;
  }

  /**
   * @return the target SwordMaster
   */
  @Override
  public SwordMaster getTargetSwordMaster() {
    return targetSwordMaster;
  }

  @Override
  public void checkWeaknessAttack(IUnit unit, IEquipableItem itemA, IEquipableItem itemB) {
    assertEquals(50, getTestUnit().getCurrentHitPoints(),EPSILON);
    assertEquals(50, unit.getCurrentHitPoints(),EPSILON);
    getTestUnit().addItem(itemA);
    getTestUnit().equipItem(itemA);
    unit.addItem(itemB);
    unit.equipItem(itemB);
    assertEquals(itemA, getTestUnit().getEquippedItem());
    assertEquals(itemB, unit.getEquippedItem());
    getTestUnit().attack(unit);
    //unit is resistant to getTestUnit: 20 points of damage to test unit, 0 to unit
    assertEquals(50, unit.getCurrentHitPoints(),EPSILON);
    assertEquals(20,getTestUnit().getCurrentHitPoints(),EPSILON);
    getTargetCleric().addItem(getStaff());
    getTargetCleric().equipItem(getStaff());
    assertEquals(getStaff(),getTargetCleric().getEquippedItem());
    getTargetCleric().attack(getTestUnit());
    assertEquals(50,getTestUnit().getCurrentHitPoints(),EPSILON);
    assertEquals(50,getTargetCleric().getCurrentHitPoints(),EPSILON);
    unit.removeItem(itemB);
    getTestUnit().removeItem(itemA);
  }

  @Override
  public void checkResistantAttack(IUnit unit, IEquipableItem itemA, IEquipableItem itemB) {
    assertEquals(50, getTestUnit().getCurrentHitPoints(),EPSILON);
    assertEquals(50, unit.getCurrentHitPoints(),EPSILON);
    getTestUnit().addItem(itemA);
    getTestUnit().equipItem(itemA);
    unit.addItem(itemB);
    unit.equipItem(itemB);
    assertEquals(itemA, getTestUnit().getEquippedItem());
    assertEquals(itemB, unit.getEquippedItem());
    getTestUnit().attack(unit);
    //getTestUnit is resistant to unit: 20 points of damage to unit, 0 to test unit
    assertEquals(20, unit.getCurrentHitPoints(),EPSILON);
    assertEquals(50,getTestUnit().getCurrentHitPoints(),EPSILON);
    getTargetCleric().addItem(getStaff());
    getTargetCleric().equipItem(getStaff());
    assertEquals(getStaff(),getTargetCleric().getEquippedItem());
    getTargetCleric().attack(unit);
    assertEquals(50,unit.getCurrentHitPoints(),EPSILON);
    assertEquals(50,getTargetCleric().getCurrentHitPoints(),EPSILON);
    unit.removeItem(itemB);
    getTestUnit().removeItem(itemA);
  }

  @Override
  public void checkSameTypeUnitAttack(IUnit unit, IEquipableItem itemA, IEquipableItem itemB) {
    assertEquals(50, getTestUnit().getCurrentHitPoints(),EPSILON);
    assertEquals(50, unit.getCurrentHitPoints(),EPSILON);
    getTestUnit().addItem(itemA);
    getTestUnit().equipItem(itemA);
    unit.addItem(itemB);
    unit.equipItem(itemB);
    assertEquals(itemA, getTestUnit().getEquippedItem());
    assertEquals(itemB, unit.getEquippedItem());
    getTestUnit().attack(unit);
    //unit attack normal to getTestUnit: 20 points of damage
    assertEquals(30, unit.getCurrentHitPoints(),EPSILON);
    assertEquals(30, getTestUnit().getCurrentHitPoints(),EPSILON);
    getTargetCleric().addItem(getStaff_normal());
    getTargetCleric().equipItem(getStaff_normal());
    assertEquals(getStaff_normal(),getTargetCleric().getEquippedItem());
    getTargetCleric().attack(unit);
    assertEquals(50,unit.getCurrentHitPoints(),EPSILON);
    getTargetCleric().attack(getTestUnit());
    assertEquals(50,getTargetCleric().getCurrentHitPoints(),EPSILON);
  }

  @Override
  public void checkArcherAttack(IEquipableItem itemA) {
    assertEquals(50, getTestUnit().getCurrentHitPoints(),EPSILON);
    assertEquals(50, getTargetArcher().getCurrentHitPoints(),EPSILON);
    getTestUnit().addItem(itemA);
    getTestUnit().equipItem(itemA);
    getTargetArcher().addItem(getBow());
    getTargetArcher().equipItem(getBow());
    assertEquals(itemA, getTestUnit().getEquippedItem());
    assertEquals(getBow(), getTargetArcher().getEquippedItem());
    getTargetArcher().attack(getTestUnit());
    assertEquals(50, getTargetArcher().getCurrentHitPoints(),EPSILON);
    assertEquals(30, getTestUnit().getCurrentHitPoints(),EPSILON);
    getTargetCleric().addItem(getStaff_normal());
    getTargetCleric().equipItem(getStaff_normal());
    assertEquals(getStaff_normal(),getTargetCleric().getEquippedItem());
    getTargetCleric().attack(getTestUnit());
    assertEquals(50,getTestUnit().getCurrentHitPoints(),EPSILON);
    assertEquals(50, getTargetCleric().getCurrentHitPoints(),EPSILON);
  }

  @Override
  public void checkArcherAttackToMagic(IEquipableItem itemA) {
    assertEquals(50, getTestUnit().getCurrentHitPoints(),EPSILON);
    assertEquals(50, getTargetArcher().getCurrentHitPoints(),EPSILON);
    getTestUnit().addItem(itemA);
    getTestUnit().equipItem(itemA);
    getTargetArcher().addItem(getBow());
    getTargetArcher().equipItem(getBow());
    assertEquals(itemA, getTestUnit().getEquippedItem());
    assertEquals(getBow(), getTargetArcher().getEquippedItem());
    getTargetArcher().attack(getTestUnit());
    assertEquals(50, getTargetArcher().getCurrentHitPoints(),EPSILON);
    assertEquals(20, getTestUnit().getCurrentHitPoints(),EPSILON);
    getTargetCleric().addItem(getStaff());
    getTargetCleric().equipItem(getStaff());
    assertEquals(getStaff(),getTargetCleric().getEquippedItem());
    getTargetCleric().attack(getTestUnit());
    assertEquals(50,getTestUnit().getCurrentHitPoints(),EPSILON);
    assertEquals(50, getTargetCleric().getCurrentHitPoints(),EPSILON);
  }

  @Override
  public void checkClericAttack(IEquipableItem item) {
    //hp normal
    assertEquals(50, getTestUnit().getCurrentHitPoints(), EPSILON);
    assertEquals(50, getTargetCleric().getCurrentHitPoints(), EPSILON);
    //test unit with inventory
    getTestUnit().addItem(item);
    //to damage to test unit
    getTestUnit().receiveAttack(getAxe()); // -20 points hp, not equipped
    assertEquals(30, getTestUnit().getCurrentHitPoints(), EPSILON);
    getTestUnit().receiveAttack(getAxe()); // -20 points hp, not equipped
    assertEquals(10, getTestUnit().getCurrentHitPoints(), EPSILON);
    //to equip to test unit
    getTestUnit().equipItem(item);
    getTargetCleric().attack(getTestUnit());
    assertEquals(10, getTestUnit().getCurrentHitPoints(), EPSILON);
    assertEquals(50, getTargetCleric().getCurrentHitPoints(), EPSILON);
    // to equip to target Cleric
    getTargetCleric().addItem(getStaff_normal());
    getTargetCleric().equipItem(getStaff_normal());
    // cleric cure to test unit
    getTargetCleric().attack(getTestUnit());
    assertEquals(30, getTestUnit().getCurrentHitPoints(), EPSILON);
    assertEquals(50, getTargetCleric().getCurrentHitPoints(), EPSILON);
    getTestUnit().removeItem(item);
    getTargetCleric().attack(getTestUnit());
    assertEquals(50, getTestUnit().getCurrentHitPoints(), EPSILON);
    getTargetCleric().attack(getTestUnit());
    assertEquals(50, getTestUnit().getCurrentHitPoints(), EPSILON);
    getTargetCleric().removeItem(getStaff_normal());
  }

  @Override
  public void checkSorcererAttack(IEquipableItem item) {
    //hp normal
    assertEquals(50, getTestUnit().getCurrentHitPoints(), EPSILON);
    assertEquals(50, getTargetCleric().getCurrentHitPoints(), EPSILON);
    //test unit with inventory
    getTestUnit().addItem(item);
    getTestUnit().equipItem(item);
    getTargetSorcerer().addItem(getLight());
    getTargetSorcerer().addItem(getDarkness());
    getTargetSorcerer().addItem(getSoul());
    getTargetSorcerer().equipItem(getLight());
    assertEquals(item, getTestUnit().getEquippedItem());
    assertEquals(getLight(),getTargetSorcerer().getEquippedItem());
    getTargetSorcerer().attack(getTestUnit());
    assertEquals(20, getTestUnit().getCurrentHitPoints(), EPSILON);
    assertEquals(20, getTargetSorcerer().getCurrentHitPoints(), EPSILON);
    //combat with darkness
    getTestUnit().receiveRecovery(getStaff());
    getTargetSorcerer().receiveRecovery(getStaff());
    getTargetSorcerer().changeEquippedItem(getDarkness());
    assertEquals(getDarkness(),getTargetSorcerer().getEquippedItem());
    getTargetSorcerer().attack(getTestUnit());
    assertEquals(20, getTestUnit().getCurrentHitPoints(), EPSILON);
    assertEquals(20, getTargetSorcerer().getCurrentHitPoints(), EPSILON);
    //combat with soul
    getTestUnit().receiveRecovery(getStaff());
    getTargetSorcerer().receiveRecovery(getStaff());
    getTargetSorcerer().changeEquippedItem(getSoul());
    getTargetSorcerer().attack(getTestUnit());
    assertEquals(20, getTestUnit().getCurrentHitPoints(), EPSILON);
    assertEquals(20, getTargetSorcerer().getCurrentHitPoints(), EPSILON);

  }
  @Test
  @Override
  public void alpacaReceiveAttack(){
    assertEquals(50, getTargetAlpaca().getCurrentHitPoints(), EPSILON);
    assertEquals(50, getTestUnit().getCurrentHitPoints(), EPSILON);
    getTestUnit().addItem(getTestItem());
    getTestUnit().equipItem(getTestItem());
    getTestUnit().attack(getTargetAlpaca());
    assertEquals(30, getTargetAlpaca().getCurrentHitPoints(), EPSILON);
    assertEquals(50, getTestUnit().getCurrentHitPoints(), EPSILON);
  }

  @Test
  @Override
  public void receiveNormalAttackTest() {
    assertEquals(50, getTestUnit().getCurrentHitPoints(), EPSILON);
    getTestUnit().receiveAttack(getAxe());
    assertEquals(50 - getAxe().getPower(), getTestUnit().getCurrentHitPoints(), EPSILON);
  }

  @Test
  @Override
  public void receiveRecoveryAttackTest() {
    assertEquals(50, getTestUnit().getCurrentHitPoints(), EPSILON);
    getTestUnit().receiveRecovery(getStaff_normal());
    assertEquals(50,getTestUnit().getCurrentHitPoints(), EPSILON);
    getTestUnit().receiveAttack(getAxe());
    assertEquals(50- getAxe().getPower(),getTestUnit().getCurrentHitPoints(), EPSILON);
    getTestUnit().receiveRecovery(getStaff_normal());
    assertEquals(50,getTestUnit().getCurrentHitPoints(), EPSILON);
  }

  @Test
  @Override
  public void receiveResistantAttackTest() {
    assertEquals(50,getTestUnit().getCurrentHitPoints(), EPSILON);
    getTestUnit().receiveAttackResistant(getAxe());
    double hp_new = 50 - getAxe().getPower() + 20;
    assertEquals(hp_new, getTestUnit().getCurrentHitPoints(), EPSILON);
  }

  @Test
  @Override
  public void receiveWeaknessAttackTest() {
    assertEquals(50,getTestUnit().getCurrentHitPoints(), EPSILON);
    getTestUnit().receiveAttackWeakness(getAxe());
    double hp_new = 50 - getAxe().getPower()*1.5;
    assertEquals(hp_new, getTestUnit().getCurrentHitPoints(), EPSILON);
  }
  /*
  @Test
  public void isLiveTest(){
    assertEquals(50, getTestUnit().getCurrentHitPoints());
    getTestUnit().receiveAttack(getBow());
    assertEquals(30, getTestUnit().getCurrentHitPoints());
    assertTrue(getTestUnit().isLive());
    getTestUnit().receiveAttack(getBow());
    assertTrue(getTestUnit().isLive());
    getTestUnit().receiveAttack(getBow());
    assertFalse(getTestUnit().isLive());
  }
   */

}
