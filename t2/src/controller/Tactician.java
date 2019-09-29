package controller;

import java.util.*;

import model.items.IEquipableItem;
import model.map.Field;
import model.map.Location;
import model.units.Hero;
import model.units.IUnit;

/**
 * Un Tactician debe ser capaz de conocer todas las unidades que posee, y tener conocimiento del mapa del
 * juego. Dentro de su turno un jugador puede mover a todas sus unidades, pero una sola vez. Para facilitar
 * la implementación el jugador debe mantener una referencia a la unidad actualmente seleccionada.
 *
 * @author Sebastian Sepulveda
 * @version 2.0
 * @since 2.0
 *
 */


public class Tactician {
    private final String mark;
    private final List<IUnit> units = new ArrayList<>();
    private IUnit currentUnit;
    private boolean status;
    private final Field map;
    private Field ownMap;
    private Field location;
    private Map<Integer,Boolean> liveHero = new HashMap<>();



    /**
     * Constructor to specify an alternative source of moves
     *
     * @param markName name for mark to player
     * @param map reference to map in the game
     * @param unitSet Tactician's firsts units in the start game.
     *
     */
    public Tactician(String markName, Field map, IUnit... unitSet){
        this.mark = markName;
        this.map = map;
        this.ownMap = map;
        this.status = true;
        this.units.addAll(Arrays.asList(unitSet));
        // for default the first element in units will be the first object in the list units
        this.currentUnit = units.get(0);
    }

    /**
     * Constructor to specify range of the map that can use the Tactician
     */
    public Tactician(String markName, Field map,List<Integer> rangeX, List<Integer> rangeY, IUnit... unitSet){
        this(markName,map, unitSet);
        if(rangeX.get(0)!=-1 && rangeY.get(0)!=-1){
            for(int i = rangeX.get(0); i< rangeX.get(1); i++){
                for(int j = rangeY.get(0); j< rangeY.get(1); j++){
                    ownMap.addCells(true, map.getCell(i,j));
                }
            }
        }
    }

    /**
     * @return Tactician's name
     */
    public String getName() {
        return mark;
    }

    /**
     * Remove the unit which is died
     * @param unitDeleted
     */
    public void removeUnit(IUnit unitDeleted){
        units.remove(unitDeleted);
    }

    /**
     * Added a unit for a player
     * @param unitAdded
     */
    public void addUnit(IUnit unitAdded){
        if(unitAdded instanceof Hero){
            int pos = this.units.size()-1;
            this.liveHero.put(pos,true);
        }
        units.add(unitAdded);
    }

    /**
     *
     * @return all the player's units
     */
    public List<IUnit> getUnits(){
        return units;
    }

    /**
     *
     * @return true if a player can play, otherwise false
     */
    public boolean canPlay() {
        return true;
    }


    /**
     *
     * @param item
     */
    public void equippedCurrentUnit(IEquipableItem item){
        currentUnit.equipItem(item);
    }

    /**
     *
     * @return
     */
    public IEquipableItem getEquipItemCurrentUnit(){
        return currentUnit.getEquippedItem();
    }

    /**
     *
     * @return
     */
    public List<IEquipableItem> getItemsCurrentUnit(){
        return currentUnit.getItems();
    }


    /**
     *
     * @return
     */
    public IUnit getCurrentUnit() {
        return currentUnit;
    }

    /**
     *
     * @param unit
     */
    public void setCurrentUnit(IUnit unit) {
        this.currentUnit = unit;
    }

    /**
     *
     * @param index
     */
    public void selectUnit(int index){
        currentUnit = units.get(index);
    }


    // STATUS PLAYER
    /**
     *
     * @param decision
     * @return the tactician's decision
     */
    public boolean followPlaying(boolean decision){
        return decision;
    }

    /**
     *
     * @return
     */
    public boolean getStatus(){
        return status;
    }

    /**
     *
     */
    public void retirePlayer(){
        status = false;
    }
}
