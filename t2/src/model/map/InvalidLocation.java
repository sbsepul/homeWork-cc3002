package model.map;

/**
 * This class represents an empty or invalid location on the game's map.
 *
 * @author Sebastian Sepulveda
 * @since 1.0
 */
public class InvalidLocation extends Location {

  /**
   * Creates an empty location on the game's map
   */
  public InvalidLocation() {
    super(-1, -1);
  }

  @Override
  public void addNeighbour(final Location neighbour) {
  }

  @Override
  protected void addTo(final Location location) {
  }

  @Override
  public boolean isValid() {
    return false;
  }
}
