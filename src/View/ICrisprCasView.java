package View;

import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Represents different ways a CRISPR-Cas model can be visualized to the user.
 */
public interface ICrisprCasView {

  /**
   * Produces a visualization of the Crispr-Cas model in some way.
   *
   * @throws IOException If the rendering fails for any reason
   */
  void render() throws IOException;

  /**
   * Visualizes the adaptation process in a CRISPR-Cas system.
   *
   * @throws IOException If the rendering fails for some reason
   */
  void drawAdaptation();

  /**
   * Visualizes the crRNA biogenesis process in a CRISPR-Cas system.
   *
   * @throws IOException If the rendering fails for some reason
   */
  void drawBiogenesis();

  /**
   * Visualizes the interference process in a CRISPR-Cas system.
   *
   * @throws IOException If the rendering fails for some reason
   */
  void drawInterference();

  /**
   * Sets the isDrawAdaptation {@code boolean} value to be bool.
   *
   * @param bool the {@code boolean} value to set to
   */
  void setDrawAdaptation(boolean bool);

  /**
   * Sets the isDrawBiogenesis {@code boolean} value to be bool.
   *
   * @param bool the {@code boolean} value to set to
   */
  void setDrawBiogenesis(boolean bool);

  /**
   * Sets the isDrawInterference {@code boolean} value to be bool.
   *
   * @param bool the {@code boolean} value to set to
   */
  void setDrawInterference(boolean bool);

  /**
   * Sets the various {@code JComponents} to an {@code ActionListener}.
   *
   * @param listener the {@code ActionListener} to set to
   */
  void setListener(ActionListener listener);

}
