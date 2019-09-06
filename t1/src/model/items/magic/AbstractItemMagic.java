package model.items.magic;

import model.items.*;

/**
 * Abstract class that defines some common information and behaviour between all items magics.
 *
 * @author Sebastian Sepulveda
 * @since 1.0
 */

public abstract class AbstractItemMagic extends AbstractItem implements IEquipableItem{
    /**
     * Constructor for a default item without any special behaviour.
     *
     * @param name     the name of the item
     * @param power    the power of the item (this could be the amount of damage or healing the item does)
     * @param minRange the minimum range of the item
     * @param maxRange
     */
    public AbstractItemMagic(String name, int power, int minRange, int maxRange) {
        super(name, power, minRange, maxRange);
    }

    @Override
    public void receiveAxeAttack(Axe attackAxe) {
        this.receiveWeaknessAttack(attackAxe);
        if(attackAxe.getOwner().getCurrentHitPoints()>0){
            attackAxe.getOwner().receiveAttackWeakness(this);
        }
    }

    @Override
    public void receiveBowAttack(Bow attackBow) {
        this.receiveWeaknessAttack(attackBow);
        if(attackBow.getOwner().getCurrentHitPoints()>0){
            attackBow.getOwner().receiveAttackWeakness(this);
        }
    }

    @Override
    public void receiveSpearsAttack(Spear attackSpears) {
        this.receiveWeaknessAttack(attackSpears);
        if(attackSpears.getOwner().getCurrentHitPoints()>0){
            attackSpears.getOwner().receiveAttackWeakness(this);
        }
    }

    @Override
    public void receiveSwordsAttack(Sword attackSword) {
        this.receiveWeaknessAttack(attackSword);
        if(attackSword.getOwner().getCurrentHitPoints()>0){
            attackSword.getOwner().receiveAttackWeakness(this);
        }
    }

}
