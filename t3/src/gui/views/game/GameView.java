package gui.views.game;

import javax.swing.JSplitPane;

/**
 * The main game view.
 * The screen is divided in two sections:
 * <ul>
 *   <li>
 *     The left one has the game's field
 *   </li>
 *   <li>
 *     The one to the right contains information about the game
 *   </li>
 * </ul>
 *
 * @author Ignacio Slater Muñoz
 * @version 3.0b11
 * @since 3.0
 */
public class GameView extends JSplitPane {

  /**
   * Creates a new <code>JSplitPane</code> configured to arrange the child
   * components side-by-side horizontally, using two buttons for the components.
   */
  public GameView() {
    super(HORIZONTAL_SPLIT, new FieldPane(), new InfoPane());
    this.setDividerLocation(1280 * 3 / 4);
    this.setEnabled(false);
  }
}
