package model.units;

import model.items.IEquipableItem;
import model.items.magic.Darkness;
import model.items.magic.Light;
import model.items.magic.Soul;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Test set for the Sorcerer unit
 *
 * @author Sebastian Sepulveda
 * @since 1.0
 */
public class SorcererTest extends AbstractTestUnit {
    private Sorcerer sorcerer;
    private Light light_p;
    private Darkness darkness_p;
    private Soul soul_p;

    @Override
    public void setTestUnit() {
        sorcerer = new Sorcerer(50, 2, field.getCell(0, 0));
        light_p = new Light("Light_private", 20, 1,2);
        darkness_p = new Darkness("Darkness_private", 20,1,2);
        soul_p = new Soul("Soul_private", 20, 1,2);
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
    public void weaknessAttackTest() {
        // Light is weak to Soul
        checkWeaknessAttack(getTargetSorcerer(),getLight(), getSoul());
        // Darkness is weak to Light
        checkWeaknessAttack(getTargetSorcerer(),getDarkness(), getLight());
        // Soul is weak to Darkness
        checkWeaknessAttack(getTargetSorcerer(),getSoul(), getDarkness());
    }

    @Test
    @Override
    public void resistantAttackTest() {
        // Light is resistant to Soul
        checkResistantAttack(getTargetSorcerer(),getLight(), getDarkness());
        // Darkness is resistant to Light
        checkResistantAttack(getTargetSorcerer(),getDarkness(), getSoul());
        // Soul is resistant to Darkness
        checkResistantAttack(getTargetSorcerer(),getSoul(), getLight());
    }

    @Test
    @Override
    public void sameTypeUnitAttackTest() {
        checkSameTypeUnitAttack(getTargetSorcerer(),darkness_p,getDarkness());
        checkSameTypeUnitAttack(getTargetSorcerer(),light_p,getLight());
        checkSameTypeUnitAttack(getTargetSorcerer(),soul_p,getSoul());
    }

}
