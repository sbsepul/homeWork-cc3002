package model.factoryUnit;

import model.units.Alpaca;

public class AlpacaFactory implements IFactoryUnit {

    @Override
    public Alpaca create() {
        return new Alpaca(50, 2, null);
    }
}
