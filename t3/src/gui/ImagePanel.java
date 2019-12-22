package gui;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import org.jetbrains.annotations.NotNull;

/**
 * This class represents a panel with a background image.
 *
 * @author Ignacio Slater Muñoz.
 * @version 3.0.6
 * @since 3.0
 */
public class ImagePanel extends JPanel {

  protected Image panelBackground;

  /**
   * Creates a new panel using a picture.
   *
   * @param imgPath
   *     the path to the background image
   */
  public ImagePanel(String imgPath) {
    super();
    this.panelBackground = new ImageIcon(imgPath).getImage();
  }

  @Override
  public void paintComponent(@NotNull Graphics g) {
    g.drawImage(panelBackground, 0, 0, null);
  }

}


