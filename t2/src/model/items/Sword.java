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

/**
 * This class represents a <i>Sword</i> type item.
 * <p>
 * Swords are strong against axes and weak against spears.
 *
 * @author Sebastian Sepulveda
 * @since 1.0
 */
public class Sword extends AbstractAttack implements IAttack {

  /**
   * Creates a new Sword.
   *
   * @param name
   *     the name that identifies the weapon
   * @param power
   *     the base damage pf the weapon
   * @param minRange
   *     the minimum range of the weapon
   * @param maxRange
   *     the maximum range of the weapon
   */
  public Sword(final String name, final int power, final int minRange, final int maxRange) {
    super(name, power, minRange, maxRange);
  }

  @Override
  public void equipTo(IUnit unit) {
    unit.equipItemSword(this);
    this.setOwner(unit);
  }

  @Override
  public void receiveBowAttack(Bow attackBow) {
    receiveAttackNormal(attackBow);
  }
  @Override
  public void receiveSwordsAttack(Sword attackSword) {
    receiveAttackNormal(attackSword);
  }

  @Override
  public void receiveAxeAttack(Axe attackAxe) {
    receiveSoftAttack(attackAxe);
  }

  @Override
  public void receiveSpearsAttack(Spear attackSpears) {
    receiveWeakAttack(attackSpears);
  }

  @Override
  public void receiveSoulAttack(Soul attackSoul)
  { receiveMagicAttack(attackSoul); }

  @Override
  public void receiveLightAttack(Light attackLight)
  { receiveMagicAttack(attackLight); }

  @Override
  public void receiveDarknessAttack(Darkness attackDarkness)
  { receiveMagicAttack(attackDarkness); }

  @Override
  public boolean equals(Object obj) {
    return obj instanceof Sword && super.equals(obj);
  }

}
