package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

public class CanvasPanel extends JPanel {

  private List<IViewShape> shapes;

  /**
   * The default constructor of this CanvasPanel.
   *
   * @param w The width of the canvas
   * @param h The height of the canvas
   */
  public CanvasPanel(int w, int h) {
    super();
    setPreferredSize(new Dimension(w, h));
    setBackground(Color.WHITE);
    this.shapes = new ArrayList<>();
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;

    for (IViewShape shape: this.shapes) {
      shape.render(g2d);
    }
  }

  /**
   * Adds a desired shape to be drawn into the list of IViewShapes.
   *
   * @param shape The desired shape to be drawn
   */
  public void drawShape(IViewShape shape) {
    shapes.add(shape);
  }
}
