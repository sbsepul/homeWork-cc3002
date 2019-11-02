/*
 * The MIT License
 *
 * Copyright (c) 2019 Google, Inc. http://angularjs.org
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package model.units.factoryUnit;

import java.util.List;

/**
 * This class can create the factory units define in UnitType
 *
 * @author Sebastian Sepulveda
 * @version 2.0
 * @since 2.0
 */
public class FactoryProviderUnit {

    public IFactoryUnit makeUnit(UnitType unitType){
        switch (unitType){
            case HERO:
                return new HeroFactory();
            case ALPACA:
                return new AlpacaFactory();
            case SWORDMASTER:
                return new SwordMasterFactory();
            case ARCHER:
                return new ArcherFactory();
            case CLERIC:
                return new ClericFactory();
            case FIGHTER:
                return new FighterFactory();
            case SORCERER:
                return new SorcererFactory();
            default:
                throw new IllegalArgumentException("Unit not supported");
        }
    }

    public List<IFactoryUnit> createUnitPack(){
        //factoryUnit.makeUnit(UnitType.HERO)
        return List.of(
                this.makeUnit(UnitType.ALPACA),
                this.makeUnit(UnitType.ARCHER),
                this.makeUnit(UnitType.CLERIC),
                this.makeUnit(UnitType.FIGHTER),
                this.makeUnit(UnitType.SORCERER),
                this.makeUnit(UnitType.SWORDMASTER)
        );
    }
}

