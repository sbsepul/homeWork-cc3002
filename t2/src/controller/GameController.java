package controller;

import model.items.IEquipableItem;
import model.map.Field;
import model.map.Location;
import model.units.IUnit;

import java.util.*;

/**
 * Controller of the game.
 * The controller manages all the input received from de game's GUI.
 *
 * @author Sebastian Sepulveda
 * @version 2.0
 * @since 2.0
 */
public class GameController {

  private int turnCurrent;
  private Random random = new Random();
  private int numPlayers;
  private final int tamMap;
  private List<Tactician> players;
  private Field map;
  private long maxRounds;
  private int numRounds;
  private IUnit selectedUnit;
  private IEquipableItem selectedItem;


  /**
   * Creates the controller for a new game.
   *
   * @param numberOfPlayers
   *     the number of players for this game
   * @param mapSize
   *     the dimensions of the map, for simplicity, all maps are squares
   */
  public GameController(int numberOfPlayers, int mapSize) {
    this.numPlayers = numberOfPlayers;
    this.tamMap = mapSize;
    this.map = createNewMap(mapSize);
    this.players = createTacticians(numberOfPlayers);
  }

  private Field createNewMap(int tamMapInit) {
    Field mapCreate = new Field();
    //adding locations to map
    List<Location> locations = new ArrayList<>();
    //int n = (int) Math.floor(Math.sqrt(mapSize));
    int n = (int) Math.pow(tamMapInit,2);
    for(int i = 0; i < n; i++){
      for(int j = 0; i < n; i++){
        locations.add(new Location(i,j));
      }
    }
    for(Location cell:locations) {
      mapCreate.addCells(true, cell);
    }
    return mapCreate;
  }

  private void resetController(){
    this.players = createTacticians(this.numPlayers);
    this.map = createNewMap(this.tamMap);
  }

