package model.items.magic;

import model.items.*;

/**
 * Abstract class that defines some common information
 * and behaviour between all items magics.
 *
 * @author Sebastian Sepulveda
 * @since 1.0
 */

public abstract class AbstractItemMagic extends AbstractAttack implements IAttack{
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
        super.receiveMagicAttack(attackAxe);
    }

    @Override
    public void receiveBowAttack(Bow attackBow) {
        super.receiveMagicAttack(attackBow);
    }

    @Override
    public void receiveSpearsAttack(Spear attackSpears) {
        super.receiveMagicAttack(attackSpears);
    }

    @Override
    public void receiveSwordsAttack(Sword attackSword) {
        super.receiveMagicAttack(attackSword);
    }

}
