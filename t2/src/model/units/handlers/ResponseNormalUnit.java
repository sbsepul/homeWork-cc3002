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

import controller.Tactician;
import model.units.IUnit;
import model.units.NormalUnit;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;


/**
 * Listener of the normal unit's status in the player's inventory
 *
 * @author Sebastian Sepulveda
 * @version 1.0
 * @since 2.0
 *
 */
public class ResponseNormalUnit implements IResponseToTactician {
    private Tactician player;

    /**
     * @param tactician is the player in the game.
     *             This listener observe to a normal unit
     *             if a normal unit die, this unit is remove of the game
     */
    public ResponseNormalUnit(Tactician tactician){
        this.player = tactician;
    }

    /**
     * This method gets called when a bound property is changed.
     *
     * @param evt A PropertyChangeEvent object describing the event source
     *            and the property that has changed.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        //System.out.println("HP change of: " +  evt.getOldValue() + " to: " + evt.getNewValue());
        if((double) evt.getNewValue() <= 0) player.removeUnit((NormalUnit) evt.getSource());
    }

    @Override
    public Tactician getResponse() {
        return player;
    }
}
