package model.items;

import model.items.magic.Soul;
import model.map.Location;
import model.units.IUnit;
import model.units.Sorcerer;

/**
 * Test set for Soul
 *
 * @author Sebastian Sepulveda
 * @since 1.0
 */

public class SoulTest extends AbstractTestItem {
    private Soul soul;
    private Soul wrongSoul;
    private Sorcerer sorcerer;

    @Override
    public void setTestItem() {
        expectedName = "Common darkness";
        expectedPower = 10;
        expectedMinRange = 1;
        expectedMaxRange = 2;
        soul = new Soul(expectedName, expectedPower, expectedMinRange, expectedMaxRange);
    }

    @Override
    public void setWrongRangeItem() {
        wrongSoul = new Soul("WrongDarkness", 0, -1,-2);
    }

    @Override
    public void setTestUnit() {
        sorcerer = new Sorcerer(50,4,new Location(0,0));
    }

    @Override
    public IEquipableItem getWrongTestItem() {
        return wrongSoul;
    }

    @Override
    public IEquipableItem getTestItem() {
        return soul;
    }

    @Override
    public IUnit getTestUnit() {
        return sorcerer;
    }
}
