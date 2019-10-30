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

import static org.junit.jupiter.api.Assertions.*;

class ResponseSpecialUnitTest extends AbstractResponse {
    ResponseSpecialUnit responseSpecialUnit;

    @Override
    public void setResponse() {
        responseSpecialUnit = new ResponseSpecialUnit(tacticianTest);
    }

    @Override
    public IResponseToTactician getListener() {
        return responseSpecialUnit;
    }

    @Test
    public void attackToUnit(){
        assertEquals(2, getTacticianTest().getUnits().size());
        assertEquals(2, getTacticianTarget().getUnits().size());

        getTacticianTest().setCurrentUnit(getTacticianTest().getUnits().get(1));
        getTacticianTest().generateAttack(getTacticianTarget().getUnits().get(1));
        assertEquals(40, getTacticianTest().getCurrentUnit().getCurrentHitPoints());
        assertEquals(40, getTacticianTarget().getUnits().get(1).getCurrentHitPoints());

        assertEquals(2, getTacticianTest().getUnits().size());
        //30
        getTacticianTest().generateAttack(getTacticianTarget().getUnits().get(1));
        assertEquals(30, getTacticianTest().getCurrentUnit().getCurrentHitPoints());
        assertEquals(30, getTacticianTarget().getUnits().get(1).getCurrentHitPoints());
        //20
        getTacticianTest().generateAttack(getTacticianTarget().getUnits().get(1));
        assertEquals(20, getTacticianTest().getCurrentUnit().getCurrentHitPoints());
        assertEquals(20, getTacticianTarget().getUnits().get(1).getCurrentHitPoints());
        //10
        getTacticianTest().generateAttack(getTacticianTarget().getUnits().get(1));
        assertEquals(10, getTacticianTest().getCurrentUnit().getCurrentHitPoints());
        assertEquals(10, getTacticianTarget().getUnits().get(1).getCurrentHitPoints());

        //0
        getTacticianTest().generateAttack(getTacticianTarget().getUnits().get(1));
        assertEquals(10, getTacticianTest().getCurrentUnit().getCurrentHitPoints());
        assertEquals(2, getTacticianTest().getUnits().size());
        assertEquals(1, getTacticianTarget().getUnits().size());
        assertEquals(false, getTacticianTarget().getStatus());
    }
}