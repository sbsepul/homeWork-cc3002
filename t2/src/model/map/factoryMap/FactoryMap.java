package model.map.factoryMap;

import model.map.Field;
import model.map.Location;

import java.util.ArrayList;
import java.util.List;

public class FactoryMap implements IFactoryMap {
    private int tamMap;

    /**
     * Constructor of Factory Map, create a instance of Field with dimensions
     * of size x size, square for default
     *
     * @param size of the Map
     */
    public FactoryMap(int size){
        this.tamMap = size;
    }

    @Override
    public Field createMap() {
        Field map = new Field();
        List<Location> locations = new ArrayList<>();
        int n = this.tamMap;
        for(int i = 0; i < n; i++){
            for(int j = 0; i < n; i++){
                locations.add(new Location(i,j));
            }
        }
        for(Location cell:locations) {
            map.addCells(true, cell);
        }
        return map;
    }
}
