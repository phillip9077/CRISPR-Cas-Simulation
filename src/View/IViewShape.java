package View;

import java.awt.Graphics2D;

/**
 * A function class that delegates how to specifically draw an IViewShape to its respective
 * ViewShape subclasses.
 */
public interface IViewShape {

  /**
   * Renders a shape onto the main JPanel.
   *
   * @param g The object that helps render 2D shapes
   */
  void render(Graphics2D g);

  /**
   * Sets a maximum X coordinate this IViewShape can travel to.
   *
   * @param xLimit The maximum X coordinate
   */
  void setXLimit(int xLimit);

  /**
   * Sets a maximum Y coordinate this IViewShape can travel to.
   *
   * @param yLimit The maximum Y coordinate
   */
  void setYLimit(int yLimit);
}
