package controller;

import model.items.Spear;
import model.items.factoryItem.*;
import model.map.InvalidLocation;
import model.units.Alpaca;
import model.units.factoryUnit.*;
import model.items.IEquipableItem;
import model.map.Field;
import model.units.IUnit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.security.interfaces.XECKey;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;


/**
 * @author Ignacio Slater Muñoz
 * @since v2.0
 */
class GameControllerTest {

  private GameController controller;
  private long randomSeed;
  private List<String> testTacticians;
  private IFactoryUnit unitFactory;
  private IFactoryItem itemFactory;

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
    Random testRandom = new Random();
    testRandom.setSeed(randomSeed);
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
    Random randomEndTur = new Random(212121);
    Tactician firstPlayer = controller.getTurnOwner();
    // Nuevamente, para determinar el orden de los jugadores se debe usar una semilla
    Tactician secondPlayer = new Tactician("Player 1"); // <- Deben cambiar esto (!)
    assertNotEquals(secondPlayer.getName(), firstPlayer.getName());

    controller.endTurn();
    assertNotEquals(firstPlayer.getName(), controller.getTurnOwner().getName());
    assertEquals(secondPlayer.getName(), controller.getTurnOwner().getName());
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
    IntStream.range(0, 8).forEach(i -> controller.endTurn());
    assertEquals(3, controller.getRoundNumber());
    assertEquals(4, controller.getWinners().size());
    controller.getWinners()
        .forEach(player -> Assertions.assertTrue(testTacticians.contains(player)));

    controller.initGame(2);
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
  public void requestUnitTest(){
    controller.initGame(2);
    IUnit unit = controller.requestUnit(UnitType.ALPACA);
    assertEquals(unit.getClass(), Alpaca.class);
  }

  @Test
  public void addUnitToTacticianTest(){
    IUnit hero = controller.requestUnit(UnitType.HERO);
    IUnit unit = controller.requestUnit(UnitType.ARCHER);
    controller.addHeroToTactician();
    assertEquals(1, controller.getTurnOwner().getUnits().size());
    assertEquals(hero.getClass(), controller.getTurnOwner().getUnits().get(0).getClass());
    controller.addUnitToTactician(unit);
    assertEquals(2, controller.getTurnOwner().getUnits().size());
  }

  @Test
  public void putInMap(){
    IFactoryUnit archerFab = controller.getArcherFab();
    IUnit unit = controller.requestUnit(UnitType.SORCERER);
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
    IUnit unit = controller.requestUnit(UnitType.ARCHER);
    controller.setSelectedUnit(unit);
    assertEquals(unit, controller.getSelectedUnit());
  }

  @Test
  public void selectUnitIn() {
    assertNull(controller.getGameMap().getCell(0,0).getUnit());
    IUnit unitNew = controller.requestUnit(UnitType.SWORDMASTER);
    controller.putUnitInMap(unitNew, 0,0);
    assertTrue(controller.getGameMap().getCell(0,0).getUnit().equals(unitNew));
    IUnit unit = controller.getGameMap().getCell(0,0).getUnit();
    controller.selectUnitIn(1,1);
    assertNull(controller.getSelectedUnit());
    controller.selectUnitIn(0,0);
    assertEquals(unit,controller.getSelectedUnit());
    IUnit unitTwo = controller.requestUnit(UnitType.CLERIC);
    controller.putUnitInMap(unitTwo, 3,3);
    //celda 3,3 no es valida
    assertEquals(controller.getGameMap().getCell(3,3), unitTwo.getLocation());
    assertEquals(unitTwo,controller.getGameMap().getCell(3,3).getUnit());
    controller.selectUnitIn(3,3);
    assertEquals(unitTwo, controller.getSelectedUnit());
  }

  ////////////////////////////////////////////////////////
  ////////////////////////////////////////////////////////
  ////////////////////////////////////////////////////////
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
    assertTrue(controller.getResponseStatusTactician().get(controller.getPosTurnOwner()).getProperty());
    controller.getTurnOwner().retire();
    assertFalse(controller.getResponseStatusTactician().get(controller.getPosTurnOwner()).getProperty());
  }

  @Test
  public void useItemOn() {
    IFactoryUnit archer = controller.getArcherFab();
    IFactoryUnit fighter = controller.getFighterFab();
    archer.addItemForDefault();
    fighter.addItemForDefault();
    controller.putUnitInMap(archer.createUnit(), 0,0);
    controller.putUnitInMap(fighter.createUnit(),1,1);
    System.out.println(controller.getGameMap().toString());
    controller.selectUnitIn(0,0);
    assertEquals(50, controller.getSelectedUnit().getCurrentHitPoints());
    assertEquals(1, controller.getSelectedUnit().getItems().size());
    controller.equipItem(0);
    controller.selectUnitIn(1,1);
    controller.equipItem(0);
    controller.selectUnitIn(0,0);
    controller.useItemOn(1,1);
    assertEquals(40, controller.getSelectedUnit().getCurrentHitPoints());
    assertEquals(40, controller.getGameMap().getCell(1,1).getUnit().getCurrentHitPoints());
  }

  @Test
  public void selectItem() {
    assertNull(controller.getSelectedItem());
    IFactoryUnit alpaca = controller.getAlpacaFab();
    alpaca.setItems(
            controller.getAxeFab().createItem(),
            controller.getSwordFab().createItem(),
            controller.getBowFab().createItem(),
            controller.getLightFab().createItem()
    );
    controller.putUnitInMap(alpaca.createUnit(), 1,1);
    controller.selectUnitIn(1,1);
    controller.selectItem(0);
    assertEquals(controller.getAxeFab().createItem().getName(),controller.getSelectedItem());
    controller.selectItem(1);

    controller.selectItem(2);

    controller.selectItem(3);
  }

  @Test
  public void giveItemTo() {
  }
}