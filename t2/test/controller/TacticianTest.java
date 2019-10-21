package controller;

import model.map.Field;
import model.units.IUnit;
import model.units.factoryUnit.IFactoryUnit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
    public void constructorTactician(){
        String name = "Player 0";
        assertEquals(name, tactician.getName());
        assertEquals(0,tactician.getUnits().size());
        assertEquals(0,tactician.getTacticianStatus().getPropertyChangeListeners().length);
    }

    @Test
    public void getCurrentUnit(){
        IUnit unit = controller.getArcherFab().createUnit();
        assertNull(tactician.getCurrentUnit());
        tactician.setCurrentUnit(unit);
        assertNull(tactician.getCurrentUnit());
        tactician.addUnitInventory(unit);
        assertEquals(1,tactician.getUnits().size());
        tactician.setCurrentUnit(unit);
        assertEquals(unit.getClass(),tactician.getCurrentUnit().getClass());
    }
    @Test
    public void removeUnit(){
        IFactoryUnit fab = controller.getArcherFab();
        tactician.addUnitInventory(fab.createUnit());
        assertEquals(1,tactician.getUnits().size());
        tactician.addUnitInventory(fab.createUnit());

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
