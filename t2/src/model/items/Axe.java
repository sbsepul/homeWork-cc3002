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

package model.items;

import model.items.magic.Darkness;
import model.items.magic.Light;
import model.items.magic.Soul;
import model.units.IUnit;
import org.jetbrains.annotations.NotNull;

/**
 * This class represents an Axe.
 * <p>
 * Axes are strong against spears but weak against swords.
 *
 * @author Sebastian Sepulveda
 * @since 1.0
 */
public class Axe extends AbstractAttack {

  /**
   * Creates a new Axe item
   *
   * @param name
   *     the name of the Axe
   * @param power
   *     the damage of the axe
   * @param minRange
   *     the minimum range of the axe
   * @param maxRange
   *     the maximum range of the axe
   */
  public Axe(final String name, final int power, final int minRange, final int maxRange) {
    super(name, power, minRange, maxRange);
  }

  @Override
    public void equipTo(@NotNull IUnit unit) {
      unit.equipItemAxe(this);
      this.setOwner(unit);
    }

  @Override
  public void receiveBowAttack(Bow attackBow) {
    super.receiveAttackNormal(attackBow);
  }

  @Override
  public void receiveAxeAttack(Axe attackAxe) { super.receiveAttackNormal(attackAxe); }

  @Override
  public void receiveSwordsAttack(Sword attackSword) { super.receiveStrongAttack(attackSword);  }

  @Override
  public void receiveSpearsAttack(Spear attackSpears) { super.receiveSoftAttack(attackSpears);  }

  @Override
  public void receiveSoulAttack(Soul attackSoul) { super.receiveMagicAttack(attackSoul); }

  @Override
  public void receiveLightAttack(Light attackLight) { super.receiveMagicAttack(attackLight); }

  @Override
  public void receiveDarknessAttack(Darkness attackDarkness) {
    super.receiveMagicAttack(attackDarkness);
  }

}
