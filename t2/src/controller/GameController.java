package controller;

import model.items.factoryItem.*;
import model.map.Location;
import model.map.factoryMap.FactoryMap;
import model.map.factoryMap.IFactoryMap;
import model.units.Hero;
import model.units.factoryUnit.*;
import model.items.IEquipableItem;
import model.map.Field;
import model.units.IUnit;

import java.util.*;

/**
 * Controller of the game.
 * The controller manages all the input received from de game's GUI.
 *
 * @author Sebastian Sepulveda
 * @version 1.0
 * @since 2.0
 */
public class GameController {

  private Random random = new Random(212121);
  private long maxRounds;
  private int numRounds;
  private int turnCurrent=0;
  private final int numPlayers;
  private final int tamMap;
  private Field map;
  private List<Tactician> players;
  private IUnit selectedUnit;
  private IEquipableItem selectedItem;
  private IFactoryMap factoryMap;
  private FactoryProviderUnit factoryUnit;
  private FactoryItemProvider factoryItem;
  private List<ResponseStatusTactician> responseStatusTactician = new ArrayList<>();
  private List<ResponseHeroes> responseHeroes = new ArrayList<>();

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
    this.factoryItem = new FactoryItemProvider();
    this.factoryUnit = new FactoryProviderUnit();
  }

  /**
   * Create a instance of Field with a Factory Map
   * @param tamMapInit for default is square. N x N
   * @return a Field of N x N
   */
  public Field createNewMap(int tamMapInit) {
    this.factoryMap = new FactoryMap(this.tamMap);
    return factoryMap.createMap();
  }

  /**
   * Reset the game to the beginning
   */
  public void resetController(){
    this.players = createTacticians(this.numPlayers);
    this.map = createNewMap(this.tamMap);
  }

  public Random getRandom() {
    return random;
  }

  /**
   * Getter the number of players in the game
   * @return the number of players
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
    return this.players.get(this.turnCurrent);
  }

  /**
   * @return
   */
  public List<ResponseStatusTactician> getResponseStatusTactician() {
    return responseStatusTactician;
  }

  /**
   * Create a list of Tacticians
   *
   * @param numTacticians that begin in the game
   * @return a list of tacticians without units
   */
  public List<Tactician> createTacticians(int numTacticians){
    List<Tactician> tact = new ArrayList<Tactician>(numTacticians);
    for(int i=0; i<numTacticians; i++){
      String name = "Player " + i;
      tact.add(new Tactician(name));
      responseStatusTactician.add(new ResponseStatusTactician());
      responseStatusTactician.get(i).setProperty(true);
    }
    int index=0;
    for(Tactician t : tact){
      t.addResponseStatusTactician(responseStatusTactician.get(index));
      index++;
    }
    return tact;
  }

  /**
   * Getter the position of the current player of the list of Tacticians
   * @return a integer of current player
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

  /**
   *
   * @return a Random order for the tactician in a new Round
   */
  public void createNewRound(){
    int nPlayers = this.players.size();
    Tactician ini_t = this.players.get(nPlayers-1);
    for(int i = 0; i <  nPlayers; i++){
      int posRandom = getRandom().nextInt(nPlayers);
      Tactician temp_t = players.get(i);
      Tactician other = players.get(posRandom);
      players.set(posRandom, temp_t);
      players.set(i, other);
    }
    if(players.get(0).equals(ini_t)){
      Tactician temp = players.get(0);
      players.set(0, players.get(nPlayers-1));
      players.set(nPlayers-1,temp);
    }
  }

  /**
   * Change the current turn for the next.
   * If the current player is the last in the round
   * the next turn begin in the start
   *
   */
  private void changeToNextTurn(){
    int nPlayers = getTacticians().size();
    if(turnCurrent==nPlayers-1){
      createNewRound();
      this.turnCurrent=0;
    }
    else this.turnCurrent++;
  }

  /**
   * Finishes the current player's turn.
   */
  public void endTurn() {
    if(getTurnOwner().canPlay()){
      if(turnCurrent == getTacticians().size()-1) this.numRounds++;
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
   * @param nameTactician
   *     the player to be removed
   */
  public void removeTactician(String nameTactician) {
    players.removeIf(tactician -> tactician.getName().equals(nameTactician));
  }

  /**
   * Verify that the game not over yet
   * @return true if the not over, false otherwise
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
        this.getWinners();
        return false;
      }
      // Round number < Max rounds
      else return true;
    }
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
    return getTacticians().size()==1? true:false;
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
   * @return Players's name who won. null if there are not players in the game.
   */
  public List<String> getNamePlayers(){
    List<String> win = new ArrayList<>();
    if(getTacticians().size()==0) return null;
    if(isOnlyAPlayer()){
      win.add(getTacticians().get(0).getName());
      return win;
    }
    else{
      if(getMaxRounds()==-1) return null;
      int maxUnits = 0;
      for(Tactician t: this.getTacticians()){
        int numUnits = t.getUnits().size();
        if(numUnits>maxUnits){
          maxUnits = numUnits;
          win.clear();
          win.add(t.getName());
        }
        else if(numUnits == maxUnits){
          win.add(t.getName());
        }
      }
    }
    return win;
  }
  /**
   * @return the winner of this game, if the match ends in a draw returns a list of all the winners
   */
  public List<String> getWinners() {
    if(getMaxRounds()==-1){
      return getNamePlayers();
    }
    if(getRoundNumber()>getMaxRounds()){
      return getNamePlayers();
    }
    return null;
  }

  /**
   * @return the current player's selected unit
   */
  public IUnit getSelectedUnit() {
    return selectedUnit;
  }

  /**
   *
   * @param newUnit
   */
  public void setSelectedUnit(IUnit newUnit){
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
     else this.setSelectedUnit(null);
  }

  /**
   * @return the inventory of the currently selected unit.
   */
  public List<IEquipableItem> getItems() {
    return getSelectedUnit().getItems();
  }

  /**
   * Equips an item from the inventory to the currently selected unit.
   *
   * @param index
   *     the location of the item in the inventory.
   */
  public void equipItem(int index) {
    IEquipableItem item = getSelectedUnit().getItems().get(index);
    getSelectedUnit().setEquippedItem(item);
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
    if(enemy!=null && getSelectedUnit()!=null) {
      getSelectedUnit().attack(getGameMap().getCell(x,y).getUnit());
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

  /**
   * Getter item selected
   * @return a item selected of the unit selected
   */
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
    //si la unidad actual tiene el item equipado
    if(getGameMap().getCell(x,y).getUnit()!=null && getSelectedUnit()!=null){
      getSelectedUnit().giveItem(getGameMap().getCell(x,y).getUnit(),getSelectedItem());
    }
  }

  // Factories process

  /**
   *
   * @param unitType
   * @return
   */
  public IUnit requestUnit(UnitType unitType){
    IUnit unit = factoryUnit.makeUnit(unitType).createUnit();
    return unit;
  }

  /**
   *
   * @param unit
   */
  public void addUnitToTactician(IUnit unit){
    getTurnOwner().addUnit(unit);
  }

  /**
   * Add a Hero without items to current tactician.
   */
  public void addHeroToTactician(){
    Hero unit = new HeroFactory().createUnit();
    getTurnOwner().addUnitHero(unit);
  }

  /**
   * assign random units and items for each player in the game
   * each unit have a hero with his item
   */
  public void assignUnitRandom(){
    List<IFactoryUnit> fabUnit = factoryUnit.createUnitPack();
    int i=0, j=3;
    for(Tactician t : this.getTacticians()){
      //Collections.shuffle(fabUnit, getRandom());
      List<IFactoryUnit> subFabUnit = fabUnit.subList(i,j);
      for (int index = 0; index < 3; index++) {
        subFabUnit.get(index).addItemForDefault();
        t.addUnit(subFabUnit.get(index).createUnit());
      }
      i++; j++;
      if(j==fabUnit.size()) {
        i=0; j=3;
      }
    }
  }


  /**
   * Put anything unit in the map
   * @param unit that will be added
   * @param x axis horizontal in the map
   * @param y axis vertical in the map
   */
  public void putUnitInMap(IUnit unit, int x, int y){
    if(getGameMap().getCell(x,y).getUnit()==null){
      unit.setLocation(getGameMap().getCell(x,y));
      this.getGameMap().getCell(x,y).setUnit(unit);
    }
  }

  /**
   * Setter the position of the current player's unit
   * @param x
   * @param y
   */
  public void setPositionCurrentUnit(int x, int y){
    Location cell = getGameMap().getCell(x,y);
    this.getTurnOwner().setLocationCurrentUnit(cell);
  }

  // FACTORY UNIT

  public IFactoryUnit getSwordMasterFab(){
    return new SwordMasterFactory();
  }

  public IFactoryUnit getArcherFab(){
    return new ArcherFactory();
  }
  public IFactoryUnit getClericFab(){
    return new ClericFactory();
  }
  public IFactoryUnit getAlpacaFab(){
    return new AlpacaFactory();
  }
  public IFactoryUnit getFighterFab(){
    return new FighterFactory();
  }
  public IFactoryUnit getHeroFab(){
    return new HeroFactory();
  }
  public IFactoryUnit getSorcererFab(){
    return new SorcererFactory();
  }

  //FACTORY ITEMS

  public IFactoryItem getSwordFab(){
    return new SwordFactoryItem();
  }

  public IFactoryItem getBowFab(){
    return new BowFactoryItem();
  }

  public IFactoryItem getAxeFab(){
    return new AxeFactoryItem();
  }

  public IFactoryItem getDarknessFab(){
    return new DarknessFactoryItem();
  }
  public IFactoryItem getSoulFab(){
    return new SoulFactoryItem();
  }
  public IFactoryItem getLightFab(){
    return new LightFactoryItem();
  }
  public IFactoryItem getStaffFab(){
    return new StaffFactoryItem();
  }
  public IFactoryItem getSpearFab(){
    return new SpearFactoryItem();
  }



}
