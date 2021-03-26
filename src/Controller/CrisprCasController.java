package Controller;

import Model.ICrisprCasModel;
import View.ICrisprCasView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Represents the user's ability to switch between the three different animations for the CRISPR-Cas
 * adaptation, biogenesis, and interference mechanisms.
 */
public class CrisprCasController implements ICrisprCasController, ActionListener {

  private ICrisprCasModel model;
  private ICrisprCasView view;

  /**
   * Default constructor for a {@code ICrisprCasController}.
   *
   * @param model The reference to a {@code ICrisprCasModel} model
   * @param view  The reference to a {@code ICrisprCasView} view
   */
  public CrisprCasController(ICrisprCasModel model, ICrisprCasView view) {
    this.model = model;
    this.view = view;

    this.view.setListener(this);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    switch (e.getActionCommand()) {
      case "adaptation":
        this.view.setDrawAdaptation(true);
        break;
      case "biogenesis":
        this.view.setDrawBiogenesis(true);
        break;
      case "interference":
        this.view.setDrawInterference(true);
        break;
    }
  }
}
