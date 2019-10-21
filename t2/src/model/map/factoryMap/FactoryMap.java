package model.map.factoryMap;

import model.map.Field;
import model.map.Location;

import java.util.ArrayList;
import java.util.List;

public class FactoryMap implements IFactoryMap {
    private int tamMap;
    private long numLong;

    /**
     * Constructor of Factory Map, create a instance of Field with dimensions
     * of size x size, square for default
     *
     * @param size of the Map
     */
    public FactoryMap(int size){
        this.tamMap = size;
        numLong = 0;
    }

    @Override
    public Field createMap() {
        Field map = new Field();
        if(numLong!=0) map.setSeed(getLong());
        List<Location> locations = new ArrayList<>();
        int n = this.tamMap;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++) {
                locations.add(new Location(i, j));
            }
        }
        for(Location loc: locations){
            map.addCells(false,loc);
        }
        //Location[] loc = (Location[]) locations.toArray();
        //map.addCells(false, loc);
        return map;
    }

    @Override
    public void setNumLong(long numLong) {
        this.numLong = numLong;
    }

    @Override
    public long getLong(){
        return this.numLong;
    }
}
