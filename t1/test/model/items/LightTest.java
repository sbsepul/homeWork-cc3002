package model.items;

import model.items.magic.Light;
import model.map.Location;
import model.units.IUnit;
import model.units.Sorcerer;

/**
 * Test set for Light
 *
 * @author Sebastian Sepulveda
 * @since 1.0
 */

public class LightTest extends AbstractTestItem {
    private Light light;
    private Light wrongLight;
    private Sorcerer sorcerer;

    @Override
    public void setTestItem() {
        expectedName = "Common darkness";
        expectedPower = 10;
        expectedMinRange = 1;
        expectedMaxRange = 2;
        light = new Light(expectedName, expectedPower, expectedMinRange, expectedMaxRange);
    }

    @Override
    public void setWrongRangeItem() {
        wrongLight = new Light("WrongDarkness", 0, -1,-2);
    }

    @Override
    public void setTestUnit() {
        sorcerer = new Sorcerer(50,4,new Location(0,0));
    }

    @Override
    public IEquipableItem getWrongTestItem() {
        return wrongLight;
    }

    @Override
    public IEquipableItem getTestItem() {
        return light;
    }

    @Override
    public IUnit getTestUnit() {
        return sorcerer;
    }
}
