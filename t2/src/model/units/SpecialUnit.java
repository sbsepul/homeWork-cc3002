package model.units;

import model.units.handlers.ResponseSpecialUnit;

public interface SpecialUnit extends IUnit {
    /**
     *
     * @param plc
     */
    void addObserver(ResponseSpecialUnit plc);
}
