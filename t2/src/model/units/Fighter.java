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

package model.units;

import model.items.Axe;
import model.items.IEquipableItem;
import model.map.Location;

/**
 * This class represents a fighter type unit.
 * A fighter is a unit that can only use axe type weapons.
 *
 * @author Sebastian Sepulveda
 * @since 1.0
 */
public class Fighter extends AbstractNormalUnit {

  /**
   * Create a Fighter with HP, movement, location and items
   *
   * @param hitPoints
   * @param movement
   * @param location
   * @param items
   */
  public Fighter(final int hitPoints, final int movement, final Location location,
      IEquipableItem... items) {
    super(hitPoints, movement, location, 3, items);
  }
  @Override
  public void equipItemAxe(Axe item) { equippedItem = item;  }
  @Override
  public void attack(IUnit enemy) {
    if (this.initCombat(enemy)){
      if(enemy.canCounterAttack(getEquippedItem().getDistance())){
        enemy.getEquippedItem().receiveAxeAttack((Axe) this.getEquippedItem());
      }
      else enemy.receiveAttack(this.getEquippedItem());
    }
  }
}
