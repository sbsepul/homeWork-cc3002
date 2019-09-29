package controller;

import model.map.Field;
import model.map.Location;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.List;

public class TacticianTest {
    private Tactician tactician;
    private Field mapTactician;
    private Field map;

    @BeforeEach
    public void setUp(){
        int mapSize = 9<<2;
        List<Location> locations = new ArrayList<>();
        int n = (int) Math.floor(Math.sqrt(mapSize));
        for(int i = 0; i < n; i++){
            for(int j = 0; i < n; i++){
                locations.add(new Location(i,j));
            }
        }
        for(Location cell:locations) {
            map.addCells(true, cell);
        }
        tactician = new Tactician("Player0", map);
    }
}
