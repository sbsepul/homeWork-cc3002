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

package controller;

import model.items.*;
import model.items.magic.Darkness;
import model.items.magic.Light;
import model.items.magic.Soul;
import model.map.InvalidLocation;
import model.units.*;
import model.units.factoryUnit.*;
import model.map.Field;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;


/**
 * @author Sebastian Sepulveda
 * @version 1.0
 * @since 2.0
 */
class GameControllerTest {

  private GameController controller;
  private long randomSeed;
  private List<String> testTacticians;

  @BeforeEach
  public void setUp() {
    // Se define la semilla como un número aleatorio para generar variedad en los tests || ok
    randomSeed = new Random().nextLong();
    controller = new GameController(4, 7);
    testTacticians = List.of("Player 0", "Player 1", "Player 2", "Player 3");
  }


  @Test
  public void getTacticians() {
    List<Tactician> tacticians = controller.getTacticians();
    assertEquals(4, tacticians.size());
    assertEquals(4, controller.getNumPlayers());
    for (int i = 0; i < tacticians.size(); i++) {
      assertEquals("Player " + i, tacticians.get(i).getName());
    }
  }

  @Test
  public void getGameMap() {
    Field gameMap = controller.getGameMap();
    assertEquals(7, gameMap.getSize()); // getSize deben definirlo || ok
    assertTrue(controller.getGameMap().isConnected());
    Random testRandom = new Random(randomSeed);
     // System.out.println(gameMap.toString());

    controller.setSeedMap(testRandom.nextLong());
    Field newGameMap = controller.createNewMap();
    assertEquals(7, newGameMap.getSize());
    assertTrue(newGameMap.isConnected());
      //System.out.println(newGameMap.toString());

    // Para testear funcionalidades que dependen de valores aleatorios se hacen 2 cosas:
    //  - Comprobar las invariantes de las estructuras que se crean (en este caso que el mapa tenga
    //    las dimensiones definidas y que sea conexo.
    //  - Setear una semilla para el generador de números aleatorios. Hacer esto hace que la
    //    secuencia de números generada sea siempre la misma, así pueden predecir los
    //    resultados que van a obtener.
    //    Hay 2 formas de hacer esto en Java, le pueden pasar el seed al constructor de Random, o
    //    usar el método setSeed de Random.
    //  ESTO ÚLTIMO NO ESTÁ IMPLEMENTADO EN EL MAPA, ASÍ QUE DEBEN AGREGARLO (!) || ok (?)
  }

  @Test
  public void getTurnOwner() {
    //  En este caso deben hacer lo mismo que para el mapa
    Tactician tactician = controller.getTurnOwner();
    String firstName = controller.getTacticians().get(0).getName();
    assertEquals(firstName, tactician.getName());
  }

  @Test
  public void getRoundNumber() {
    controller.initGame(10);
    // Round where the tactician chose to the units
    // IntStream.range(0, 4).forEach(i -> controller.endTurn()); // added
    for (int i = 1; i < 10; i++) {
      assertEquals(i, controller.getRoundNumber());
      for (int j = 0; j < 4; j++) {
        controller.endTurn();
      }
    }
  }

  @Test
  public void createNewRoundTest(){
    //RANDOM TEST
    Tactician firstPlayer = controller.getTacticians().get(0);
    Tactician lastPlayer = controller.getTacticians().get(3);
    controller.createNewRound();
    Tactician newFirst = controller.getTacticians().get(0);
    assertFalse(lastPlayer.getName().equals(newFirst.getName()));
    assertFalse(firstPlayer.getName().equals(newFirst.getName()));
  }

  @Test
  void getMaxRounds() {
    Random randomTurnSequence = new Random();
    IntStream.range(0, 50).map(i -> randomTurnSequence.nextInt() & Integer.MAX_VALUE).forEach(nextInt -> {
      controller.initGame(nextInt);
      //System.out.println(nextInt);
      assertEquals(nextInt, controller.getMaxRounds());
      //System.out.println(nextInt);
    });
    controller.initEndlessGame();
    assertEquals(-1, controller.getMaxRounds());
  }

