package controller;

import model.map.Field;
import model.map.Location;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        tactician = new Tactician("Player 0");
    }

    @Test
    public void getCurrentUnit(){

    }
    @Test
    public void removeUnit(){

    }
    @Test
    public void addUnit(){

    }

    @Test
    public void getUnits(){

    }
    @Test
    public void canPlay(){

    }


}
