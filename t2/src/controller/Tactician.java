package controller;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.*;

import model.items.IEquipableItem;
import model.units.IUnit;
import model.units.NormalUnit;
import model.units.SpecialUnit;
import model.units.handlers.ResponseNormalUnit;
import model.units.handlers.ResponseSpecialUnit;

/**
 * A Tactician is the interface for the interaction between the user and the model of the game.
 * In the developer of the code, a tactician represent to a player, so it must to know
 * all the features of the units and the quantity of units that have.
 *
 * Besides, a tactician have a reference to the unit currently selected
 *
 *
 *
 *
 * @author Sebastian Sepulveda
 * @version 2.0
 * @since 2.0
 *
 */


public class Tactician {

    private boolean status;
    private final String mark;
    private List<IUnit> units = new ArrayList<>();
    private IUnit currentUnit;
    private PropertyChangeSupport
            changesNormalUnit = new PropertyChangeSupport(this),
            changesSpecialUnit = new PropertyChangeSupport(this);

    /**
     * Constructor to specify an alternative source of moves
     *
     * @param markName name for mark to player
     * @param unitSet Tactician's firsts units in the start game.
     *
     */
    public Tactician(final String markName, IUnit... unitSet){
        this.mark = markName;
        this.status = true;
        this.units.addAll(Arrays.asList(unitSet));
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
     * Remove the unit which is died
     * @param unitDeleted
     */
    public void removeUnit(NormalUnit unitDeleted){
        if(units.contains(unitDeleted)) {
            int initSize = getUnits().size();
            units.remove(unitDeleted);
            changesNormalUnit.firePropertyChange(
                    new PropertyChangeEvent(
                            this,
                            "Normal Unit deleted",
                            initSize,
                            getUnits())
            );
        }
    }


    public void removeSpecialUnit(SpecialUnit specialUnit) {
        if(units.contains(specialUnit)) {
            int initSize = getUnits().size();
            units.remove(specialUnit);
            changesSpecialUnit.firePropertyChange(
                    new PropertyChangeEvent(
                            this,
                            "Special Unit deleted",
                            initSize,
                            getUnits()
                    )
            );
        }
    }

    /**
     * Added a unit to the inventory of unit from player
     * @param unitAdded added with hp full
     */
    public void addUnitInventory(NormalUnit unitAdded) {
        ResponseNormalUnit respNormalUnit = new ResponseNormalUnit(this);
        unitAdded.addObserver(respNormalUnit);
        units.add(unitAdded);
    }

    /**
     * Add a hero to the inventory of units from player
     * @param unitHero added
     */
    public void addUnitHero(SpecialUnit unitHero) {
        ResponseSpecialUnit respSpecialUnit = new ResponseSpecialUnit(this);
        unitHero.addObserver(respSpecialUnit);
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
    public double maxHitPointsCurrentUnit(){ return getCurrentUnit().getMaxCurrentHitPoints(); }

    /**
     * @return get the reference to the current unit of tactician
     */
    public IUnit getCurrentUnit() { return currentUnit; }

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
        if(getUnits().size()>0) currentUnit = units.get(index);
    }

    // STATUS PLAYER

    /**
     * Retire a tactician of the game
     */
    public void retire(){
        this.status = false;
    }

    /**
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
     * @param enemy
     */
    public void generateAttack(IUnit enemy){
        getCurrentUnit().attack(enemy);
    }

    /**
     *
     * @param target
     * @param itemSelected
     */
    public void giveItemToUnit(IUnit target, IEquipableItem itemSelected){
        if(this.getUnits().contains(target)){
            this.getCurrentUnit().giveItem(target,itemSelected);
        }
    }

    /**
     * Add a controller like listener in the change in the
     * quantity of tactician's normal units.
     *
     * @param plc controller of the game
     */
    public void addObserverNormalUnit(PropertyChangeListener plc){
        changesNormalUnit.addPropertyChangeListener(plc);
    }

    /**
     * Add a controller like listener in the change in the
     * quantity of tactician's special units.
     *
     * @param plc controller of the game
     */
    public void addObserverSpecialUnit(PropertyChangeListener plc){
        changesSpecialUnit.addPropertyChangeListener(plc);
    }

}
