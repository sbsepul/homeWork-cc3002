package controller;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.*;

import model.items.IEquipableItem;
import model.map.Location;
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

    private PropertyChangeSupport tacticianStatus;
    private PropertyChangeSupport unitStatus;
    private boolean status;
    private final String mark;
    private List<IUnit> units = new ArrayList<>();
    private List<Hero> heroes;
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
        this.tacticianStatus = new PropertyChangeSupport(this);
    }
    //Observer for Tactician

    public PropertyChangeSupport getTacticianStatus(){
        return this.tacticianStatus;
    }

    public void addResponseStatusTactician(PropertyChangeListener pcl){
        tacticianStatus.addPropertyChangeListener(pcl);
    }

    public void removePropertyChangeListener(PropertyChangeListener pcl){
        tacticianStatus.removePropertyChangeListener(pcl);
    }

    /**
     * @return Tactician's name
     */
    public String getName() {
        return mark;
    }

    /**
     * @return all the player's units
     */
    public List<IUnit> getUnits(){
        return units;
    }

    /**
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
        if(units.contains(unitDeleted)) units.remove(unitDeleted);
    }

    /**
     * Added a unit to the inventory of unit from player
     * @param unitAdded added with hp full
     */
    public void addUnitInventory(IUnit unitAdded){
        units.add(unitAdded);
    }

    /**
     * Add a hero to the inventory of units from player
     * @param unitHero added
     */
    public void addUnitHero(Hero unitHero) {
        units.add(unitHero);
    }


    /**
     * @return true if a player can play, otherwise false
     */
    public boolean canPlay() { return this.status; }

    /**
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
        if(getUnits().contains(unit)) this.currentUnit = unit;
    }

    /**
     * @param index select a unit of inventory's unit
     */
    public void selectUnit(int index){
        currentUnit = units.get(index);
    }

    // STATUS PLAYER

    public void retire(){
        tacticianStatus.firePropertyChange(
                new PropertyChangeEvent(this,"status", getStatus(),false)
        );
        this.status = false;
    }

    /**
     *
     * @return
     */
    public boolean getStatus() { return this.status; }

    /**
     *
     * @param item
     */
    public void setEquipItem(IEquipableItem item) {
       this.currentUnit.changeEquippedItem(item);
    }

    /**
     *
     * @param location
     */
    public void setLocationCurrentUnit(Location location){
        getCurrentUnit().setLocation(location);
    }


    /**
     *
     * @param enemy
     */
    public void generateAttack(IUnit enemy){
        double hpInit = getCurrentUnit().getCurrentHitPoints();
        getCurrentUnit().attack(enemy);
        unitStatus.firePropertyChange(
                new PropertyChangeEvent(this,
                        "statusUnit",
                        hpInit,
                        getCurrentUnit().getCurrentHitPoints())
        );
    }

    public void giveItemToUnit(IUnit target, IEquipableItem itemSelected){
        //si la unidad actual tiene el item equipado
        if(this.getCurrentUnit().getItems().contains(itemSelected)){
            //si el jugador actual contiene a target unit
            if(this.getUnits().contains(target)){
                this.getCurrentUnit().giveItem(target,itemSelected);
            }
        }
    }

}
