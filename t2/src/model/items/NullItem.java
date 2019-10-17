package model.items;

import model.items.magic.Darkness;
import model.items.magic.Light;
import model.items.magic.Soul;
import model.units.IUnit;

public class NullItem extends AbstractItem {

    public NullItem() {
        super(" ", 0, -1, -1);
    }

    /**
     * Equips this item to a unit.
     *
     * @param unit the unit that will be quipped with the item
     */
    @Override
    public void equipTo(IUnit unit) { }

    @Override
    public void receiveBowAttack(Bow attackBow) { }
    @Override
    public void receiveAxeAttack(Axe attackAxe) { }
    @Override
    public void receiveSwordsAttack(Sword attackSword) { }
    @Override
    public void receiveSpearsAttack(Spear attackSpears) { }
    @Override
    public void receiveDarknessAttack(Darkness attackDarkness) { }
    @Override
    public void receiveLightAttack(Light attackLight) {  }
    @Override
    public void receiveSoulAttack(Soul attackSoul) {  }
}
