package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.TimerTask;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.Timer;
import javax.swing.WindowConstants;
import org.w3c.dom.Text;

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
  private JButton adaptButton;
  private JButton genButton;
  private JButton interButton;
  private JTextArea textArea;
  private boolean isDrawAdaptation;
  private boolean isDrawBiogenesis;
  private boolean isDrawInterference;

  public CrisprCasType2ASwingView() {
    super();

    this.isDrawAdaptation = false;
    this.isDrawBiogenesis = false;
    this.isDrawInterference = false;

    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    this.setSize(new Dimension(500, 750));
    this.setLayout(new BorderLayout());

    JPanel container = new JPanel();
    this.canvas = new CanvasPanel(500, 500);
    container.add(this.canvas);

    JPanel controlPanel = this.initControlPanel();
    container.add(controlPanel);

    JPanel textBox = new JPanel();
    this.textArea = new JTextArea();
    this.textArea.setText("Click on the Adaptation button to start the animation!");
    this.textArea.setEditable(false);
    this.textArea.setLineWrap(true);
    this.textArea.setBounds(50, 50, 290, 300);
    this.textArea.setWrapStyleWord(true);
    this.textArea.setBackground(Color.WHITE);
    textBox.add(this.textArea);
    container.add(textBox);

    this.add(container, BorderLayout.CENTER);
    setVisible(true);
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
          drawBiogenesis();
        } else if (isDrawInterference) {
          // TODO: write out what it takes to visualize interference
          drawInterference();
        }
        canvas.repaint();
      }
    });
  }

  @Override
  public void drawAdaptation() {
    // resetting the canvas
    this.canvas.clearShapes();
    // creating all the shapes needed
    IViewShape cell = new ViewOval(new int[]{180}, new int[]{-50}, 10, 0, 500, 600,
        Color.LIGHT_GRAY);
    IViewShape phage = new ViewRect(new int[]{0}, new int[]{230}, 10, 0, 100, 50, Color.BLUE);
    IViewShape virus = new ViewRect(new int[]{0}, new int[]{250}, 10, 0, 100, 10, Color.RED);
    IViewShape complex = new ViewPoly(
        new int[]{340, 350, 380, 410, 420, 400, 420, 410, 380, 350, 340, 360},
        new int[]{190, 180, 210, 180, 190, 220, 250, 260, 230, 260, 250, 220},
        0, 0, 0, 0, Color.BLACK, 12);
    IViewShape spacer = new ViewRect(new int[]{25}, new int[]{250}, 10, 0, 50, 10, Color.GREEN);
    // setting their X and Y limits if necessary
    phage.setXLimit(80);
    virus.setXLimit(330);
    spacer.setXLimit(355);
    // draw all the shapes needed
    this.canvas.drawShape(cell);
    this.canvas.drawShape(virus);
    this.canvas.drawShape(spacer);
    this.canvas.drawShape(phage);
    this.canvas.drawShape(complex);
    // setting a text description for adaptation
    this.textArea.setText("The blue rectangle is the bacteriophage, the organism that injects viral "
        + "DNA into the bacteria. As the viral DNA (the red + green strand) enters the bacteria,"
        + "the orange polygon called the Cas1-Cas2 complex will be able to extract a portion of the"
        + "viral DNA (the green strand). This portion is then inserted into the CRISPR array ("
        + "not shown on screen), which consists of the first stage to bacterial adaptive immunity.");
    // reset the isDrawAdaptation value to be false so the timer doesn't constantly create new shapes
    this.isDrawAdaptation = false;
  }

  @Override
  public void drawBiogenesis() {
    java.util.Timer utilTimer = new java.util.Timer();
    // resetting the canvas
    this.canvas.clearShapes();
    // creating all shapes needed
    IViewShape cell = new ViewOval(new int[]{0}, new int[]{-50}, 0, 0, 500, 600,
        Color.LIGHT_GRAY);
    IViewShape repeat1 = new ViewRect(new int[]{150}, new int[]{250}, 0, 0, 50, 10,
        Color.BLUE);
    IViewShape spacer1 = new ViewRect(new int[]{200}, new int[]{250}, 0, 10, 50, 10,
        Color.GREEN);
    IViewShape repeat2 = new ViewRect(new int[]{250}, new int[]{250}, 0, 10, 50, 10,
        Color.BLUE);
    IViewShape spacer2 = new ViewRect(new int[]{300}, new int[]{250}, 0, 10, 50, 10,
        Color.RED);
    IViewShape repeat3 = new ViewRect(new int[]{350}, new int[]{250}, 0, 10, 50, 10,
        Color.BLUE);
    // draw all the shapes needed
    this.canvas.drawShape(cell);
    this.canvas.drawShape(repeat1);
    this.canvas.drawShape(spacer1);
    this.canvas.drawShape(repeat2);
    this.canvas.drawShape(spacer2);
    this.canvas.drawShape(repeat3);
    // setting delay for certain animations if necessary
    utilTimer.schedule(new TimerTask() {
      @Override
      public void run() {
        spacer1.setYLimit(150);
        repeat2.setYLimit(150);
        spacer2.setYLimit(380);
        repeat3.setYLimit(380);
        utilTimer.cancel();
      }
    }, 2000);
    // setting a text description for biogenesis
    this.textArea.setText("At this stage, the CRISPR array is transcribed to produce mature CRISPR "
        + "RNAs (crRNAs) that consists of a palindromic repeat (the blue strands) and the acquired "
        + "viral DNA (the green and red strands). These crRNAs would go around in the cell to find "
        + "any potential viruses that might have infected the bacteria. Note that in reality, the "
        + "CRISPR array doesn't break apart like this.");
    // reset the isDrawBiogenesis value to be false so the timer doesn't constantly create new shapes
    this.isDrawBiogenesis = false;
  }

  @Override
  public void drawInterference() {
    java.util.Timer utilTimer = new java.util.Timer();
    // resetting the canvas
    this.canvas.clearShapes();
    // creating all shapes needed
    IViewShape cell = new ViewOval(new int[]{-180}, new int[]{-50}, 10, 0, 500, 600,
        Color.LIGHT_GRAY);
    IViewShape phage = new ViewRect(new int[]{500}, new int[]{230}, 10, 0, 100, 50, Color.BLUE);
    IViewShape virus1 = new ViewRect(new int[]{500}, new int[]{250}, 10, 10, 20, 10, Color.RED);
    IViewShape virus2 = new ViewRect(new int[]{520}, new int[]{250}, 10, 15, 20, 10, Color.GREEN);
    IViewShape virus3 = new ViewRect(new int[]{540}, new int[]{250}, 10, 10, 20, 10, Color.GREEN);
    IViewShape virus4 = new ViewRect(new int[]{560}, new int[]{250}, 10, 20, 20, 10, Color.GREEN);
    IViewShape virus5 = new ViewRect(new int[]{580}, new int[]{250}, 10, 5, 20, 10, Color.RED);
    IViewShape repeat = new ViewRect(new int[]{170}, new int[]{240}, 0, 0, 40, 10,
        Color.BLUE);
    IViewShape spacer = new ViewRect(new int[]{200}, new int[]{240}, 0, 10, 60, 10,
        Color.GREEN);
    // setting X and Y limits if necessary
    phage.setXLimit(320);
    virus1.setXLimit(180);
    virus2.setXLimit(200);
    virus3.setXLimit(220);
    virus4.setXLimit(240);
    virus5.setXLimit(260);
    // draw all the shapes needed
    this.canvas.drawShape(cell);
    this.canvas.drawShape(virus1);
    this.canvas.drawShape(virus2);
    this.canvas.drawShape(virus3);
    this.canvas.drawShape(virus4);
    this.canvas.drawShape(virus5);
    this.canvas.drawShape(phage);
    this.canvas.drawShape(repeat);
    this.canvas.drawShape(spacer);
    utilTimer.schedule(new TimerTask() {
      @Override
      public void run() {
        virus1.setXLimit(600);
        virus1.setYLimit(-100);
        virus2.setXLimit(-100);
        virus2.setYLimit(600);
        virus3.setXLimit(600);
        virus3.setYLimit(600);
        virus4.setXLimit(600);
        virus4.setYLimit(-100);
        virus5.setXLimit(-100);
        virus5.setYLimit(-100);
      }
    }, 5500);
    // setting a text description for interference
    this.textArea.setText("This stage is when the mature crRNAs do find a viral DNA. Once they "
        + "find a matching virus DNA, they will send chemical signals telling the bacteria to "
        + "help destroy the virus. Think of this last step as how our immune system fights off "
        + "disease. It's basically the same thing!");
    // reset the isDrawInterference value to be false so the timer doesn't constantly create new shapes
    this.isDrawInterference = false;
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

  @Override
  public void setListener(ActionListener listener) {
    this.adaptButton.addActionListener(listener);
    this.genButton.addActionListener(listener);
    this.interButton.addActionListener(listener);
  }

  /**
   * Formats a user-friendly control panel that the user should be able to use to manipulate the
   * animation.
   *
   * @return A formatted {@code JPanel} with different {@code JComponent}s
   */
  private JPanel initControlPanel() {
    JPanel controlPanel = new JPanel(new FlowLayout());
    this.adaptButton = new JButton("Adaptation");
    this.adaptButton.setActionCommand("adaptation");
    this.genButton = new JButton("Biogenesis");
    this.genButton.setActionCommand("biogenesis");
    this.interButton = new JButton("Interference");
    this.interButton.setActionCommand("interference");
    controlPanel.add(this.adaptButton);
    controlPanel.add(this.genButton);
    controlPanel.add(this.interButton);

    return controlPanel;
  }
}
