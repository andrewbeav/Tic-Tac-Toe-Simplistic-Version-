import javafx.application.*;
import javafx.scene.*;
import javafx.stage.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.image.*;
import java.util.*;
import javafx.geometry.*;

public class WinnerDialog {

  private static boolean toResetBoard = false;

  public static boolean show(String message) {
    Stage stage = new Stage();

    toResetBoard = false;

    stage.initModality(Modality.APPLICATION_MODAL);
    stage.setTitle("Congratulations!");
    stage.setMinWidth(200);
    stage.setMinHeight(100);

    Label label = new Label(message);
    label.getStyleClass().add("label");

    VBox layout = new VBox(10);
    layout.getStyleClass().add("background");
    layout.setAlignment(Pos.CENTER);

    HBox buttonLayout = new HBox(10);

    Button playAgainButton = new Button("Play Again");
    Button exitButton = new Button("Quit Game");

    playAgainButton.setOnAction(e -> {
      stage.close();
      toResetBoard = true;
    });

    exitButton.setOnAction(e -> {
      stage.close();
      toResetBoard = false;
    });

    playAgainButton.getStyleClass().add("button");
    exitButton.getStyleClass().add("button");

    buttonLayout.getChildren().addAll(playAgainButton, exitButton);
    buttonLayout.setAlignment(Pos.CENTER);

    layout.getChildren().addAll(label, buttonLayout);

    Scene scene = new Scene(layout, 335, 100);
    scene.getStylesheets().add("winner_dialog_styles.css");
    stage.setScene(scene);
    stage.showAndWait();

    return toResetBoard;
  }

}
