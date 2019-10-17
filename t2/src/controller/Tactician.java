package controller;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.net.http.WebSocket;
import java.util.*;

import model.items.IEquipableItem;
import model.units.Hero;
import model.units.IUnit;

/**
 * Un Tactician debe ser capaz de conocer todas las unidades que posee, y tener conocimiento del mapa del
 * juego. Dentro de su turno un jugador puede mover a todas sus unidades, pero una sola vez. Para facilitar
 * la implementación el jugador debe mantener una referencia a la unidad actualmente seleccionada.
 *
 * Dado a que tactician debe ocupar cosas de IEquipable y de IUnit, se deberia ocupar el patron de diseño
 * de
 *
 * @author Sebastian Sepulveda
 * @version 2.0
 * @since 2.0
 *
 */


public class Tactician {

    private PropertyChangeSupport support;
    private boolean status;
    private final String mark;
    private List<IUnit> units = new ArrayList<>();
    private IUnit currentUnit;
    private Map<Integer,Boolean> liveHero = new HashMap<>();

    /**
     * Constructor to specify an alternative source of moves
     *
     * @param markName name for mark to player
     * @param unitSet Tactician's firsts units in the start game.
     *
     */
    public Tactician(String markName, IUnit... unitSet){
        this.mark = markName;
        this.status = true;
        this.units.addAll(Arrays.asList(unitSet));
        this.support = new PropertyChangeSupport(this);
        // for default the first element in units will be the first object in the list units
        if(!this.units.isEmpty()){
            this.currentUnit = units.get(0);
        }
    }
    //Observer for Tactician

    public PropertyChangeSupport getPropertyChangeSupport(){
        return this.support;
    }

    public void addObserver(PropertyChangeListener pcl){
        support.addPropertyChangeListener(pcl);
    }

    public void removePropertyChangeListener(PropertyChangeListener pcl){
        support.removePropertyChangeListener(pcl);
    }

    /**
     * @return Tactician's name
     */
    public String getName() {
        return mark;
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
     * @return List of Heros live, null if Tactician don't have Heros
     */
    public Map<Integer, Boolean> getLiveHero() {
        return liveHero;
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
     * @param unitAdded added with hp full
     */
    public void addUnit(IUnit unitAdded){
        units.add(unitAdded);
    }

    /**
     * Add a hero to the inventory of units
     * @param unitHero added
     */
    public void addUnitHero(Hero unitHero){
        units.add(unitHero);
    }

    /**
     *
     * @return true if a player can play, otherwise false
     */
    public boolean canPlay() {
        if(this.getLiveHero().size()==0){
            if(this.getStatus()){
                if(this.isDieAllUnit()){
                    return false;
                }
                return this.getStatus();
            }
            return this.getStatus();
        }
        else{
            if(liveHero.containsValue(false)){
                return false;
            }
            else{
                return this.getStatus();
            }
        }
    }

    /**
     *
     * @return
     */
    public boolean isDieAllUnit() {
        if(this.getUnits().isEmpty()) return true;
        for(int i = 0; i < this.getUnits().size(); i++){
            if(this.getUnits().get(i).getCurrentHitPoints()>0){
                return false;
            }
        }
        return true;
    }

    public String getNameCurrentUnit(){ return getCurrentUnit().getEquippedItem().getName();}

    /**
     * @return getter the item equipped in the current unit of tactician
     */
    public IEquipableItem getEquipItemCurrentUnit(){
        return currentUnit.getEquippedItem();
    }

    /**
     * @return getter the item's inventory of the tactician
     */
    public List<IEquipableItem> getItemsCurrentUnit(){
        return currentUnit.getItems();
    }

    /**
     * @return the current HP of the unit
     */
    public double hitPointsCurrentUnit(){ return getCurrentUnit().getCurrentHitPoints(); }

    /**
     * @return the maximum HP of the unit
     */
    public double maxHitPointsCurrenUnit(){ return getCurrentUnit().getMaxCurrentHitPoints(); }

    /**
     * @return get the reference to the current unit of tactician
     */
    public IUnit getCurrentUnit() {
        return currentUnit;
    }

    /**
     * @param unit that will change to current unit
     */
    public void setCurrentUnit(IUnit unit) {
        this.currentUnit = unit;
    }

    /**
     * @param index select a unit of inventory's unit
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
        return this.status;
    }

    /**
     * Set the Status of the tactician to false (retired)
     */
    public void setStatus(boolean newStatus) {
        support.firePropertyChange("status", this.getPropertyChangeSupport(), newStatus);
        this.status = newStatus;
    }

    /**
     *
     * @param item
     */
    public void setEquipItem(IEquipableItem item) {
       this.currentUnit.changeEquippedItem(item);
    }
}
