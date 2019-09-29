package controller;

import model.map.Field;
import model.map.Location;
import org.junit.jupiter.api.BeforeEach;

import javax.swing.table.TableCellRenderer;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class TacticianTest {
    private Tactician tactician;
    private Field mapTactician;
    private Field map;
    private GameController controller;

    @BeforeEach
    public void setUp(){
        controller = new GameController(2,9);
        int mapSize = controller.getGameMap().getSize();
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
        for(int i=0; i<3; i++){
            for(int j = 0; j<3; j++){
                Location l = map.getCell(i,j);
                mapTactician.addCells(true, l);
            }
        }
        tactician = new Tactician("Player0", mapTactician);
    }
}
