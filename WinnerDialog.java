import javafx.application.*;
import javafx.scene.*;
import javafx.stage.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.image.*;
import java.util.*;
import javafx.geometry.*;

public class WinnerDialog {

  public static void show(String message) {
    Stage stage = new Stage();

    stage.initModality(Modality.APPLICATION_MODAL);
    stage.setTitle("Congratulations!");
    stage.setMinWidth(200);
    stage.setMinHeight(100);

    Label label = new Label(message);
    Button playAgainButton = new Button("Play Again");
    Button exitButton = new Button("Quit Game");

    VBox layout = new VBox(10);
    layout.setAlignment(Pos.CENTER);

    HBox buttonLayout = new HBox(10);
    buttonLayout.getChildren().addAll(playAgainButton, exitButton);
    buttonLayout.setAlignment(Pos.CENTER);

    layout.getChildren().addAll(label, buttonLayout);

    Scene scene = new Scene(layout, 335, 100);
    stage.setScene(scene);
    stage.show();
  }

}
