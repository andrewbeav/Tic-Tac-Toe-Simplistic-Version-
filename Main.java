import javafx.application.*;
import javafx.scene.*;
import javafx.stage.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.image.*;
import java.util.*;

public class Main extends Application {

  Board gameBoard;
  private int boardSize = 3;
  private int currentPlayer = 1;

  private boolean isGamePlaying = true;

  private Image oIcon = new Image(getClass().getResourceAsStream("o.png"));
  private Image xIcon = new Image(getClass().getResourceAsStream("x.png"));

  ArrayList<Button> buttonList = new ArrayList<>();

  GridPane buttonGrid;

  Stage primaryStage;

  public static void main(String[] args) {
    launch(args);
  }

  public void start(Stage primaryStage) {

    this.primaryStage = primaryStage;
    primaryStage.setTitle("Tic Tac Toe");

    // Board Stuff
    gameBoard = new Board(boardSize);

    BorderPane layout = new BorderPane();

    buttonGrid = new GridPane();
    int numOfButtons = boardSize*boardSize;
    makeButtonGrid(numOfButtons);

    layout.setCenter(buttonGrid);

    Scene scene = new Scene(layout, 100*boardSize, 100*boardSize);
    scene.getStylesheets().add("styles.css");
    primaryStage.setScene(scene);
    primaryStage.show();

  }

  private void makeButtonGrid(int numOfButtons) {
    for (int i = 0; i < numOfButtons; i++) {
      Button button = new Button();
      button.setMinWidth(100);
      button.setMinHeight(100);

      // Style Stuff
      button.getStyleClass().add("grid_button");

      button.setOnAction(e -> handleButtonEvent(button));

      GridPane.setRowIndex(button, getRowOfButton(i));
      GridPane.setColumnIndex(button, getColumnOfButton(i));

      buttonList.add(button);
    }

    addButtonsToGrid();
  }

  private void addButtonsToGrid() {
    for (int i = 0; i < buttonList.size(); i++) {
      buttonGrid.getChildren().add(buttonList.get(i));
    }
  }

  private void handleButtonEvent(Button button) {
    int index = findIndexOfButton(button);
    int btnRow = getRowOfButton(index);
    int btnCol = getColumnOfButton(index);

    if (isGamePlaying && !gameBoard.checkIfOwned(btnRow, btnCol)) {
      if (currentPlayer == 1) button.setGraphic(new ImageView(xIcon));
      else if (currentPlayer == 2) button.setGraphic(new ImageView(oIcon));

      gameBoard.assignOwner(getRowOfButton(index), getColumnOfButton(index), currentPlayer);

      swapCurrentPlayer();
    }

    if (gameBoard.checkForWin()) {
      isGamePlaying = false;
      if (WinnerDialog.show("Player " + gameBoard.getWinner() + " won the game!") == true) {
        resetGui();
      }
      else {
        primaryStage.close();
      }
    }
    else if (gameBoard.checkForScratch()) {
      isGamePlaying = false;
      if (WinnerDialog.show("Scratch!")){
        resetGui();
      }
      else {
        primaryStage.close();
      }
    }
  }

  private void resetGui() {
    for(Button button : buttonList) {
      button.setGraphic(null);
      gameBoard.reset();
    }

    isGamePlaying = true;
  }

  private void swapCurrentPlayer() {
    if (currentPlayer == 1) currentPlayer = 2;
    else currentPlayer = 1;
  }

  private int findIndexOfButton(Button button) {
    for (int i = 0; i < buttonList.size(); i++) {
      if (button == buttonList.get(i)) return i;
    }

    return -1;
  }

  public int getRowOfButton(int index) {
    int row = -1;

    int minVal = 0;
    for (int r = 0; r < boardSize; r++) {
      if (index < (boardSize*(r+1)) && index >= minVal) row = r;
      minVal += boardSize;
    }

    return row;
  }

  public int getColumnOfButton(int index) {
    int column = -1;

    int minVal = 0;
    for (int c = 0; c < boardSize; c++) {
      if (index % boardSize == c) column = c;
      minVal += boardSize;
    }

    return column;
  }

}
