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

import controller.handler.NormalUnitLoseHandler;
import controller.handler.ResponseStatusTactician;
import controller.handler.SpecialUnitLoseHandler;
import controller.handler.UnitMovedHandler;
import model.items.factoryItem.*;
import model.map.factoryMap.FactoryMap;
import model.map.factoryMap.IFactoryMap;
import model.units.NormalUnit;
import model.units.SpecialUnit;
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
  private long seedMap = 0;
  private long maxRounds;
  private int numRounds;
  private int turnCurrent = 0;
  private final int numPlayers;
  private final int tamMap;
  private Field map;
  private List<Tactician> players;
  private List<Tactician> initPlayerStatus = new ArrayList<>();
  private IUnit selectedUnit;
  private IEquipableItem selectedItem;
  private IFactoryMap factoryMap;
  private List<IUnit> unitsMoved = new ArrayList<>();

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
    this.map = createNewMap();
    this.players = createTacticians(numberOfPlayers);
  }

  /**
   * Create a instance of Field with a Factory Map
   * @return a Field of N x N with tamMap initial
   */
  public Field createNewMap() {
    this.factoryMap = new FactoryMap(this.tamMap);
    if(seedMap!=0) this.factoryMap.setNumLong(this.seedMap);
    return factoryMap.createMap();
  }

  /**
   * Create a list of Tacticians
   *
   * @param numTacticians that begin in the game
   * @return a list of tacticians without units
   */
  public List<Tactician> createTacticians(int numTacticians){
    List<Tactician> tacticianList = new ArrayList<Tactician>(numTacticians);
    for(int i=0; i<numTacticians; i++){
      StringBuilder builder = new StringBuilder();
      builder.append("Player ");
      builder.append(i);
      Tactician pTactician = new Tactician(builder.toString());
      pTactician.addObserverNormalUnit(new NormalUnitLoseHandler(this));
      pTactician.addObserverSpecialUnit(new SpecialUnitLoseHandler(this));
      pTactician.addObserverStatus(new ResponseStatusTactician(this));
      pTactician.addObserverUnitMoved(new UnitMovedHandler(this));
      tacticianList.add(pTactician);
    }
    return tacticianList;
  }

  /**
   * Reset the game to the beginning
   */
  public void resetController(){
    // this.map = createNewMap();
    this.players = new ArrayList<>(getInitPlayerStatus());
  }

    /**
     * @return random number for create news rounds
     */
  public Random getRandom() {
    return random;
  }

    /**
     * Setter the seed of the random number of map
     * @param seedMap long number
     */
  public void setSeedMap(long seedMap) {
    this.seedMap = seedMap;
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
   * @return the index of the current player
   */
  public int getTurnCurrent() {
    return turnCurrent;
  }

  /**
   * @return list of units moved
   */
  public List<IUnit> getUnitsMoved() {
    return unitsMoved;
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
   * @return The status initial of the players
   *         The units return to their init status
   */
  public List<Tactician> getInitPlayerStatus() {
    return List.copyOf(initPlayerStatus);
  }

  /**
   * @return the number of players in the init Game
   */
  public int getInitNumPlayer(){ return numPlayers; }

  /**
   * @return the map initial with the units in the map
   */
  //public Field getInitMap() {
  //  return initMap;
  //}

  /**
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
  public void changeToNextTurn(){
    int nPlayers = getTacticians().size();
    if(turnCurrent==nPlayers-1){
      if(numRounds>1) createNewRound();
      this.turnCurrent=0;
    }
    else this.turnCurrent++;
  }

  /**
   * Finishes the current player's turn.
   */
  public void endTurn() {
    if(turnCurrent == getTacticians().size()-1) this.numRounds++;
    this.changeToNextTurn();
    this.getTacticians().get(getTurnCurrent()).removeAllMove();
    this.unitsMoved.clear();
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
    if(this.numRounds==0 && getInitNumPlayer()==getTacticians().size()) {
      this.initPlayerStatus.addAll(players);
    }
    else this.resetController();
    this.maxRounds = maxTurns;
    this.numRounds = 1;
    this.turnCurrent = 0;
    this.unitsMoved.clear();
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
    if(getRoundNumber()>getMaxRounds() || getMaxRounds()==-1){
      return getNamePlayers();
    }
    return null;
  }

    /**
     * @return the current player's unit
     */
    public IUnit getCurrentUnit() {
        return getTurnOwner().getCurrentUnit();
    }

    /**
     * @param currentUnit that it will be selected in the player's inventory
     */
    public void setCurrentUnit(IUnit currentUnit) {
        getTurnOwner().setCurrentUnit(currentUnit);
    }

    /**
    * @return the current player's selected unit
    */
  public IUnit getSelectedUnit() {
    return selectedUnit;
  }

  /**
   * @param newUnit selected in the map of the game
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
     setCurrentUnit(selectedUnit);
     this.setSelectedUnit(selectedUnit);
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
      if(getSelectedUnit().equals(getCurrentUnit())){
        getTurnOwner().generateAttack(enemy);
      }
      else getSelectedUnit().attack(getGameMap().getCell(x,y).getUnit());
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
    if(getGameMap().getCell(x,y).getUnit()!=null && getSelectedUnit()!=null){
      if(getSelectedUnit().equals(getCurrentUnit())) {
        getTurnOwner().giveItemToUnit(getGameMap().getCell(x,y).getUnit(),getSelectedItem());
      }
      else if (getSelectedUnit().getTactician()==null){
        getSelectedUnit().giveItem(getGameMap().getCell(x,y).getUnit(),getSelectedItem());
      }
      else if (getTacticians().contains(getSelectedUnit().getTactician())) {
        getSelectedUnit().getTactician().setCurrentUnit(getSelectedUnit());
        getSelectedUnit().getTactician().giveItemToUnit(getGameMap().getCell(x,y).getUnit(),getSelectedItem());
      }
    }
  }

  // Factories process

  /**
   * @param unit added to the player's inventory of units
   */
  public void addUnitToTactician(NormalUnit unit) {
    getTurnOwner().addUnitInventory(unit);
  }

  /**
   * Add a Hero without items to current tactician.
   *
   * Add a ResponseHeroes
   */
  public void addHeroToTactician(SpecialUnit hero){
    getTurnOwner().addUnitHero(hero);
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
    }
  }

  /**
   * Add a Bow to unit selected
   */
  public void addBowToSelectedUnit(){ getSelectedUnit().addItem(getBowFab().createItem()); }

  /**
   * Add a Axe to unit selected
   */
  public void addAxeToSelectedUnit(){ getSelectedUnit().addItem(getAxeFab().createItem());}

  /**
   * Add a Darkness to unit selected
   */
  public void addDarknessToSelectedUnit(){ getSelectedUnit().addItem(getDarknessFab().createItem());}

  /**
   * Add a Light to unit selected
   */
  public void addLightToSelectedUnit() { getSelectedUnit().addItem(getLightFab().createItem());}

  /**
   * Add a Soul to unit selected
   */
  public void addSoulToSelectedUnit() { getSelectedUnit().addItem(getSoulFab().createItem());}

    /**
     * Add a Staff to unit selected
     */
  public void addStaffToSelectedUnit() { getSelectedUnit().addItem(getStaffFab().createItem());}

    /**
     * Add a Spear to unit selected
     */
  public void addSpearToSelectedUnit() { getSelectedUnit().addItem(getSpearFab().createItem());}

  /**
   * Add a Sword to unit selected
   */
  public void addSwordToSelectedUnit(){ getSelectedUnit().addItem(getSwordFab().createItem());}

  /**
     * Move to position (x,y) of the GameMap
     * to the current unit selected
     *
     * @param x position of the rows in the map
     * @param y position of the column in the map
     */
  public void moveToSelectedUnit(int x, int y){
    if(!getUnitsMoved().contains(getSelectedUnit())){
      getSelectedUnit().moveTo(getGameMap().getCell(x,y));
    }
  }

  /**
   * @param unitMoved
   */
  public void addUnitMoved(IUnit unitMoved){
    this.unitsMoved.add(unitMoved);
  }


  // FACTORY UNIT

  /**
   * @return Factory from SwordMaster <b>IUnit</b>
   */
  public IFactoryUnit getSwordMasterFab(){
    return new SwordMasterFactory();
  }

  /**
   * @return Factory from Archer <b>IUnit</b>
   */
  public IFactoryUnit getArcherFab(){
    return new ArcherFactory();
  }

  /**
   * @return Factory from Cleric <b>IUnit</b>
   */
  public IFactoryUnit getClericFab(){
    return new ClericFactory();
  }

  /**
   * @return Factory from Alpaca <b>IUnit</b>
   */
  public IFactoryUnit getAlpacaFab(){
    return new AlpacaFactory();
  }

  /**
   * @return Factory from Fighter <b>IUnit</b>
   */
  public IFactoryUnit getFighterFab(){
    return new FighterFactory();
  }

  /**
   * @return Factory from Hero <b>IUnit</b>
   */
  public HeroFactory getHeroFab(){ return new HeroFactory(); }

  /**
   * @return Factory from Sorcerer <b>IUnit</b>
   */
  public IFactoryUnit getSorcererFab(){
    return new SorcererFactory();
  }

  //FACTORY ITEMS

  /**
   * @return Factory from Sword <b>IEquipableItem</b>
   */
  public IFactoryItem getSwordFab(){
    return new SwordFactoryItem();
  }

  /**
   * @return Factory from Bow <b>IEquipableItem</b>
   */
  public IFactoryItem getBowFab(){
    return new BowFactoryItem();
  }

  /**
   * @return Factory from Axe <b>IEquipableItem</b>
   */
  public IFactoryItem getAxeFab(){
    return new AxeFactoryItem();
  }

  /**
   * @return Factory from Darkness <b>IEquipableItem</b>
   */
  public IFactoryItem getDarknessFab(){
    return new DarknessFactoryItem();
  }

  /**
   * @return Factory from Soul <b>IEquipableItem</b>
   */
  public IFactoryItem getSoulFab(){
    return new SoulFactoryItem();
  }

  /**
   * @return Factory from Light <b>IEquipableItem</b>
   */
  public IFactoryItem getLightFab(){
    return new LightFactoryItem();
  }

  /**
   * @return Factory from Staff <b>IEquipableItem</b>
   */
  public IFactoryItem getStaffFab(){
    return new StaffFactoryItem();
  }

  /**
   * @return Factory from Spear <b>IEquipableItem</b>
   */
  public IFactoryItem getSpearFab(){
    return new SpearFactoryItem();
  }



}
