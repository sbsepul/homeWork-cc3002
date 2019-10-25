package model.units;

import model.units.handlers.ResponseNormalUnit;

public interface NormalUnit extends IUnit {
    /**
     *
     * @param plc
     */
    void addObserver(ResponseNormalUnit plc);
}
