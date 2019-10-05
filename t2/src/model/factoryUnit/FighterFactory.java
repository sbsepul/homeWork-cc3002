package model.factoryUnit;

import model.units.Fighter;
import model.units.IUnit;

public class FighterFactory implements IFactoryUnit {
    @Override
    public Fighter create() {
        return new Fighter(50,2,null);
    }
}
