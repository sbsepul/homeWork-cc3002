package model.items;

public interface IHeap extends IEquipableItem {
    /**
     * @param itemAnything
     */
    public void receiveAttackNormalDamage(IAttack itemAnything);

    /**
     * @param itemMagic
     */
    void receiveAttackMagic(IAttack itemMagic);
}
