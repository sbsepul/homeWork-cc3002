package controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    private Field location;
    private List<Boolean> liveHero;


    /**
     * Constructor to specify an alternative source of moves
     *
     * @param markName name for mark to player
     * @param map
     *
     */
    public Tactician(String markName, Field map, IUnit... unitSet){
        this.mark = markName;
        this.map = map;
        this.status = true;
        IUnit hero = new Hero(50,2, new Location(0,0));
        this.units.add(hero);
        this.liveHero.add(true);
        this.units.addAll(Arrays.asList(unitSet));
        // for default the first element in units will be the first object in the list units
        this.currentUnit = units.get(0);
    }

    /**
     * Constructor normal to use with input
     *
     * @param initMark
     */
    //public Tactician(String initMark) { this(initMark,IUnit...); }

    /**
     * Special constructor to make a Player that plays a fixed
     * set of moves from a String.
     *
     * Used to define test cases.
     */
    //public Tactician(String initMark) {
    //    this(initMark);
    //}

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
