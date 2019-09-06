package model.units;

import model.items.IEquipableItem;
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
    @Override
    public void equipDarknessTest() {
        assertNull(sorcerer.getEquippedItem());
        sorcerer.equipItem(darkness);
        assertNull(sorcerer.getEquippedItem());
        sorcerer.addItem(darkness);
        sorcerer.equipItem(darkness);
        assertEquals(darkness, sorcerer.getEquippedItem());
        assertEquals(1, sorcerer.getItems().size());
        sorcerer.addItem(darkness);
        assertEquals(2 , sorcerer.getItems().size());
        sorcerer.equipItem(axe);
        assertEquals(darkness, sorcerer.getEquippedItem());
        sorcerer.addItem(sword);
        assertEquals(3 , sorcerer.getItems().size());
    }

    @Test
    @Override
    public void equipLightTest() {
        assertNull(sorcerer.getEquippedItem());
        sorcerer.equipItem(light);
        assertNull(sorcerer.getEquippedItem());
        sorcerer.addItem(light);
        sorcerer.equipItem(light);
        assertEquals(light, sorcerer.getEquippedItem());
        assertEquals(1, sorcerer.getItems().size());
        sorcerer.addItem(light);
        assertEquals(2 , sorcerer.getItems().size());
        sorcerer.equipItem(axe);
        assertEquals(light, sorcerer.getEquippedItem());
        sorcerer.addItem(sword);
        assertEquals(3 , sorcerer.getItems().size());
    }

    @Test
    @Override
    public void equipSoulTest() {
        assertNull(sorcerer.getEquippedItem());
        sorcerer.equipItem(soul);
        assertNull(sorcerer.getEquippedItem());
        sorcerer.addItem(soul);
        sorcerer.equipItem(soul);
        assertEquals(soul, sorcerer.getEquippedItem());
        assertEquals(1, sorcerer.getItems().size());
        sorcerer.addItem(soul);
        assertEquals(2 , sorcerer.getItems().size());
        sorcerer.equipItem(axe);
        assertEquals(soul, sorcerer.getEquippedItem());
        sorcerer.addItem(sword);
        assertEquals(3 , sorcerer.getItems().size());
    }


    @Override
    public void testCombat() {

    }

    @Test
    @Override
    public void weaknessAttack() {
        // Light is weak to Soul
        checkWeaknessAttackTest(getTargetSorcerer(),getLight(), getSoul());
        // Darkness is weak to Light
        checkWeaknessAttackTest(getTargetSorcerer(),getDarkness(), getLight());
        // Soul is weak to Darkness
        checkWeaknessAttackTest(getTargetSorcerer(),getSoul(), getDarkness());
    }

    @Override
    public void resistantAttack() {

    }

}
