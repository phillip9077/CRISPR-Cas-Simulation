package View;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 * Represents an oval that has a x-y position, a width, a height, and a color, which knows how
 * to draw itself through the render method.
 */
public class ViewOval extends AbstractViewShape {

  /**
   * The default constructor for an ViewOval.
   *
   * @param x     The x coordinate
   * @param y     The y coordinate
   * @param vx    The x velocity
   * @param vy    The y velocity
   * @param w     The width
   * @param h     The height
   * @param color The color
   */
  protected ViewOval(int x, int y, int vx, int vy, int w, int h, Color color) {
    super(x, y, vx, vy, w, h, color);
  }

  @Override
  public void render(Graphics2D g) {
    g.setColor(color);
    g.fillOval(x, y, w, h);
    this.x += vx;
    this.y += vy;
  }
}
