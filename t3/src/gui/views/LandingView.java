package gui.views;

import gui.ImagePanel;
import javax.swing.JButton;

/**
 * This class represents the landing panel of the application.
 * <p>
 * A landing panel is the first view that the user will see when running the application.
 * </p>
 * This panel contains a button to move to the next view.
 *
 * @author Ignacio Slater Muñoz (mailto:ignacio.slater@ug.uchile.cl)
 * @version 3.0.9
 * @since 3.0
 */
public class LandingView extends ImagePanel {

  private static final String BACKGROUND_IMAGE = "resources/landing_page.jpg";
  private final ViewsContainer container;
  private JButton changeViewBtn;

  /**
   * Creates de landing panel with a background image and a button.
   *
   * @param container
   *     the container of this panel.
   */
  public LandingView(final ViewsContainer container) {
    super(BACKGROUND_IMAGE);
    this.container = container;
    this.setLayout(null);
    setupChangeViewButton();
    this.add(changeViewBtn);
  }

  /**
   * Configures the change view button at the center of the view.
   */
  private void setupChangeViewButton() {
    changeViewBtn = new JButton("Change view");
    changeViewBtn.addActionListener(e -> {
      LandingView.this.changeContainerDimensions(1280, 720);
      container.nextView();
    });
    changeViewBtn.setBounds(panelBackground.getWidth(null) / 2 - 160,
        panelBackground.getHeight(null) / 2 - 20, 320, 40);
  }

  /**
   * Changes the dimensions of this view's container.
   *
   * @param width
   *     the new horizontal size of the container.
   * @param height
   *     the new vertical size of the container.
   */
  private void changeContainerDimensions(final int width, final int height) {
    container.changeFrameDimensions(width, height);
  }
}
