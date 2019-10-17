package model.items;

public interface IHeap extends IEquipableItem {
    /**
     * Receive a attack of a item not magic
     * @param itemAnything
     */
    public void receiveAttackNormalDamage(IAttack itemAnything);

    /**
     * @param itemMagic
     */
    void receiveAttackMagic(IAttack itemMagic);
}
