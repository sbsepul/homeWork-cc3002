package model.units;

import model.items.IEquipableItem;
import model.map.Location;

import java.util.List;

public class Basic implements IUnit{

  @Override
  public void equipItem(IEquipableItem item) {

  }

  @Override
  public int getCurrentHitPoints() {
    return 0;
  }

  @Override
  public List<IEquipableItem> getItems() {
    return null;
  }

  @Override
  public IEquipableItem getEquippedItem() {
    return null;
  }

  @Override
  public void setEquippedItem(IEquipableItem item) {

  }

  @Override
  public Location getLocation() {
    return null;
  }

  @Override
  public void setLocation(Location location) {

  }

  @Override
  public int getMovement() {
    return 0;
  }

  @Override
  public void moveTo(Location targetLocation) {

  }
}
