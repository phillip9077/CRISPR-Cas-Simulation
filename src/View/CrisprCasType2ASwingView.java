package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.Timer;
import javax.swing.WindowConstants;

/**
 * Represents the Swing visualization of the CRISPR-Cas model with a timer to produce an actual
 * simulation of the bacterial CRISPR-Cas system. The three animations are written separately to
 * allow more flexibility for the Controller; it would be able to call upon each animation as
 * necessary.
 */
public class CrisprCasType2ASwingView extends JFrame implements ICrisprCasView {

  /*
  Desired methods:
    - visualization for adaptation
    - visualization for biogenesis
    - visualization for interference
    - have JButtons or something to allow the user to dictate when to perform those three methods
      * will have to enforce the methods called in the right order (hopefully)

  The controller, when the user indicates so, will call the adaptation/biogenesis/interference
  method from both the model and view.

  The user will input a random DNA sequence for adaptation and its animation plays; a call to
  biogenesis will show the DNA sequence being inserted into the CRISPR array; the final call to
  interference should show a bacteriophage attempting to insert the same DNA sequence, but failing
  to do so.
   */

  private final CanvasPanel canvas;
  private Timer timer;
  private boolean isDrawAdaptation;
  private boolean isDrawBiogenesis;
  private boolean isDrawInterference;

  public CrisprCasType2ASwingView() {
    super();

    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    this.setSize(new Dimension(500, 500));

    this.canvas = new CanvasPanel(500, 500);
    this.add(this.canvas);

    setVisible(true);

    this.isDrawAdaptation = false;
    this.isDrawBiogenesis = false;
    this.isDrawInterference = false;
  }

  @Override
  public void render() throws IOException {
    this.initTimer();
    this.timer.start();
  }

  /**
   * Initializes the timer necessary to animate a CRISPR-Cas model.
   */
  private void initTimer() {
    this.timer = new Timer(100, new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        // TODO: think about how the whole animation should be played out (does it loop? should it stop after one cycle? etc)
        if (isDrawAdaptation) {
          drawAdaptation();
        } else if (isDrawBiogenesis) {
          // TODO: write out what it takes to visualize biogenesis
        } else if (isDrawInterference) {
          // TODO: write out what it takes to visualize interference
        }
        canvas.repaint();
      }
    });
  }

  @Override
  public void drawAdaptation() {
    // creating all the shapes needed
    IViewShape cell = new ViewOval(180, -50, 0, 0, 500, 600, Color.LIGHT_GRAY);
    IViewShape phage = new ViewRect(0, 230, 10, 0, 100, 50, Color.BLUE);
    IViewShape virus = new ViewRect(0, 250, 10, 0, 100, 10, Color.RED);
    // setting their X and Y limits if necessary
    phage.setXLimit(80);
    virus.setXLimit(330);
    // draw all the shapes needed
    this.canvas.drawShape(cell);
    this.canvas.drawShape(virus);
    this.canvas.drawShape(phage);
    // reset the isDrawAnimation value to be false so the timer doesn't constantly create new shapes
    this.isDrawAdaptation = false;
  }

  @Override
  public void drawBiogenesis() {

  }

  @Override
  public void drawInterference() {

  }

  @Override
  public void setDrawAdaptation(boolean bool) {
    this.isDrawAdaptation = bool;
  }

  @Override
  public void setDrawBiogenesis(boolean bool) {
    this.isDrawBiogenesis = bool;
  }

  @Override
  public void setDrawInterference(boolean bool) {
    this.isDrawInterference = bool;
  }
}