  @Test
  public void endTurn() {
    controller.initGame(3);
    Tactician firstPlayer = controller.getTurnOwner();
    // Nuevamente, para determinar el orden de los jugadores se debe usar una semilla
    Tactician secondPlayer = new Tactician("Player 1"); // <- Deben cambiar esto (!)
    assertNotEquals(secondPlayer.getName(), firstPlayer.getName());
    controller.endTurn();

    assertNotEquals(firstPlayer.getName(), controller.getTurnOwner().getName());
    assertEquals(secondPlayer.getName(), controller.getTurnOwner().getName());
    IntStream.range(0,2).forEach(i->controller.endTurn());
    Tactician lastPlayer = new Tactician("Player 3");
    assertEquals("Player 3", controller.getTurnOwner().getName());
    controller.endTurn();

    assertFalse(lastPlayer.getName().equals(controller.getTurnOwner().getName()));
  }

  @Test
  public void removeTactician() {
    assertEquals(4, controller.getTacticians().size());
    controller.getTacticians()
        .forEach(tactician -> Assertions.assertTrue(testTacticians.contains(tactician.getName())));

    controller.removeTactician("Player 0");
    assertEquals(3, controller.getTacticians().size());
    controller.getTacticians().forEach(tactician -> assertNotEquals("Player 0", tactician.getName()));
    controller.getTacticians()
        .forEach(tactician -> Assertions.assertTrue(testTacticians.contains(tactician.getName())));

    controller.removeTactician("Player 5");
    assertEquals(3, controller.getTacticians().size());
    controller.getTacticians()
        .forEach(tactician -> Assertions.assertTrue(testTacticians.contains(tactician.getName())));
  }

  @Test
  public void getWinners() {
    controller.initGame(2);
    // Round where the tactician chose to the units
    // IntStream.range(0, 4).forEach(i -> controller.endTurn()); //added
    IntStream.range(0, 8).forEach(i -> controller.endTurn());
    assertEquals(3, controller.getRoundNumber());
    assertEquals(4, controller.getWinners().size());
    controller.getWinners()
        .forEach(player -> Assertions.assertTrue(testTacticians.contains(player)));

    controller.initGame(2);
    // Round where the tactician chose to the units
    // IntStream.range(0, 4).forEach(i -> controller.endTurn()); //added
    IntStream.range(0, 4).forEach(i -> controller.endTurn());
    assertNull(controller.getWinners());
    controller.removeTactician("Player 0");
    controller.removeTactician("Player 2");
    IntStream.range(0, 2).forEach(i -> controller.endTurn());
    List<String> winners = controller.getWinners();
    assertEquals(2, winners.size());
    assertTrue(List.of("Player 1", "Player 3").containsAll(winners));

    controller.initEndlessGame();
    for (int i = 0; i < 3; i++) {
      assertNull(controller.getWinners());
      controller.removeTactician("Player " + Integer.toString(i));
    }
    assertTrue(List.of("Player 3").containsAll(controller.getWinners()));
  }


  @Test
  public void addUnitToTacticianTest(){
    IUnit hero = controller.getHeroFab().createUnit();
    IUnit unit = controller.getArcherFab().createUnit();
    controller.addHeroToTactician(controller.getHeroFab().createUnit());
    assertEquals(1, controller.getTurnOwner().getUnits().size());
    assertEquals(hero.getClass(), controller.getTurnOwner().getUnits().get(0).getClass());
    controller.addUnitToTactician((NormalUnit) unit);
    assertEquals(2, controller.getTurnOwner().getUnits().size());
  }

  @Test
  public void putInMap(){
    IFactoryUnit archerFab = controller.getArcherFab();
    IUnit unit = controller.getSorcererFab().createUnit();
    controller.putUnitInMap(unit, 8,8);
    assertEquals(InvalidLocation.class, unit.getLocation().getClass());
    controller.putUnitInMap(unit, 0,0);
    assertFalse(unit.getClass().equals(InvalidLocation.class));
    assertTrue(controller.getGameMap().getCell(0,0).equals(unit.getLocation()));
    IUnit unitExtracted = controller.getGameMap().getCell(0,0).getUnit();

    assertEquals(unit, unitExtracted);
    controller.putUnitInMap(archerFab.createUnit(), 0,0);
    assertFalse(controller.getGameMap().getCell(0,0).equals(archerFab.getLocation()));
    assertTrue(controller.getGameMap().getCell(0,0).equals(unit.getLocation()));

  }

