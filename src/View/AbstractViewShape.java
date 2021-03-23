package View;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 * A representation of an IViewShape with a x-y position, a width, a height, and a color, which
 * knows how to draw itself through the render method.
 */
public abstract class AbstractViewShape implements IViewShape {

  protected int x;
  protected int y;
  protected int vx;
  protected int vy;
  protected final int w;
  protected final int h;
  protected final Color color;
  protected int xLimit;
  protected int yLimit;

  /**
   * The default constructor for an AbstractViewShape.
   *
   * @param x     The x coordinate
   * @param y     The y coordinate
   * @param vx    The x velocity
   * @param vy    The y velocity
   * @param w     The width
   * @param h     The height
   * @param color The color
   */
  protected AbstractViewShape(int x, int y, int vx, int vy, int w, int h, Color color) {
    this.x = x;
    this.y = y;
    this.vx = vx;
    this.vy = vy;
    this.w = w;
    this.h = h;
    this.color = color;
  }

  @Override
  public abstract void render(Graphics2D g);

  @Override
  public void setXLimit(int xLimit) {
    this.xLimit = xLimit;
  }

  @Override
  public void setYLimit(int yLimit) {
    this.yLimit = yLimit;
  }

}
