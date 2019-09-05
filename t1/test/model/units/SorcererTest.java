package model.units;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class SorcererTest extends AbstractTestUnit {
    private Sorcerer sorcerer;


    @Override
    public void setTestUnit() {
        sorcerer = new Sorcerer(50, 2, field.getCell(0, 0));
    }

    @Override
    public IUnit getTestUnit() {
        return sorcerer;
    }

    @Test
    public void equipDarknessTest() {
        assertNull(sorcerer.getEquippedItem());
        sorcerer.equipItem(darkness);
        assertNull(sorcerer.getEquippedItem());
    }

    @Test
    public void equipLightTest() {
        assertNull(sorcerer.getEquippedItem());
        sorcerer.equipItem(light);
        assertNull(sorcerer.getEquippedItem());
    }

    @Test
    public void equipSoulTest() {
        assertNull(sorcerer.getEquippedItem());
        sorcerer.equipItem(soul);
        assertNull(sorcerer.getEquippedItem());
    }


    @Override
    public void testCombat() {

    }
}
