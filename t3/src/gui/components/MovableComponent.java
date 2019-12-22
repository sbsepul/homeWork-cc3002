package gui.components;

import java.awt.Image;
import java.awt.Point;
import javax.swing.ImageIcon;

/**
 * This class represents an object that can be moved inside the game.
 *
 * @author Ignacio Slater Muñoz
 * @version 3.0b9
 * @since 3.0
 */
public class MovableComponent {

  private final Image sprite;
  private Point position;

  /**
   * Creates a new component.
   *
   * @param position
   *     the starting position of the component
   * @param spritePath
   *     the path to the component's sprite
   */
  public MovableComponent(final Point position, final String spritePath) {
    this.position = position;
    sprite = new ImageIcon(spritePath).getImage();
  }

  /**
   * Moves the component to a new position
   *
   * @param horizontalDistance
   *     the horizontal movement
   * @param verticalDistance
   *     the vertical movement
   */
  public void move(int horizontalDistance, int verticalDistance) {
    position.translate(horizontalDistance, verticalDistance);
  }

  /**
   * @return the component's sprite
   */
  public Image getSprite() {
    return sprite;
  }

  /**
   * @return the component's horizontal position
   */
  public int getHorizontalPosition() {
    return (int) position.getX();
  }

  /**
   * @return the component's vertical position
   */
  public int getVerticalPosition() {
    return (int) position.getY();
  }
}
