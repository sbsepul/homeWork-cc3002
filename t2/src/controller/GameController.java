package controller;

import model.items.IEquipableItem;
import model.map.Field;
import model.map.Location;
import model.units.IUnit;

import java.io.IOException;
import java.util.*;

/**
 * Controller of the game.
 * The controller manages all the input received from de game's GUI.
 *
 * @author Ignacio Slater Muñoz
 * @version 2.0
 * @since 2.0
 */
public class GameController {

  private int turnCurrent = 0;
  private Random random = new Random();
  private int numPlayers;
  private int tamMap;
  private List<Tactician> players;
  private List<String> namePlayers;
  private Field map;
  private int maxRounds;
  private int numRounds;


  /**
   * Creates the controller for a new game.
   *
   * @param numberOfPlayers
   *     the number of players for this game
   * @param mapSize
   *     the dimensions of the map, for simplicity, all maps are squares
   */
  public GameController(int numberOfPlayers, int mapSize) {
    numPlayers = numberOfPlayers;
    tamMap = mapSize;
    // desordenamos la lista
    players = new ArrayList<>(numberOfPlayers);
    // seed
    random.setSeed(500);
    for(int i=0; i<numberOfPlayers; i++){
      int posRandom = random.nextInt(numberOfPlayers);
      String name = "Player " + Integer.toString(i+1);
      namePlayers.add(posRandom, name);
      players.add(posRandom, new Tactician(name));
    }

    //adding locations to map
    List<Location> locations = new ArrayList<>();
    double n = Math.sqrt(mapSize);
    for(int i = 0; i < (int) n; i++){
      for(int j = 0; i < (int) n; i++){
        locations.add(new Location(i,j));
      }
    }
    for(Location cell:locations) {
      map.addCells(true, cell);
    }
  }

  /**
   * @return the list of all the tacticians participating in the game.
   */
  public List<Tactician> getTacticians() {
    return List.copyOf(players);
  }

  /**
   * @return the map of the current game
   */
  public Field getGameMap() {
    return map;
  }

  /**
   * @return the tactician that's currently playing
   */
  public Tactician getTurnOwner() {
    return players.get(turnCurrent);
  }

  /**
   * @return the number of rounds since the start of the game.
   */
  public int getRoundNumber() {
    return numRounds;
  }

  /**
   * @return the maximum number of rounds a match can last
   */
  public int getMaxRounds() {
    return maxRounds;
  }

  /**
   * Finishes the current player's turn.
   */
  public void endTurn() {
    //verify if a player has lost, for example in a counter attack
    if(getTurnOwner().canPlay()){
      // if can
      turnCurrent++;
    }
    else{
      players.remove(turnCurrent);
    }
  }

  /**
   * Removes a tactician and all of it's units from the game.
   *
   * @param tactician
   *     the player to be removed
   */
  public void removeTactician(String tactician) {
    int index = namePlayers.indexOf(tactician);
    players.remove(index);
    namePlayers.remove(index);
  }

  /**
   * Starts the game.
   * @param maxTurns
   *  the maximum number of turns the game can last
   */
  public void initGame(final int maxTurns) {
    maxRounds = maxTurns;
    numRounds = maxRounds;
    boolean time = false;
    if(maxTurns==-1){
      time = true;
    }
    while(numRounds<=getMaxRounds() || time){
      /*
       colocar aqui todo lo que significa iniciar una partida
       asegurarse cuando termina una partida
      */
    }
    // detener el juego y retornar ganadores????? (no devuelve nada este metodo)
  }

  /**
   * Starts a game without a limit of turns.
   */
  public void initEndlessGame() {
    initGame(-1);
  }

  /**
   * @return the winner of this game, if the match ends in a draw returns a list of all the winners
   */
  public List<String> getWinners() {
    ArrayList<String> winners = null;
    Map<String, Integer> unitRest = new HashMap<String,Integer>();
    for(Tactician t: this.getTacticians()){
      // verificando en initGame o en otro proceso que el numero de
      // partidas ya se acabo, entonces hay que ver cual t tiene
      // mayor cantidad de unidades restantes
    }
    return winners;
  }

  /**
   * @return the current player's selected unit
   */
  public IUnit getSelectedUnit() {
    return getTurnOwner().getCurrentUnit();
  }

  /**
   * Selects a unit in the game map
   *
   * @param x
   *     horizontal position of the unit
   * @param y
   *     vertical position of the unit
   */
  public void selectUnitIn(int x, int y) {

  }

  /**
   * @return the inventory of the currently selected unit.
   */
  public List<IEquipableItem> getItems() {
    return null;
  }

  /**
   * Equips an item from the inventory to the currently selected unit.
   *
   * @param index
   *     the location of the item in the inventory.
   */
  public void equipItem(int index) {

  }

  /**
   * Uses the equipped item on a target
   *
   * @param x
   *     horizontal position of the target
   * @param y
   *     vertical position of the target
   */
  public void useItemOn(int x, int y) {

  }

  /**
   * Selects an item from the selected unit's inventory.
   *
   * @param index
   *     the location of the item in the inventory.
   */
  public void selectItem(int index) {

  }

  /**
   * Gives the selected item to a target unit.
   *
   * @param x
   *     horizontal position of the target
   * @param y
   *     vertical position of the target
   */
  public void giveItemTo(int x, int y) {

  }
}
