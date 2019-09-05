package model.items.magic;

import model.items.AbstractItem;
import model.items.IEquipableItem;
import model.units.IUnit;

public class Soul extends AbstractItem {
    /**
     * Constructor for a default item without any special behaviour.
     *
     * @param name     the name of the item
     * @param power    the power of the item (this could be the amount of damage or healing the item does)
     * @param minRange the minimum range of the item
     * @param maxRange
     */
    public Soul(String name, int power, int minRange, int maxRange) {
        super(name, power, minRange, maxRange);
    }

    @Override
    public void equipTo(IUnit unit) {
        unit.equipItemSoul(this);
        this.setOwner(unit);
    }

    public void magicAttack(IEquipableItem enemyAttack){
        enemyAttack.receiveSoulAttack(this);
    }

    @Override
    public void receiveDarknessAttack(Darkness attackDarkness) {
        this.receiveWeaknessAttack(attackDarkness);
        if(attackDarkness.getOwner().getCurrentHitPoints()>0){
            attackDarkness.getOwner().receiveAttackResistant(this);
        }
    }

    @Override
    public void receiveLightAttack(Light attackLight) {
        this.receiveResistantAttack(attackLight);
        if(attackLight.getOwner().getCurrentHitPoints()>0){
            attackLight.getOwner().receiveAttackWeakness(this);
        }
    }

    @Override
    public void receiveSoulAttack(Soul attackSoul) {
        this.receiveAttack(attackSoul);
        if(attackSoul.getOwner().getCurrentHitPoints()>0){
            attackSoul.getOwner().receiveAttack(this);
        }
    }
}
