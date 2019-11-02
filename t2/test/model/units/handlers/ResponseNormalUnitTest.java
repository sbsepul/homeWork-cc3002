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

package model.units.handlers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ResponseNormalUnitTest extends AbstractResponse{
    private ResponseNormalUnit responseNormalUnit;

    @Override
    public void setResponse() {
        responseNormalUnit = new ResponseNormalUnit(tacticianTest);
    }

    @Override
    public IResponseToTactician getListener() {
        return responseNormalUnit;
    }

    @Test
    public void attackToUnit(){
        assertEquals(2, getTacticianTest().getUnits().size());
        assertEquals(2, getTacticianTarget().getUnits().size());

        getTacticianTest().setCurrentUnit(getTacticianTest().getUnits().get(0));
        getTacticianTest().generateAttack(getTacticianTarget().getUnits().get(0));
        assertEquals(40, getTacticianTest().getCurrentUnit().getCurrentHitPoints());
        assertEquals(40, getTacticianTarget().getUnits().get(0).getCurrentHitPoints());

        assertEquals(2, getTacticianTest().getUnits().size());
        //30
        getTacticianTest().generateAttack(getTacticianTarget().getUnits().get(0));
        assertEquals(30, getTacticianTest().getCurrentUnit().getCurrentHitPoints());
        assertEquals(30, getTacticianTarget().getUnits().get(0).getCurrentHitPoints());

        //attack to unit own
        getTacticianTest().generateAttack(getTacticianTest().getUnits().get(1));
        assertEquals(30, getTacticianTest().getCurrentUnit().getCurrentHitPoints());
        assertEquals(50, getTacticianTest().getUnits().get(1).getCurrentHitPoints());
        //20
        getTacticianTest().generateAttack(getTacticianTarget().getUnits().get(0));
        assertEquals(20, getTacticianTest().getCurrentUnit().getCurrentHitPoints());
        assertEquals(20, getTacticianTarget().getUnits().get(0).getCurrentHitPoints());
        //10
        getTacticianTest().generateAttack(getTacticianTarget().getUnits().get(0));
        assertEquals(10, getTacticianTest().getCurrentUnit().getCurrentHitPoints());
        assertEquals(10, getTacticianTarget().getUnits().get(0).getCurrentHitPoints());

        //0
        getTacticianTest().generateAttack(getTacticianTarget().getUnits().get(0));
        assertEquals(10, getTacticianTest().getCurrentUnit().getCurrentHitPoints());
        assertEquals(2, getTacticianTest().getUnits().size());
        assertEquals(1, getTacticianTarget().getUnits().size());
        assertEquals(true, getTacticianTarget().getStatus());
    }

    @Test
    public void healingUnit(){
        assertEquals(2, getTacticianTest().getUnits().size());
        assertEquals(2, getTacticianTarget().getUnits().size());

        getTacticianTest().addUnitInventory(getCleric());

        assertEquals(getTacticianTest(),getCleric().getTactician());

        assertEquals(3, getTacticianTest().getUnits().size());
        assertEquals(2, getTacticianTarget().getUnits().size());

        getTacticianTest().getUnits().forEach(i -> assertEquals(50, i.getCurrentHitPoints()));

        getTacticianTest().setCurrentUnit(getSpecialUnitTest());
        getTacticianTest().generateAttack(getSpecialUnitTarget());
        assertEquals(40, getSpecialUnitTest().getCurrentHitPoints());
        assertEquals(40, getSpecialUnitTarget().getCurrentHitPoints());

        getTacticianTest().setCurrentUnit(getCleric());
        assertTrue(getTacticianTest().getCurrentUnit().isInRange(getSpecialUnitTarget()));
        getTacticianTest().generateAttack(getSpecialUnitTarget());
        assertEquals(40, getSpecialUnitTarget().getCurrentHitPoints());
        assertEquals(50, getCleric().getCurrentHitPoints());

        assertTrue(getTacticianTest().getCurrentUnit().isInRange(getSpecialUnitTest()));
        getTacticianTest().generateAttack(getSpecialUnitTest());
        assertEquals(50, getSpecialUnitTest().getCurrentHitPoints());
        assertEquals(50, getCleric().getCurrentHitPoints());
    }

}