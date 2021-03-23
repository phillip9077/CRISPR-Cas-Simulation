package View;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 * Represents a rectangle that has a x-y position, a width, a height, and a color, which knows how
 * to draw itself through the render method.
 */
public class ViewRect extends AbstractViewShape {

  /**
   * The default constructor for an ViewRect.
   *
   * @param x     The x coordinate
   * @param y     The y coordinate
   * @param vx    The x velocity
   * @param vy    The y velocity
   * @param w     The width
   * @param h     The height
   * @param color The color
   */
  protected ViewRect(int x, int y, int vx, int vy, int w, int h, Color color) {
    super(x, y, vx, vy, w, h, color);
  }

  @Override
  public void render(Graphics2D g) {
    if (this.x < this.xLimit) {
      this.x += vx;
    }
    if (this.y < this.yLimit) {
      this.y += vy;
    }
    g.setColor(color);
    g.fillRect(x, y, w, h);
  }
}
