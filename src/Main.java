import Controller.CrisprCasController;
import Controller.ICrisprCasController;
import Model.CrisprCasType2AModel;
import Model.ICrisprCasModel;
import View.CrisprCasType2ASwingView;
import View.ICrisprCasView;
import java.io.IOException;

public class Main {

  public static void main(String[] args) throws IOException {
    ICrisprCasModel model = new CrisprCasType2AModel();
    ICrisprCasView view = new CrisprCasType2ASwingView();
    ICrisprCasController controller = new CrisprCasController(model, view);
    view.render();
  }
}
