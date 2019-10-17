package model.items;

public abstract class AbstractAttack extends AbstractItem implements IAttack {
    /**
     * Constructor for a default item without any special behaviour.
     *
     * @param name     the name of the item
     * @param power    the power of the item (this could be the amount of damage or healing the item does)
     * @param minRange the minimum range of the item
     * @param maxRange
     */
    public AbstractAttack(String name, int power, int minRange, int maxRange) {
        super(name, power, minRange, maxRange);
    }
    public void receiveAttackNormal(IAttack itemNormal){
        this.receiveUnitAttack(itemNormal);
        if(this.canAttack(itemNormal)){
            itemNormal.receiveUnitAttack(this);
        }
    }

    public void receiveWeakAttack(IAttack attackStrong) {
        this.receiveUnitWeaknessAttack(attackStrong);
        if(this.canAttack(attackStrong)){
            attackStrong.receiveUnitResistantAttack(this);
        }
    }

    public void receiveSoftAttack(IAttack attackSoft) {
        this.receiveUnitResistantAttack(attackSoft);
        if(this.canAttack(attackSoft)){
            attackSoft.receiveUnitWeaknessAttack(this);
        }
    }

    @Override
    public void receiveMagicAttack(IAttack attackMagic) {
        this.receiveUnitWeaknessAttack(attackMagic);
        if(this.canAttack(attackMagic)){
            attackMagic.receiveUnitWeaknessAttack(this);
        }
    }
}