  @Test
  public void getSelectedUnit() {
    assertNull(controller.getSelectedUnit());
    IUnit unit = controller.getArcherFab().createUnit();
    controller.setSelectedUnit(unit);
    assertEquals(unit, controller.getSelectedUnit());
  }

  @Test
  public void selectUnitIn() {
    assertNull(controller.getGameMap().getCell(0,0).getUnit());
    IUnit unitNew = controller.getSwordMasterFab().createUnit();
    controller.putUnitInMap(unitNew, 0,0);
    assertTrue(controller.getGameMap().getCell(0,0).getUnit().equals(unitNew));
    IUnit unit = controller.getGameMap().getCell(0,0).getUnit();
    controller.selectUnitIn(1,1);
    assertNull(controller.getSelectedUnit());
    controller.selectUnitIn(0,0);
    assertEquals(unit,controller.getSelectedUnit());
    IUnit unitTwo = controller.getClericFab().createUnit();
    controller.putUnitInMap(unitTwo, 3,3);
    //celda 3,3 no es valida
    assertEquals(controller.getGameMap().getCell(3,3), unitTwo.getLocation());
    assertEquals(unitTwo,controller.getGameMap().getCell(3,3).getUnit());
    controller.selectUnitIn(3,3);
    assertEquals(unitTwo, controller.getSelectedUnit());
  }

  @Test
  public void getItems() {
    //System.out.println(controller.getGameMap().toString());
    IEquipableItem axe = controller.getAxeFab().createItem();
    IEquipableItem sword = controller.getSwordFab().createItem();
    IFactoryUnit alpaca = controller.getAlpacaFab();
    alpaca.setItems(axe,sword);
    IUnit unit = alpaca.createUnit();
    controller.putUnitInMap(unit, 1,1);
    assertNull(controller.getSelectedUnit());
    controller.selectUnitIn(1,1);
    assertEquals(unit, controller.getSelectedUnit());
    List<IEquipableItem> itemsUnit = unit.getItems();
    itemsUnit.forEach(item -> assertTrue(controller.getItems().contains(item)));
  }

  @Test
  public void equipItem() {
    IFactoryUnit archerFab = controller.getArcherFab();
    IEquipableItem bowFirst = controller.getBowFab().createItem();
    archerFab.setItems(
            controller.getAxeFab().createItem(),
            bowFirst,
            controller.getSwordFab().createItem()
    );
    IUnit archer = archerFab.createUnit();
    controller.putUnitInMap(archer, 1,1);
    controller.selectUnitIn(1,1);
    assertEquals(archer, controller.getSelectedUnit());
    assertEquals(3, controller.getSelectedUnit().getItems().size());
    assertNull(controller.getSelectedUnit().getEquippedItem());
    controller.equipItem(0);
    assertNull(controller.getSelectedUnit().getEquippedItem());
    controller.equipItem(1);
    IEquipableItem bow = controller.getBowFab().createItem();
    assertEquals(
            bow.getClass(),
            controller.getSelectedUnit().getEquippedItem().getClass());
    controller.equipItem(2);
    assertEquals(
            bow.getClass(),
            controller.getSelectedUnit().getEquippedItem().getClass());
  }

  @Test
  public void removeUnitStatus(){
    assertEquals(4, controller.getNumPlayers());
    assertTrue(controller.getTurnOwner().getStatus());
    controller.getTurnOwner().retire();
    assertEquals(3, controller.getNumPlayers());
  }


