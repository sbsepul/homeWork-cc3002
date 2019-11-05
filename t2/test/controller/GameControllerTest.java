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
import model.map.Location;
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
import java.util.function.Consumer;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;


/**
 * @author Sebastian Sepulveda
 * @version 1.0
 * @since 2.0
 */
class GameControllerTest {

  private GameController controller, controllerSmall;
  private long randomSeed;
  private List<String> testTacticians;
  private List<String> testTacticianSmall;

  @BeforeEach
  public void setUp() {
    // Se define la semilla como un número aleatorio para generar variedad en los tests || ok
    randomSeed = new Random().nextLong();
    controller = new GameController(4, 7);
    testTacticians = List.of("Player 0", "Player 1", "Player 2", "Player 3");
    controllerSmall = new GameController(2,6);
    testTacticianSmall = List.of("Player 0", "Player 1");
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
  public void getWinnerMaxUnit(){
    IFactoryUnit alpaca = controller.getAlpacaFab();
    controller.initGame(1);
    controller.addUnitToTactician((NormalUnit) alpaca.createUnit());
    assertEquals(1, controller.getTurnOwner().getUnits().size());
    controller.endTurn();
    controller.addUnitToTactician((NormalUnit) alpaca.createUnit());
    controller.addUnitToTactician((NormalUnit) alpaca.createUnit());
    assertEquals(2, controller.getTurnOwner().getUnits().size());
    controller.endTurn();
    controller.addUnitToTactician((NormalUnit) alpaca.createUnit());
    controller.addUnitToTactician((NormalUnit) alpaca.createUnit());
    controller.addUnitToTactician((NormalUnit) alpaca.createUnit());
    assertEquals(3, controller.getTurnOwner().getUnits().size());
    controller.endTurn();
    controller.endTurn();
    assertEquals("Player 2",controller.getWinners().get(0));
    assertEquals(1, controller.getWinners().size());
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
    assertFalse(controller.getSelectedUnit().getEquippedItem().isUtil());
    controller.equipItem(0);
    assertFalse(controller.getSelectedUnit().getEquippedItem().isUtil());
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
    IUnit uAlpaca = alpaca.createUnit();
    uAlpaca.addItem(controller.getSpearFab().createItem());
    controller.putUnitInMap(sorcerer.createUnit(),6,1);
    controller.putUnitInMap(cleric.createUnit(),6,0);
    controller.putUnitInMap(uAlpaca,5,1);
    // select cleric
    controller.selectUnitIn(6,0);
    controller.selectItem(0);
    assertEquals(1,controller.getSelectedUnit().getItems().size());
    controller.giveItemTo(6,1);
    assertEquals(0,controller.getSelectedUnit().getItems().size());
    controller.selectUnitIn(6,1);
    assertEquals(2, controller.getSelectedUnit().getItems().size());
    // select alpaca
    controller.selectUnitIn(5,1);
    controller.selectItem(0);
    controller.giveItemTo(6,1);
    assertEquals(1,controller.getSelectedUnit().getItems().size());
    assertEquals(3,controller.getGameMap().getCell(6,1).getUnit().getItems().size());
    controller.selectItem(0);
    controller.giveItemTo(6,1);
    assertEquals(1,controller.getSelectedUnit().getItems().size());
  }

  @Test
  public void giveToSameTactician(){
      controllerSmall.initGame(1);
      assignUnitToSmallController();
      // give a item to unit in the same inventory
      assertNull(controllerSmall.getSelectedUnit());
      controllerSmall.selectUnitIn(0,0);
      assertEquals(1, controllerSmall.getSelectedUnit().getItems().size());
      controllerSmall.selectItem(0);
      assertEquals(1, controllerSmall.getGameMap().getCell(0,1).getUnit().getItems().size());
      controllerSmall.giveItemTo(0,1);
      assertEquals(2, controllerSmall.getGameMap().getCell(0,1).getUnit().getItems().size());
      assertEquals(0, controllerSmall.getSelectedUnit().getItems().size());

      // give a item to unit that does not own
      controllerSmall.selectUnitIn(0,1);
      assertEquals(1, controllerSmall.getGameMap().getCell(1,1).getUnit().getItems().size());
      controllerSmall.giveItemTo(1,1);
      assertEquals(2, controllerSmall.getSelectedUnit().getItems().size());
      assertEquals(1, controllerSmall.getGameMap().getCell(1,1).getUnit().getItems().size());

      // give a item to unit of other Tactician but that is in the inventory correctly
      controllerSmall.selectUnitIn(1,0);
      assertEquals(1, controllerSmall.getSelectedUnit().getItems().size());
      controllerSmall.selectItem(0);
      assertEquals(1, controllerSmall.getGameMap().getCell(1,1).getUnit().getItems().size());
      controllerSmall.giveItemTo(1,1);
      assertEquals(2, controllerSmall.getGameMap().getCell(1,1).getUnit().getItems().size());
      assertEquals(0, controllerSmall.getSelectedUnit().getItems().size());
  }

  @Test
  public void assignUnitToSmallController(){
    /*
    In a small game with 2 players
    The player 1 and 2 have 3 units:
      alpaca, fighter, hero.
    each unit have his item equipable
    Positions units player 1: (0,0) - (0,1) - (0,2)
    Positions units player 2: (1,0) - (1,1) - (1,2)
     */
    IFactoryUnit alpaca = controllerSmall.getAlpacaFab();
    alpaca.addItemForDefault();
    IFactoryUnit fighter = controllerSmall.getFighterFab();
    fighter.addItemForDefault();
    IFactoryUnit hero = controllerSmall.getHeroFab();
    hero.addItemForDefault();
    controllerSmall.addUnitToTactician((NormalUnit) alpaca.createUnit());
    controllerSmall.addUnitToTactician((NormalUnit) fighter.createUnit());
    controllerSmall.addHeroToTactician((SpecialUnit) hero.createUnit());
    IUnit unit01 = controllerSmall.getTurnOwner().getUnits().get(0);
    IUnit unit02 = controllerSmall.getTurnOwner().getUnits().get(1);
    IUnit unit03 = controllerSmall.getTurnOwner().getUnits().get(2);
    controllerSmall.putUnitInMap(unit01,0,0);
    controllerSmall.putUnitInMap(unit02,0,1);
    controllerSmall.putUnitInMap(unit03, 0,2);
    assertEquals(controllerSmall.getGameMap().getCell(0,0), unit01.getLocation());
    assertEquals(controllerSmall.getGameMap().getCell(0,1), unit02.getLocation());
    assertEquals(controllerSmall.getGameMap().getCell(0,0).getUnit(), unit01);
    assertEquals(controllerSmall.getGameMap().getCell(0,1).getUnit(), unit02);
    assertEquals(controllerSmall.getGameMap().getCell(0,2), unit03.getLocation());
    assertEquals(controllerSmall.getGameMap().getCell(0,2).getUnit(), unit03);

    controllerSmall.changeToNextTurn();

    alpaca.addItemForDefault();
    fighter.addItemForDefault();
    hero.addItemForDefault();
    controllerSmall.addUnitToTactician((NormalUnit) alpaca.createUnit());
    controllerSmall.addUnitToTactician((NormalUnit) fighter.createUnit());
    controllerSmall.addHeroToTactician((SpecialUnit) hero.createUnit());
    IUnit unit04 = controllerSmall.getTurnOwner().getUnits().get(0);
    IUnit unit05 = controllerSmall.getTurnOwner().getUnits().get(1);
    IUnit unit06 = controllerSmall.getTurnOwner().getUnits().get(2);
    controllerSmall.putUnitInMap(unit04,1,0);
    controllerSmall.putUnitInMap(unit05,1,1);
    controllerSmall.putUnitInMap(unit06, 1,2);
    assertEquals(controllerSmall.getGameMap().getCell(1,0), unit04.getLocation());
    assertEquals(controllerSmall.getGameMap().getCell(1,1), unit05.getLocation());
    assertEquals(controllerSmall.getGameMap().getCell(1,0).getUnit(), unit04);
    assertEquals(controllerSmall.getGameMap().getCell(1,1).getUnit(), unit05);
    assertEquals(controllerSmall.getGameMap().getCell(1,2), unit06.getLocation());
    assertEquals(controllerSmall.getGameMap().getCell(1,2).getUnit(), unit06);

    controllerSmall.changeToNextTurn();

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
    // put 3 units in positions: (6,0) ; (5,0) ; (5,1)
    putInPosition();
    // test moving a unit to position occupied
    // System.out.println(controller.getGameMap().toString());
    controller.selectUnitIn(6,0);
    IUnit unitSelected = controller.getSelectedUnit();
    controller.moveToSelectedUnit(5,1);
    assertFalse(unitSelected.equals(controller.getGameMap().getCell(5,1).getUnit()));
    assertEquals(unitSelected.getLocation(), controller.getSelectedUnit().getLocation());

    // test moving unit to max movement
    controller.selectUnitIn(5,1);
    IUnit unitMoved = controller.getSelectedUnit();
    controller.moveToSelectedUnit(4,2);
    assertEquals(unitMoved,controller.getGameMap().getCell(4,2).getUnit());
    assertFalse(unitMoved.getLocation().equals(controller.getSelectedUnit()));
    // System.out.println(controller.getGameMap().toString());

    // test move unit to position valid and there are not duplicate
    controller.selectUnitIn(5,0);
    IUnit unitMovedOverOther =  controller.getSelectedUnit();
    controller.moveToSelectedUnit(6,1);
    assertEquals(unitMovedOverOther, controller.getGameMap().getCell(6,1).getUnit());
    // System.out.println(controller.getGameMap().toString());
    assertNull(controller.getGameMap().getCell(5,0).getUnit());
  }

  @Test
  public void movedToUnitInGame(){
    controllerSmall.initGame(2);
    assignUnitToSmallController();
    controllerSmall.selectUnitIn(0,0);
    controllerSmall.moveToSelectedUnit(2,0);
    assertEquals(1, controllerSmall.getTurnOwner().getMoves().size());
    assertEquals(1, controllerSmall.getUnitsMoved().size());
    controllerSmall.selectUnitIn(0,1);
    controllerSmall.moveToSelectedUnit(2,1);
    assertEquals(2, controllerSmall.getTurnOwner().getMoves().size());
    assertEquals(2, controllerSmall.getUnitsMoved().size());
    controllerSmall.selectUnitIn(0,2);
    controllerSmall.moveToSelectedUnit(2,2);
    assertEquals(3, controllerSmall.getTurnOwner().getMoves().size());
    assertEquals(3, controllerSmall.getUnitsMoved().size());
    controllerSmall.selectUnitIn(1,1);
    controllerSmall.moveToSelectedUnit(0,0);
    assertEquals(4, controllerSmall.getUnitsMoved().size());
    assertEquals(3, controllerSmall.getTurnOwner().getMoves().size());
    System.out.println(controllerSmall.getGameMap().toString());
  }

  @Test
  public void generateAttackInGame(){
    controllerSmall.initGame(4);
    assignUnitToSmallController();

    controllerSmall.selectUnitIn(0,2);
    assertEquals(50, controllerSmall.getSelectedUnit().getCurrentHitPoints());
    controllerSmall.equipItem(0);

    controllerSmall.selectUnitIn(1,1);
    assertEquals(50, controllerSmall.getSelectedUnit().getCurrentHitPoints());
    controllerSmall.equipItem(0);

    controllerSmall.selectUnitIn(0,1);
    assertEquals(50, controllerSmall.getSelectedUnit().getCurrentHitPoints());
    controllerSmall.equipItem(0);
    //Round 1

    //hero 0 attack to fighter 1
    controllerSmall.selectUnitIn(0,2);
    controllerSmall.useItemOn(1,1);
    assertEquals(35, controllerSmall.getSelectedUnit().getCurrentHitPoints());
    assertEquals(50, controllerSmall.getGameMap().getCell(1,1).getUnit().getCurrentHitPoints());

    controllerSmall.endTurn();
    assertEquals(1, controllerSmall.getTurnCurrent());
    assertEquals("Player 1", controllerSmall.getTurnOwner().getName());
    controllerSmall.selectUnitIn(1,1);
    controllerSmall.useItemOn(0,2);
    assertEquals(50, controllerSmall.getSelectedUnit().getCurrentHitPoints());
    assertEquals(20, controllerSmall.getGameMap().getCell(0,2).getUnit().getCurrentHitPoints());

    //Round 2
    controllerSmall.endTurn();
    assertEquals(0, controllerSmall.getTurnCurrent());
    assertEquals("Player 0", controllerSmall.getTurnOwner().getName());
    controllerSmall.selectUnitIn(0,2);
    controllerSmall.useItemOn(1,1);
    assertEquals(50, controllerSmall.getGameMap().getCell(1,1).getUnit().getCurrentHitPoints());
    assertEquals(5, controllerSmall.getSelectedUnit().getCurrentHitPoints());

    controllerSmall.endTurn();
    controllerSmall.selectUnitIn(1,1);
    controllerSmall.useItemOn(0,1);
    assertEquals("axe", controllerSmall.getGameMap().getCell(0,1).getUnit().getEquippedItem().getName());
    assertEquals(40, controllerSmall.getGameMap().getCell(0,1).getUnit().getCurrentHitPoints());
    assertEquals(40, controllerSmall.getSelectedUnit().getCurrentHitPoints());

    //Round 3
    controllerSmall.endTurn();
    controllerSmall.selectUnitIn(0,1);
    controllerSmall.useItemOn(1,1);
    assertEquals(30, controllerSmall.getSelectedUnit().getCurrentHitPoints());
    assertEquals(30,controllerSmall.getGameMap().getCell(1,1).getUnit().getCurrentHitPoints());

    controllerSmall.endTurn();
    controllerSmall.selectUnitIn(1,1);
    controllerSmall.useItemOn(0,1);
    assertEquals(20, controllerSmall.getSelectedUnit().getCurrentHitPoints());
    assertEquals(20,controllerSmall.getGameMap().getCell(0,1).getUnit().getCurrentHitPoints());

    //Round 4
    controllerSmall.endTurn();
    controllerSmall.selectUnitIn(0,1);
    controllerSmall.useItemOn(1,1);
    assertEquals(10, controllerSmall.getSelectedUnit().getCurrentHitPoints());
    assertEquals(10,controllerSmall.getGameMap().getCell(1,1).getUnit().getCurrentHitPoints());

    controllerSmall.endTurn();
    controllerSmall.selectUnitIn(1,1);
    controllerSmall.useItemOn(0,1);
    assertEquals(2, controllerSmall.getTacticians().size());
    assertEquals(3, controllerSmall.getTurnOwner().getUnits().size());
    assertEquals(2, controllerSmall.getTacticians().get(0).getUnits().size());
    assertTrue(controllerSmall.getTacticians().get(0).getStatus());

    controllerSmall.endTurn();
    controllerSmall.selectUnitIn(0,2);
    controllerSmall.useItemOn(1,1);
    assertEquals(1, controllerSmall.getTacticians().size());
  }


  /**
   * STATUS INITIAL
   *
   *     #  #  #  #  #  #  #
   * 6 # 3  +##+  +  +##+##+#
   * 5 # 3##3  +##4  4  4##+#
   * 4 # +##+  +##+  +##+  +#
   * 3 # +  +##+  +##+  +  +#
   * 2 # +  +  +  +##2##+##+#
   * 1 # 1  +  +##+##2  2  +#
   * 0 # 1  1  +  +  +  +  +#
   *     #  #  #  #  #  #  #
   *     0  1  2  3  4  5  6
   *
   * STATUS FINAL
   *
   *  #  #  #  #  #  #  #
   * #+  +##+  +  +##+##+#
   * #+##+  +##+  +  +##+#
   * #+##+  +##+  +##+  3#
   * #+  +##+  +##+  +  +#
   * #+  +  3  +##+##+##+#
   * #+  +  0##+##3  +  +#
   * #0  0  +  +  +  +  +#
   *  #  #  #  #  #  #  #
   */
  public void gameNormal(){
    assertEquals(0, controller.getTurnCurrent());
    assertEquals(0, controller.getRoundNumber());
    controller.initGame(3);
    assignUnitToTactician();
    // PLAYER 0

    // System.out.println(controller.getGameMap().toString());
    assertEquals(controller.getTacticians().size(), controller.getInitPlayerStatus().size());
    controller.selectUnitIn(1,5);
    assertNull(controller.getCurrentUnit());

    // test move current unit first time
    // select archer
    controller.selectUnitIn(0,0);
    controller.getTurnOwner().getUnits().forEach(i -> i.equipItem(i.getItems().get(0)));
    //controller.equipItem(0);
    assertEquals(controller.getSelectedUnit(), controller.getCurrentUnit());
    controller.moveToSelectedUnit(1,1);
    assertNull(controller.getGameMap().getCell(0,0).getUnit());

    // test move current unit second time
    controller.moveToSelectedUnit(2,1);
    Location locNew0 = controller.getGameMap().getCell(1,1);
    assertEquals(locNew0, controller.getSelectedUnit().getLocation());

    // test move second unit of tactician
    // select alpaca
    controller.selectUnitIn(1,0);
    controller.moveToSelectedUnit(2,0);
    Location locNew1 = controller.getGameMap().getCell(1,0);
    assertNull(locNew1.getUnit());

    // test move third unit of tactician
    // select sorcerer
    controller.selectUnitIn(0,1);
    //controller.equipItem(0);
    controller.moveToSelectedUnit(0,3);
    Location locNew2 = controller.getGameMap().getCell(1,0);
    assertNull(locNew2.getUnit());
    // System.out.println(controller.getGameMap().toString());

    controller.endTurn();
    // PLAYER 1

    controller.getTurnOwner().getUnits().forEach(i -> i.equipItem(i.getItems().get(0)));

    //select fighter
    controller.selectUnitIn(1,5);
    controller.moveToSelectedUnit(0,4);
    controller.useItemOn(0,3);
    assertEquals(35, controller.getSelectedUnit().getCurrentHitPoints());
    assertEquals(35, controller.getGameMap().getCell(0,3).getUnit().getCurrentHitPoints());

    //select cleric
    controller.selectUnitIn(2,5);
    controller.moveToSelectedUnit(3,5);

    //select hero
    controller.selectUnitIn(1,6);
    controller.moveToSelectedUnit(3,6);

    // controller change items with a player different of the current

    controller.selectUnitIn(6,0);
    assertEquals(1, controller.getSelectedUnit().getItems().size());
    assertEquals(1, controller.getGameMap().getCell(5,0).getUnit().getItems().size());
    controller.addSpearToSelectedUnit();
    assertEquals(2, controller.getSelectedUnit().getItems().size());
    controller.selectItem(1);
    controller.giveItemTo(5,0);
    assertEquals(1,controller.getSelectedUnit().getItems().size());
    assertEquals(2, controller.getGameMap().getCell(5,0).getUnit().getItems().size());

    controller.endTurn();
    // PLAYER 2

    controller.getTurnOwner().getUnits().forEach(i -> i.equipItem(i.getItems().get(0)));

    //select fighter
    controller.selectUnitIn(5,1);
    //controller.equipItem(0);
    controller.moveToSelectedUnit(3,1);
    controller.useItemOn(1,1);
    assertEquals(40, controller.getGameMap().getCell(1,1).getUnit().getCurrentHitPoints());
    assertEquals(40, controller.getSelectedUnit().getCurrentHitPoints());

    //select hero
    controller.selectUnitIn(6,0);
    controller.equipItem(0);
    controller.moveToSelectedUnit(5,1);

    //select hero
    controller.selectUnitIn(5,0);
    controller.equipItem(0);
    controller.moveToSelectedUnit(3,0);
    assertEquals(controller.getUnitsMoved().size(), controller.getTurnOwner().getMoves().size());

    controller.endTurn();
    assertEquals(0, controller.getUnitsMoved().size());

    // PLAYER 3
    controller.getTurnOwner().getUnits().forEach(i -> i.equipItem(i.getItems().get(0)));

    //select sorcerer
    controller.selectUnitIn(5,5);
    //controller.equipItem(0);
    controller.moveToSelectedUnit(4,6);

    //select sm
    controller.selectUnitIn(5,4);
    //controller.equipItem(0);
    controller.moveToSelectedUnit(3,4);
    assertEquals(controller.getSelectedUnit().getLocation(), controller.getGameMap().getCell(3,4));
    assertEquals(controller.getGameMap().getCell(3,4).getUnit(), controller.getSelectedUnit());

    // select sorcerer
    controller.selectUnitIn(4,6);
    // sorcerer attack to hero
    controller.useItemOn(3,6);
    assertEquals(35, controller.getGameMap().getCell(3,6).getUnit().getCurrentHitPoints());
    assertEquals(35, controller.getSelectedUnit().getCurrentHitPoints());

    //select fighter
    controller.selectUnitIn(5,3);
    //controller.equipItem(0);
    controller.moveToSelectedUnit(3,3);

    assertEquals(50, controller.getSelectedUnit().getCurrentHitPoints());

    controller.endTurn();
    /////////////////////////////////////////////////////////////
    ///////////           ROUND 2               /////////////////
    /////////////////////////////////////////////////////////////

    // System.out.println(controller.getGameMap().toString());
    assertEquals(2, controller.getRoundNumber());
    assertEquals(0, controller.getTurnCurrent());
    assertFalse(controller.getTurnOwner().getName().equals("Player 0"));
    // player 1, player 2, player 0, player 3 (Game with seed)
    assertEquals("Player 1" , controller.getTurnOwner().getName());

    // select to cleric in Player 1
    // give a item to other unit (not contained in current player's inventory)
    controller.selectUnitIn(3,5);
    assertEquals(1, controller.getGameMap().getCell(3,6).getUnit().getItems().size());
    assertEquals(1, controller.getGameMap().getCell(3,4).getUnit().getItems().size());

    // System.out.println(controller.getNamePlayers().toString());
    controller.addSoulToSelectedUnit();
    // cleric with 2 items
    assertEquals(2, controller.getSelectedUnit().getItems().size());

    controller.selectItem(1);
    controller.giveItemTo(3,4);
    assertEquals(2, controller.getSelectedUnit().getItems().size());
    // give a item to own unit
    controller.giveItemTo(3,6);
    assertEquals(1,controller.getSelectedUnit().getItems().size());
    assertEquals(2, controller.getGameMap().getCell(3,6).getUnit().getItems().size());

    // hero attack to sorcerer
    controller.selectUnitIn(3,6);
    controller.useItemOn(4,6);
    assertEquals(20, controller.getSelectedUnit().getCurrentHitPoints());
    assertEquals(20, controller.getGameMap().getCell(4,6).getUnit().getCurrentHitPoints());

    // fighter P1 attack to sorcerer P0
    controller.selectUnitIn(0,4);
    controller.useItemOn(0,3);
    assertEquals(20, controller.getSelectedUnit().getCurrentHitPoints());
    assertEquals(20, controller.getGameMap().getCell(0,3).getUnit().getCurrentHitPoints());

    controller.endTurn();

    // select to fighter Player 2
    controller.selectUnitIn(3,1);
    // fighter move to (4,2)
    // fighter search the way more close to attack
    controller.moveToSelectedUnit(4,2);
    assertEquals(40, controller.getSelectedUnit().getCurrentHitPoints());
    // fighter attack to fighter in (3,3)
    controller.useItemOn(3,3);
    assertEquals(30, controller.getSelectedUnit().getCurrentHitPoints());
    assertEquals(40, controller.getGameMap().getCell(3,3).getUnit().getCurrentHitPoints());

    // hero move to (3,1) and attack to archer in (1,1)
    controller.selectUnitIn(5,1);
    controller.moveToSelectedUnit(3,1);
    controller.useItemOn(1,1);
    assertEquals(40, controller.getSelectedUnit().getCurrentHitPoints());
    assertEquals(30, controller.getGameMap().getCell(1,1).getUnit().getCurrentHitPoints());
    // System.out.println(controller.getGameMap().toString());

    controller.endTurn();
    // PLAYER 0

    // archer attack to hero in (1,2) (Player 2)
    controller.selectUnitIn(1,1);
    controller.useItemOn(3,0);
    assertEquals(30, controller.getSelectedUnit().getCurrentHitPoints());
    assertEquals(40, controller.getGameMap().getCell(3,0).getUnit().getCurrentHitPoints());
    controller.moveToSelectedUnit(0,1);

    // select sorcerer
    // sorcerer attack to fighter
    controller.selectUnitIn(0,3);
    assertEquals(20, controller.getSelectedUnit().getCurrentHitPoints());
    controller.useItemOn(0,4);
    assertEquals(5, controller.getSelectedUnit().getCurrentHitPoints());
    assertEquals(5, controller.getGameMap().getCell(0,4).getUnit().getCurrentHitPoints());
    controller.moveToSelectedUnit(1,2);

    // System.out.println(controller.getGameMap().toString());

    controller.selectUnitIn(2,0);
    controller.moveToSelectedUnit(0,0);

    controller.endTurn();
    // PLAYER 3

    // sw of player 3 attack to fighter of player 1
    controller.selectUnitIn(3,4);
    controller.moveToSelectedUnit(1,4);
    controller.useItemOn(0,4);
    assertEquals(50, controller.getSelectedUnit().getCurrentHitPoints());
    assertNull(controller.getGameMap().getCell(0,4).getUnit());
    assertEquals(4, controller.getTacticians().size());
    for(Tactician t : controller.getTacticians()){
      if(t.getName().equals("Player 1")){
        assertEquals(2,t.getUnits().size());
      }
    }

    // sorcerer P3 attack to hero P1
    controller.selectUnitIn(4,6);
    controller.useItemOn(3,6);
    assertEquals(5, controller.getSelectedUnit().getCurrentHitPoints());
    assertEquals(5, controller.getGameMap().getCell(3,6).getUnit().getCurrentHitPoints());

    // select fighter P3 attack to hero P2
    controller.selectUnitIn(3,3);
    controller.moveToSelectedUnit(2,2);
    controller.useItemOn(3,1);
    assertEquals(40, controller.getSelectedUnit().getCurrentHitPoints());
    assertEquals(25, controller.getGameMap().getCell(3,1).getUnit().getCurrentHitPoints());

    controller.endTurn();
    /////////////////////////////////////////////////////////////
    ///////////           ROUND 3               /////////////////
    /////////////////////////////////////////////////////////////

    // System.out.println(controller.getGameMap().toString());
    // controller.getTacticians().forEach(i -> System.out.println(i.getName()));

    // Player 1
    controller.selectUnitIn(0,1);
    controller.useItemOn(3,1);
    assertEquals(30, controller.getSelectedUnit().getCurrentHitPoints());
    assertEquals(15, controller.getGameMap().getCell(3,1).getUnit().getCurrentHitPoints());


    controller.endTurn();

    controller.selectUnitIn(4,6);
    controller.useItemOn(3,6);
    assertEquals(3, controller.getTacticians().size());

    controller.selectUnitIn(2,2);
    controller.useItemOn(3,1);
    assertEquals(2, controller.getTacticians().size());

    controller.endTurn();

    assertEquals(2, controller.getWinners().size());
    assertEquals("Player 0", controller.getWinners().get(0));
    assertEquals("Player 3", controller.getWinners().get(1));
    System.out.println(controller.getGameMap().toString());
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

  @Test
  public void addItems(){
    IUnit alpaca = controller.getAlpacaFab().createUnit();
    controller.putUnitInMap(alpaca, 0, 0);
    controller.selectUnitIn(0,0);
    controller.addAxeToSelectedUnit();
    assertEquals(1, controller.getSelectedUnit().getItems().size());
    assertEquals(Axe.class, controller.getItems().get(0).getClass());
    controller.addBowToSelectedUnit();
    assertEquals(2, controller.getSelectedUnit().getItems().size());
    assertEquals(Bow.class, controller.getItems().get(1).getClass());
    controller.addDarknessToSelectedUnit();
    assertEquals(3, controller.getSelectedUnit().getItems().size());
    assertEquals(Darkness.class, controller.getItems().get(2).getClass());
    controller.addLightToSelectedUnit();
    assertEquals(4, controller.getSelectedUnit().getItems().size());
    assertEquals(Light.class, controller.getItems().get(3).getClass());
    controller.addSoulToSelectedUnit();
    assertEquals(5, controller.getSelectedUnit().getItems().size());
    assertEquals(Soul.class, controller.getItems().get(4).getClass());
    controller.addSpearToSelectedUnit();
    assertEquals(6, controller.getSelectedUnit().getItems().size());
    assertEquals(Spear.class, controller.getItems().get(5).getClass());
    controller.addSwordToSelectedUnit();
    assertEquals(7, controller.getSelectedUnit().getItems().size());
    assertEquals(Sword.class, controller.getItems().get(6).getClass());
    controller.addStaffToSelectedUnit();
    assertEquals(8, controller.getSelectedUnit().getItems().size());
    assertEquals(Staff.class, controller.getItems().get(7).getClass());
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

    fabMap.get("hero").addItemForDefault();
    controller.addHeroToTactician((SpecialUnit) fabMap.get("hero").createUnit());
    fabMap.get("hero").addItemForDefault();
    controller.addHeroToTactician((SpecialUnit) fabMap.get("hero").createUnit());
    fabMap.get("fighter").addItemForDefault();
    controller.addUnitToTactician((NormalUnit) fabMap.get("fighter").createUnit());
    controller.putUnitInMap(controller.getTurnOwner().getUnits().get(0), 6,0);
    controller.putUnitInMap(controller.getTurnOwner().getUnits().get(1), 5,0);
    controller.putUnitInMap(controller.getTurnOwner().getUnits().get(2), 5,1);

    controller.changeToNextTurn();

    fabMap.get("sorcerer").addItemForDefault();
    controller.addUnitToTactician((NormalUnit) fabMap.get("sorcerer").createUnit());
    controller.addUnitToTactician((NormalUnit) fabMap.get("sm").createUnit());
    fabMap.get("fighter").addItemForDefault();
    controller.addUnitToTactician((NormalUnit) fabMap.get("fighter").createUnit());
    controller.putUnitInMap(controller.getTurnOwner().getUnits().get(0), 5,5);
    controller.putUnitInMap(controller.getTurnOwner().getUnits().get(1), 5,4);
    controller.putUnitInMap(controller.getTurnOwner().getUnits().get(2), 5,3);

    controller.changeToNextTurn();
  }


}