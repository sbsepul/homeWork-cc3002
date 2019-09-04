package model.units;

import model.items.*;
import model.items.axe.AttackAxe;
import model.items.axe.Axe;
import model.items.bow.AttackBow;
import model.items.bow.Bow;
import model.items.spears.AttackSpears;
import model.items.spears.Spear;
import model.items.staff.AttackStaff;
import model.items.staff.Staff;
import model.items.sword.AttackSword;
import model.items.sword.Sword;
import model.map.Location;

/**
 * This class represents a <i>SwordMaster</i> type unit.
 * <p>
 * A <i>SwordMaster</i> is a unit that <b>can only use sword type weapons</b>.
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class SwordMaster extends AbstractUnit {

  public SwordMaster(final int hitPoints, final int movement, final Location location,
      IEquipableItem... items) {
    super(hitPoints, movement, location, 3, items);
  }

  @Override
  public void equipItemSword(Sword item) { equippedItem=item; }
  @Override
  public void equipItemOther(IEquipableItem item) { }
  @Override
  public void equipItemBow(Bow item) { }
  @Override
  public void equipItemAxe(Axe item) { }
  @Override
  public void equipItemStaff(Staff item) { }
  @Override
  public void equipItemSpear(Spear item) { }
}
