package model.items;

import model.items.magic.Darkness;
import model.map.Location;
import model.units.IUnit;
import model.units.Sorcerer;


/**
 * Test set for darkness
 *
 * @author Sebastian Sepulveda
 * @since 1.0
 */

public class DarknessTest extends AbstractTestItem {
    private Darkness darkness;
    private Darkness wrongDarkness;
    private Sorcerer sorcerer;

    @Override
    public void setTestItem() {
        expectedName = "Common darkness";
        expectedPower = 10;
        expectedMinRange = 1;
        expectedMaxRange = 2;
        darkness = new Darkness(expectedName, expectedPower, expectedMinRange, expectedMaxRange);
    }

    @Override
    public void setWrongRangeItem() {
        wrongDarkness = new Darkness("WrongDarkness", 0, -1,-2);
    }

    @Override
    public void setTestUnit() {
        sorcerer = new Sorcerer(50,4,new Location(0,0));
    }

    @Override
    public IEquipableItem getWrongTestItem() {
        return wrongDarkness;
    }

    @Override
    public IEquipableItem getTestItem() {
        return darkness;
    }

    @Override
    public IUnit getTestUnit() {
        return sorcerer;
    }


}
