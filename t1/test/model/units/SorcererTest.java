package model.units;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class SorcererTest extends AbstractTestUnit {
    private Sorcerer sorcererTest;


    @Override
    public void setTestUnit() {
        sorcererTest = new Sorcerer(50, 2, field.getCell(0, 0));
    }

    @Override
    public IUnit getTestUnit() {
        return null;
    }

    @Override
    public void testCombat() {

    }
}
