package controller;

import model.map.Field;
import model.units.IUnit;
import model.units.NormalUnit;
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
    }

    @Test
    public void getCurrentUnit(){
        IUnit unit = controller.getArcherFab().createUnit();
        assertNull(tactician.getCurrentUnit());
        tactician.setCurrentUnit(unit);
        assertNull(tactician.getCurrentUnit());
        tactician.addUnitInventory((NormalUnit) unit);
        assertEquals(1,tactician.getUnits().size());
        tactician.setCurrentUnit(unit);
        assertEquals(unit.getClass(),tactician.getCurrentUnit().getClass());
    }
    @Test
    public void removeUnit(){
        IFactoryUnit fab = controller.getArcherFab();
        IUnit unit1 = fab.createUnit();
        tactician.addUnitInventory((NormalUnit) unit1);
        assertEquals(1,tactician.getUnits().size());
        assertTrue(tactician.getUnits().contains(unit1));
        IUnit unit2 = fab.createUnit();
        tactician.addUnitInventory((NormalUnit) unit2);
        assertEquals(2,tactician.getUnits().size());
        assertTrue(tactician.getUnits().contains(unit2));
        tactician.removeUnit((NormalUnit) unit1);
        assertEquals(1,tactician.getUnits().size());
        assertFalse(tactician.getUnits().contains(unit1));
        assertTrue(tactician.getUnits().contains(unit2));
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

    @Test
    public void selectUnit(){
    }

}
