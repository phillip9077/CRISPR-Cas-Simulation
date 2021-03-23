import View.CrisprCasType2ASwingView;
import View.ICrisprCasView;
import java.io.IOException;

public class Main {

  public static void main(String[] args) throws IOException {
    ICrisprCasView view = new CrisprCasType2ASwingView();
    view.setDrawAdaptation(true);
    view.render();
  }
}
