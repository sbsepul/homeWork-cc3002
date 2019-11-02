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

import model.units.IUnit;

/**
 * Abstract class that defines some common information and behaviour between all items normals.
 *
 * @author Sebastian Sepulveda
 * @version 2.0
 * @since v1.0
 */
public abstract class AbstractItem implements IEquipableItem {

  private final String name;
  private final int power;
  protected int maxRange;
  protected int minRange;
  private IUnit owner;
  protected int distance;

  /**
   * Constructor for a default item without any special behaviour.
   *
   * @param name
   *     the name of the item
   * @param power
   *     the power of the item (this could be the amount of damage or healing the item does)
   * @param minRange
   *     the minimum range of the item
   * @param maxRange
   *     the maximum range of the item
   */
  public AbstractItem(final String name, final int power, final int minRange, final int maxRange) {
    this.name = name;
    this.power = power;
    this.minRange = Math.max(minRange, 1);
    this.maxRange = Math.max(maxRange, this.minRange);
  }


  @Override
  public boolean canAttack(IEquipableItem itemEnemy) {
    if(itemEnemy.getOwner()!=null){
      return this.getOwner().getCurrentHitPoints()>0 && itemEnemy.getOwner().getCurrentHitPoints()>0
              && inRangeItem();
    }
    return false;
  }

  @Override
  public boolean inRangeItem(){
    return (this.minRange<=this.distance && this.maxRange >= this.distance);
  }

  @Override
  public IUnit getOwner() {
    return owner;
  }

  @Override
  public void setOwner(IUnit owner) {
    this.owner = owner;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public int getPower() {
    return power;
  }

  @Override
  public int getMinRange() {
    return minRange;
  }

  @Override
  public int getMaxRange() {
    return maxRange;
  }

  @Override
  public void receiveUnitWeaknessAttack(IEquipableItem attack){
    this.getOwner().receiveAttackWeakness(attack);
  }

  @Override
  public void receiveUnitResistantAttack(IEquipableItem attack) {
    this.getOwner().receiveAttackResistant(attack);
  }


  @Override
  public void receiveUnitAttack(IEquipableItem attack) {
    this.getOwner().receiveAttack(attack);
  }

  @Override
  public void giveMagicAttack(IEquipableItem itemMagic){ }

  @Override
  public int getDistance() {
    return this.distance;
  }

  @Override
  public void setDistance(int distance) {
    this.distance = distance;
  }

}
