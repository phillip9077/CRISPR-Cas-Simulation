package View;

import java.awt.Color;
import java.awt.Graphics2D;

public class ViewPoly extends AbstractViewShape {

  private int n;

  /**
   * The default constructor for an ViewPoly.
   *
   * @param x     The x coordinate
   * @param y     The y coordinate
   * @param vx    The x velocity
   * @param vy    The y velocity
   * @param w     The width
   * @param h     The height
   * @param color The color
   */
  protected ViewPoly(int[] x, int[] y, int vx, int vy, int w, int h, Color color, int n) {
    super(x, y, vx, vy, w, h, color);
    this.n = n;
  }

  @Override
  public void render(Graphics2D g) {
    g.setColor(Color.ORANGE);
    g.fillPolygon(this.x, this.y, this.n);
  }
}