  /**
   * Getter the number of players in the game
   * @return
   */
  public int getNumPlayers() {
    return getTacticians().size();
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

  private List<Tactician> createTacticians(int numTacticians){
    List<Tactician> tact = new ArrayList<Tactician>(numTacticians);
    for(int i=0; i<numTacticians; i++){
      String name = "Player " + i;
      tact.add(new Tactician(name));
    }
    return tact;
  }

  /**
   *
   * @return
   */
  public int getPosTurnOwner(){
    return turnCurrent;
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
  public long getMaxRounds() {
    return maxRounds;
  }

  public void setSeedRandom(){
    random.setSeed(500);
  }
  /**
   *
   * @return a Random order for the tactician in a new Round
   */
  private void createNewRound(List<Tactician> players){
    setSeedRandom();
    int nPlayers = this.getTacticians().size();
    Tactician ini_t = this.getTacticians().get(nPlayers-1);
    for(int i = 0; i <  nPlayers; i++){
      int posRandom = random.nextInt(nPlayers);
      Tactician temp_t = players.get(i);
      players.set(i, players.get(posRandom));
      players.set(posRandom, temp_t);
    }
    if(players.get(0).equals(ini_t)){
      Tactician temp = players.get(0);
      players.set(0, players.get(nPlayers-1));
      players.set(nPlayers-1,temp);
    }
  }

  private void changeToNextTurn(){
    int nPlayers = getTacticians().size();
    if(turnCurrent==nPlayers-1){
      this.turnCurrent=0;
    }
    else this.turnCurrent++;

  }
  /**
   * Finishes the current player's turn.
   */
  public void endTurn() {
    //verify if a player has lost, for example in a counter attack
    if(getTurnOwner().canPlay()){
      if(turnCurrent==getTacticians().size()-1) this.numRounds++;
      this.changeToNextTurn();
    }
    else{
      players.remove(turnCurrent);
      this.changeToNextTurn();
    }
  }

  /**
   * Removes a tactician and all of it's units from the game.
   *
   * @param tactician
   *     the player to be removed
   */
  public void removeTactician(String tactician) {
    for(int i = 0; i<getTacticians().size(); i++){
      String name = getTacticians().get(i).getName();
      if(getTacticians().get(i).getName().equals(tactician)){
        players.remove(i);
      }
    }
    //int index = namePlayers.indexOf(tactician);
    //players.remove(index);
    //namePlayers.remove(index);
  }

  /**
   *
   * @return
   */
  public boolean notOver(){
    if(this.getMaxRounds()==-1){
      /*
      No acaba hasta que todos los jugadores se retiren
       */
      if(this.isOnlyAPlayer() || this.dieAllHero()) return false;
      return true;
    }
    else{
      if(this.isOnlyAPlayer() || this.dieAllHero() || this.getMaxRounds()==this.getRoundNumber()){
        this.updateWinners();
        return false;
      }
      // Round number < Max rounds
      else return true;
    }
  }

  /**
   *
   */
  public void updateWinners() {

  }

  /**
   *
   * @return
   */
  public boolean dieAllHero() {
    return false;
  }

  /**
   *
   * @return
   */
  public boolean isOnlyAPlayer() {
    if(getTacticians().size()==1){
      return true;
    }
    return false;
  }

  /**
   * Starts the game.
   * @param maxTurns
   *  the maximum number of turns the game can last
   */
  public void initGame(final int maxTurns) {
    this.maxRounds = maxTurns;
    this.numRounds = 1;
    this.turnCurrent = 0;
    this.resetController();
    //boolean time = false;
    //if(maxTurns==-1){
    //  time = true;
    //}

    //while(numRounds<=getMaxRounds() || time){
      /*
       colocar aqui todo lo que significa iniciar una partida
       asegurarse cuando termina una partida
       aqui se supone que se deben tomar acciones que el controller debe estar observando
       En este caso se estaria ocupando el patron de diseño Observer, donde el observado seria el
       jugador actual y el controller seria el controlador
      */
    //}
    // detener el juego y retornar ganadores????? (no devuelve nada este metodo)
  }

  private int getPlayersInit() {
    return numPlayers;
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
    List<String> win = new ArrayList<>();
    if(getMaxRounds()==-1){
      if(isOnlyAPlayer()){
        win.add(getTacticians().get(0).getName());
        return win;
      }
      return null;
    }
    if(getRoundNumber()>getMaxRounds()){
      int maxUnits = 0;
      for(Tactician t: this.getTacticians()){
        // verificando en initGame o en otro proceso que el numero de
        // partidas ya se acabo, entonces hay que ver cual t tiene
        // mayor cantidad de unidades restantes
        if(t.getUnits().size()>=maxUnits){
          maxUnits = t.getUnits().size();
          if(win.contains(t.getName())) continue;
          else win.add(t.getName());
        }
        else if(t.getUnits().isEmpty()){
          win.add(t.getName());
        }
      }
      return win;
    }
    else return null;
  }

  /**
   * @return the current player's selected unit
   */
  public IUnit getSelectedUnit() {
    return selectedUnit;
  }

  private void setSelectedUnit(IUnit newUnit){
    selectedUnit = newUnit;
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
     IUnit selectedUnit = map.getCell(x,y).getUnit();
     if(selectedUnit != null){
       //this.getTurnOwner().setCurrentUnit(map.getCell(x,y).getUnit());
       this.setSelectedUnit(selectedUnit);
     }
  }

  /**
   * @return the inventory of the currently selected unit.
   */
  public List<IEquipableItem> getItems() {
    return this.getTurnOwner().getItemsCurrentUnit();
  }

  /**
   * Equips an item from the inventory to the currently selected unit.
   *
   * @param index
   *     the location of the item in the inventory.
   */
  public void equipItem(int index) {
    IEquipableItem item = this.getTurnOwner().getItemsCurrentUnit().get(index);
    this.getTurnOwner().setEquipItem(item);
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
    IUnit enemy = getGameMap().getCell(x,y).getUnit();
    if(enemy!=null) {
      getTurnOwner().getCurrentUnit().attack(enemy);
    }
  }

  /**
   * Selects an item from the selected unit's inventory.
   *
   * @param index
   *     the location of the item in the inventory.
   */
  public void selectItem(int index) {
    selectedItem = getSelectedUnit().getItems().get(index);
  }

  public IEquipableItem getSelectedItem(){
    return selectedItem;
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
    Tactician actual = getTurnOwner();
    IUnit unitCurrent = getTurnOwner().getCurrentUnit();
    //si la unidad actual tiene el item equipado
    if(unitCurrent.getItems().contains(getSelectedItem())){
      //si el jugador actual contiene a target unit
      IUnit target = getGameMap().getCell(x,y).getUnit();
      if(actual.getUnits().contains(target)){
        getTurnOwner().getCurrentUnit().giveItem(
                getGameMap().getCell(x,y).getUnit(),getSelectedItem());
      }
    }
  }
}
