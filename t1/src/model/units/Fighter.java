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
 * This class represents a fighter type unit.
 * A fighter is a unit that can only use axe type weapons.
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class Fighter extends AbstractUnit {

  public Fighter(final int hitPoints, final int movement, final Location location,
      IEquipableItem... items) {
    super(hitPoints, movement, location, 3, items);
  }
  @Override
  public void equipItemAxe(Axe item) { equippedItem = item;  }

  @Override
  public IEquipableItem getEquippedItem() { return equippedItem; }

  @Override
  public void equipItemBow(Bow item) { }

  @Override
  public void equipItemSword(Sword item) { }

  @Override
  public void equipItemStaff(Staff item) { }

  @Override
  public void equipItemSpear(Spear item) { }

  @Override
  public void equipItemOther(IEquipableItem item) { }
}
