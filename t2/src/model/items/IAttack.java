package model.items;

public interface IAttack extends IEquipableItem{

    /**
     * @param itemNormal
     */
    public void receiveAttackNormal(IAttack itemNormal);

    /**
     * @param attackStrong
     */
    public void receiveWeakAttack(IAttack attackStrong);

    /**
     * @param attackSoft
     */
    public void receiveSoftAttack(IAttack attackSoft);

    /**
     * @param attackMagic
     */
    public void receiveMagicAttack(IAttack attackMagic);
}