  @Test
  public void useItemOn() {
    putInPosition();
    // System.out.println(controller.getGameMap().toString());
    controller.selectUnitIn(6,0);
    assertEquals(50, controller.getSelectedUnit().getCurrentHitPoints());
    assertEquals(1, controller.getSelectedUnit().getItems().size());
    controller.equipItem(0);
    controller.selectUnitIn(5,1);
    controller.equipItem(0);
    controller.selectUnitIn(5,0);
    controller.equipItem(0);
    controller.selectUnitIn(6,0);
    controller.useItemOn(5,1);
    assertEquals(40, controller.getSelectedUnit().getCurrentHitPoints());
    assertEquals(40, controller.getGameMap().getCell(5,1).getUnit().getCurrentHitPoints());
    controller.selectUnitIn(5,0);
    controller.useItemOn(5,1);
    assertEquals(50, controller.getSelectedUnit().getCurrentHitPoints());
    assertEquals(40, controller.getGameMap().getCell(5,1).getUnit().getCurrentHitPoints());
  }

  @Test
  public void selectItem() {
    assertNull(controller.getSelectedItem());
    IFactoryUnit alpaca = controller.getAlpacaFab();
    IEquipableItem axe = controller.getAxeFab().createItem();
    IEquipableItem sword = controller.getSwordFab().createItem();
    IEquipableItem bow = controller.getBowFab().createItem();
    IEquipableItem light = controller.getLightFab().createItem();
    alpaca.setItems(
            axe,sword,bow,light
    );
    controller.putUnitInMap(alpaca.createUnit(), 1,1);
    controller.selectUnitIn(1,1);
    controller.selectItem(0);
    assertEquals(axe.getName(),controller.getSelectedItem().getName());
    controller.selectItem(1);
    assertEquals(sword.getName(),controller.getSelectedItem().getName());
    controller.selectItem(2);
    assertEquals(bow.getName(),controller.getSelectedItem().getName());
    controller.selectItem(3);
    assertEquals(light.getName(),controller.getSelectedItem().getName());
  }

  @Test
  public void giveItemTo() {
    IFactoryUnit sorcerer = controller.getSorcererFab();
    IFactoryUnit cleric = controller.getClericFab();
    IFactoryUnit alpaca = controller.getAlpacaFab();
    sorcerer.addItemForDefault();
    assertEquals(1,sorcerer.getItemAll().length);
    cleric.addItemForDefault();
    assertEquals(1,cleric.getItemAll().length);
    alpaca.addItemForDefault();
    assertEquals(2,alpaca.getItemAll().length);
    controller.putUnitInMap(sorcerer.createUnit(),6,1);
    controller.putUnitInMap(cleric.createUnit(),6,0);
    controller.putUnitInMap(alpaca.createUnit(),5,1);
    controller.selectUnitIn(6,0);
    controller.selectItem(0);
    assertEquals(1,controller.getSelectedUnit().getItems().size());
    controller.giveItemTo(6,1);
    assertEquals(0,controller.getSelectedUnit().getItems().size());
    controller.selectUnitIn(6,1);
    assertEquals(2, controller.getSelectedUnit().getItems().size());
    controller.selectUnitIn(5,1);
    controller.selectItem(0);
    controller.giveItemTo(6,1);
    assertEquals(1,controller.getSelectedUnit().getItems().size());
    assertEquals(3,controller.getGameMap().getCell(6,1).getUnit().getItems().size());
    controller.selectItem(0);
    controller.giveItemTo(6,1);
    assertEquals(1,controller.getSelectedUnit().getItems().size());
  }

