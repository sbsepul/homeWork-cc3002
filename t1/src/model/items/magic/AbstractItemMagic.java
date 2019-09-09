package model.items.magic;

import model.items.*;

/**
 * Abstract class that defines some common information
 * and behaviour between all items magics.
 *
 * @author Sebastian Sepulveda
 * @since 1.0
 */

public abstract class AbstractItemMagic extends AbstractItem implements IEquipableItem{
    /**
     * Constructor for a default item magic
     *
     * @param name     the name of the item
     * @param power    the power of the item
     * @param minRange the minimum range of the item
     * @param maxRange the maximum range of the item
     */
    public AbstractItemMagic(String name, int power, int minRange, int maxRange) {
        super(name, power, minRange, maxRange);
    }

    @Override
    public void receiveAxeAttack(Axe attackAxe) {
        this.receiveWeaknessAttack(attackAxe);
        if(this.canAttack(attackAxe)){
            attackAxe.getOwner().receiveAttackWeakness(this);
        }
    }

    @Override
    public void receiveBowAttack(Bow attackBow) {
        this.receiveWeaknessAttack(attackBow);
        if(this.canAttack(attackBow)){
            attackBow.getOwner().receiveAttackWeakness(this);
        }
    }

    @Override
    public void receiveSpearsAttack(Spear attackSpears) {
        this.receiveWeaknessAttack(attackSpears);
        if(this.canAttack(attackSpears)){
            attackSpears.getOwner().receiveAttackWeakness(this);
        }
    }

    @Override
    public void receiveSwordsAttack(Sword attackSword) {
        this.receiveWeaknessAttack(attackSword);
        if(this.canAttack(attackSword)){
            attackSword.getOwner().receiveAttackWeakness(this);
        }
    }

}
