package gui.views.game;

import gui.Keys;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

/**
 * Panel containing information about the current game.
 *
 * @author Ignacio Slater Muñoz
 * @version 3.0b13
 * @since 3.0
 */
public class InfoPane extends JPanel {

  private final Color[] COLORS = new Color[]{
      Color.RED,
      Color.GREEN,
      Color.BLUE,
      Color.LIGHT_GRAY,
      Color.WHITE
  };
  private final String DEFAULT_FONT_NAME = "Dialog";
  private final int DEFAULT_FONT_SIZE = 12;

  private Color paneColor = Color.LIGHT_GRAY;

  /**
   * Creates a new <code>InfoPane</code> with a double buffer and a box layout.
   */
  public InfoPane() {
    setupLayout();
    setupLabels();
    setupColorPicker();
  }

  /**
   * Configures the pane with a box layout and a 10 pixel margin
   */
  private void setupLayout() {
    this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
    Border margin = new EmptyBorder(10, 10, 10, 10);
    this.setBorder(new CompoundBorder(this.getBorder(), margin));
  }

  /**
   * Creates and places the labels of the pane
   */
  private void setupLabels() {
    addLabel("Controls:", 10, DEFAULT_FONT_NAME, 16, Font.BOLD);
    addLabel("ARROW KEYS: Move", 0, "Helvetica", DEFAULT_FONT_SIZE, Font.PLAIN);
    addLabel("ENTER: Play sound", 20, "Helvetica", DEFAULT_FONT_SIZE, Font.PLAIN);
    addLabel("Color picker", 10, DEFAULT_FONT_NAME, 16, Font.BOLD);
  }

  /**
   * Adds a new label to the pane.
   *
   * @param text
   *     the text of the label
   * @param verticalSpace
   *     the space between this label and the next element in the pane
   * @param fontName
   *     the font to be used
   * @param fontSize
   *     the size of the text
   * @param fontStyle
   *     the style of the text, it may be one of Font.PLAIN, Font.BOLD or Font.ITALIC
   */
  private void addLabel(String text, int verticalSpace, String fontName, int fontSize,
      int fontStyle) {
    JLabel label = new JLabel(text);
    label.setFont(new Font(fontName, fontStyle, fontSize));
    this.add(label);
    if (verticalSpace >= 0) {
      this.add(Box.createRigidArea(new Dimension(0, verticalSpace)));
    }
  }

  /**
   * Creates a list of colors to pick and a button to change the current pane's color
   */
  private void setupColorPicker() {
    JList colorList = new JList<>(new String[]{
        "Red",
        "Green",
        "Blue",
        "Gray",
        "White"
    });
    colorList.getInputMap().put(KeyStroke.getKeyStroke(Keys.DOWN), "none");
    colorList.setSelectedIndex(0);
    colorList.setLayout(new BoxLayout(colorList, BoxLayout.PAGE_AXIS));
    removeBindings(colorList, Keys.DOWN, Keys.LEFT, Keys.RIGHT, Keys.UP);
    this.add(colorList);

    JButton pickColorBtn = new JButton("Change color");
    pickColorBtn.addActionListener(e -> {
      paneColor = COLORS[colorList.getSelectedIndex()];
      repaint();
    });
    this.add(pickColorBtn);
  }

  /**
   * Removes the key bindings of a component.
   *
   * @param component
   *     the component to unbind
   * @param keys
   *     the name of the keys that are going to be removed
   */
  private void removeBindings(JComponent component, String... keys) {
    for (String key :
        keys) {
      component.getInputMap().put(KeyStroke.getKeyStroke(key), "none");
    }
  }

  @Override
  protected void paintComponent(final Graphics graphics) {
    graphics.setColor(paneColor);
    graphics.fillRect(0, 0, this.getWidth(), this.getHeight());
  }
}
