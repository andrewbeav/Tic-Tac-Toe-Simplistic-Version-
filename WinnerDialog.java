import javafx.application.*;
import javafx.scene.*;
import javafx.stage.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.image.*;
import java.util.*;

public class WinnerDialog {

  public static void show(String message) {
    Stage stage = new Stage();

    stage.initModality(Modality.APPLICATION_MODAL);
    stage.setTitle("Congratulations!");
    stage.setMinWidth(200);
    stage.setMinHeight(100);

    BorderPane layout = new BorderPane();

    Label label = new Label(message);

    Scene scene = new Scene(layout);
    stage.setScene(scene);
    stage.show();
  }

}
