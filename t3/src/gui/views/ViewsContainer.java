package gui.views;

import gui.views.game.GameView;
import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Container for the game views
 *
 * @author Ignacio slater Muñoz (mailto:ignacio.slater@ug.uchile.cl)
 * @version 3.0b11
 * @since 3.0
 */
public class ViewsContainer extends JPanel {

  private final JFrame game;

  /**
   * Creates the container as a panel that can contain multiple cards.
   *
   * @param game
   *     the main frame of the game.
   */
  public ViewsContainer(final JFrame game) {
    super(new CardLayout());
    this.game = game;
    setupViews();
  }

  /**
   * Creates the views inside the container.
   */
  private void setupViews() {
    this.add(new LandingView(this));
    this.add(new GameView());
  }

  /**
   * Modifies the dimensions of the frame that holds this container
   *
   * @param width
   *     the new horizontal dimensions of the frame
   * @param height
   *     the new vertical dimensions of the frame
   */
  public void changeFrameDimensions(final int width, final int height) {
    game.setSize(width, height);
  }

  public void nextView() {
    ((CardLayout) super.getLayout()).next(this);
  }
}