  /**
   *  #  #  #  #  #  #  #
   * #o  +##+  +  +##+##+#
   * #o##o  +##+  +  +##+#
   * #+##+  +##+  +##+  +#
   * #+  +##+  +##+  +  +#
   * #+  +  +  +##+##+##+#
   * #+  +  +##+##+  +  +#
   * #+  +  +  +  +  +  +#
   *  #  #  #  #  #  #  #
   */
  @Test
  public void moveToUnit(){
    putInPosition();
    System.out.println(controller.getGameMap().toString());
    controller.selectUnitIn(6,0);
    IUnit unitSelected = controller.getSelectedUnit();
    controller.moveToSelectedUnit(5,1);
    assertFalse(unitSelected.equals(controller.getGameMap().getCell(5,1).getUnit()));
    assertEquals(unitSelected.getLocation(), controller.getSelectedUnit().getLocation());

    controller.selectUnitIn(5,1);
    IUnit unitMoved = controller.getSelectedUnit();
    controller.moveToSelectedUnit(4,2);
    assertEquals(unitMoved,controller.getGameMap().getCell(4,2).getUnit());
    assertFalse(unitMoved.getLocation().equals(controller.getSelectedUnit()));
    System.out.println(controller.getGameMap().toString());

    controller.selectUnitIn(5,0);
    IUnit unitMovedOverOther =  controller.getSelectedUnit();
    controller.moveToSelectedUnit(6,1);
    assertEquals(unitMovedOverOther, controller.getGameMap().getCell(6,1).getUnit());
    System.out.println(controller.getGameMap().toString());
    assertNull(controller.getGameMap().getCell(5,0).getUnit());
  }

  /**
   * STATUS INITIAL
   *
   *  #  #  #  #  #  #  #
   * #3  +##+  +  +##+##+#
   * #3##3  +##4  4  4##+#
   * #+##+  +##+  +##+  +#
   * #+  +##+  +##+  +  +#
   * #+  +  +  +##2##+##+#
   * #1  +  +##+##2  2  +#
   * #1  1  +  +  +  +  +#
   *  #  #  #  #  #  #  #
   *
   * STATUS FINAL
   *
   *    #  #  #  #  #  #  #
   * 6 #3  +##+  +  +##+##+#
   * 5 #3##3  +##4  4  4##+#
   * 4 #+##+  +##+  +##+  +#
   * 3 #+  +##+  +##+  +  +#
   * 2 #+  +  +  +##2##+##+#
   * 1 #1  +  +##+##2  2  +#
   * 0 #1  1  +  +  +  +  +#
   *    #  #  #  #  #  #  #
   *    0  1  2  3  4  5  6
   */
  @Test
  public void gameNormal(){
    assignUnitToTactician();
    assertEquals(0, controller.getTurnCurrent());
    assertEquals(0, controller.getRoundNumber());
    controller.initGame(3);
    System.out.println(controller.getGameMap().toString());
    assertEquals(controller.getTacticians().size(), controller.getInitPlayerStatus().size());
    controller.selectUnitIn(1,5);
    assertNull(controller.getCurrentUnit());
    controller.selectUnitIn(0,0);
    assertEquals(controller.getSelectedUnit(), controller.getCurrentUnit());
    controller.moveToSelectedUnit(2,0);
  }

  @Test
  public void getFabUnits(){
    IUnit unit1 = controller.getSwordMasterFab().createUnit();
    assertEquals(SwordMaster.class, unit1.getClass());
    IUnit unit2 = controller.getClericFab().createUnit();
    assertEquals(Cleric.class, unit2.getClass());
    IUnit unit3 = controller.getHeroFab().createUnit();
    assertEquals(Hero.class, unit3.getClass());
    IUnit unit4 = controller.getSorcererFab().createUnit();
    assertEquals(Sorcerer.class, unit4.getClass());
    IUnit unit5 = controller.getArcherFab().createUnit();
    assertEquals(Archer.class, unit5.getClass());
    IUnit unit6 = controller.getAlpacaFab().createUnit();
    assertEquals(Alpaca.class, unit6.getClass());
    IUnit unit7 = controller.getFighterFab().createUnit();
    assertEquals(Fighter.class, unit7.getClass());
  }

  @Test
  public void getFabItem(){
    IEquipableItem item1 = controller.getAxeFab().createItem();
    assertEquals(Axe.class, item1.getClass());
    IEquipableItem item2 = controller.getBowFab().createItem();
    assertEquals(Bow.class, item2.getClass());
    IEquipableItem item3 = controller.getSwordFab().createItem();
    assertEquals(Sword.class, item3.getClass());
    IEquipableItem item4 = controller.getStaffFab().createItem();
    assertEquals(Staff.class, item4.getClass());
    IEquipableItem item5 = controller.getSpearFab().createItem();
    assertEquals(Spear.class, item5.getClass());
    IEquipableItem item6 = controller.getDarknessFab().createItem();
    assertEquals(Darkness.class, item6.getClass());
    IEquipableItem item7 = controller.getLightFab().createItem();
    assertEquals(Light.class, item7.getClass());
    IEquipableItem item8 = controller.getSoulFab().createItem();
    assertEquals(Soul.class, item8.getClass());
  }


