package model.items;

public abstract class AbstractHeap extends AbstractItem implements IHeap {
    /**
     * Constructor for a default item without any special behaviour.
     *
     * @param name     the name of the item
     * @param power    the power of the item (this could be the amount of damage or healing the item does)
     * @param minRange the minimum range of the item
     * @param maxRange
     */
    public AbstractHeap(String name, int power, int minRange, int maxRange) {
        super(name, power, minRange, maxRange);
    }

    @Override
    public void receiveAttackNormalDamage(IAttack itemAnything) {
        this.receiveUnitAttack(itemAnything);
    }

    @Override
    public void receiveAttackMagic(IAttack itemMagic) {
        this.receiveUnitWeaknessAttack(itemMagic);
    }
}