  /**
   * Add three units with equip items in the map
   */
  private void putInPosition(){
    IFactoryUnit archer = controller.getArcherFab();
    IFactoryUnit fighter = controller.getFighterFab();
    IFactoryUnit master = controller.getSwordMasterFab();
    archer.addItemForDefault();
    fighter.addItemForDefault();
    master.addItemForDefault();
    controller.putUnitInMap(archer.createUnit(), 6,0);
    controller.putUnitInMap(fighter.createUnit(),5,1);
    controller.putUnitInMap(master.createUnit(), 5,0);
  }

  /**
   * Assign units with items and positions in the game for each player
   */
  private void assignUnitToTactician(){
    Map<String, IFactoryUnit> fabMap = new HashMap<>();
    fabMap.put("archer", controller.getArcherFab());
    fabMap.put("alpaca", controller.getAlpacaFab());
    fabMap.put("cleric", controller.getClericFab());
    fabMap.put("sorcerer", controller.getSorcererFab());
    fabMap.put("sm", controller.getSwordMasterFab());
    fabMap.put("hero", controller.getHeroFab());
    fabMap.put("fighter", controller.getFighterFab());
    fabMap.forEach((k,v) -> v.addItemForDefault());

    controller.addUnitToTactician((NormalUnit) fabMap.get("archer").createUnit());
    controller.addUnitToTactician((NormalUnit) fabMap.get("alpaca").createUnit());
    controller.addUnitToTactician((NormalUnit) fabMap.get("sorcerer").createUnit());
    controller.putUnitInMap(controller.getTurnOwner().getUnits().get(0), 0,0);
    controller.putUnitInMap(controller.getTurnOwner().getUnits().get(1), 1,0);
    controller.putUnitInMap(controller.getTurnOwner().getUnits().get(2), 0,1);

    controller.changeToNextTurn();

    controller.addHeroToTactician((SpecialUnit) fabMap.get("hero").createUnit());
    controller.addUnitToTactician((NormalUnit) fabMap.get("fighter").createUnit());
    controller.addUnitToTactician((NormalUnit) fabMap.get("cleric").createUnit());
    controller.putUnitInMap(controller.getTurnOwner().getUnits().get(0), 1,6);
    controller.putUnitInMap(controller.getTurnOwner().getUnits().get(1), 1,5);
    controller.putUnitInMap(controller.getTurnOwner().getUnits().get(2), 2,5);

    controller.changeToNextTurn();

    controller.addHeroToTactician((SpecialUnit) fabMap.get("hero").createUnit());
    controller.addHeroToTactician((SpecialUnit) fabMap.get("hero").createUnit());
    controller.addUnitToTactician((NormalUnit) fabMap.get("fighter").createUnit());
    controller.putUnitInMap(controller.getTurnOwner().getUnits().get(0), 6,0);
    controller.putUnitInMap(controller.getTurnOwner().getUnits().get(1), 5,0);
    controller.putUnitInMap(controller.getTurnOwner().getUnits().get(2), 5,1);

    controller.changeToNextTurn();

    controller.addUnitToTactician((NormalUnit) fabMap.get("sorcerer").createUnit());
    controller.addUnitToTactician((NormalUnit) fabMap.get("sm").createUnit());
    controller.addUnitToTactician((NormalUnit) fabMap.get("fighter").createUnit());
    controller.putUnitInMap(controller.getTurnOwner().getUnits().get(0), 5,5);
    controller.putUnitInMap(controller.getTurnOwner().getUnits().get(1), 5,4);
    controller.putUnitInMap(controller.getTurnOwner().getUnits().get(2), 5,3);

    controller.changeToNextTurn();
  }


}