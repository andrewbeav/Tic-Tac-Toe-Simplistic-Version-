import java.util.*;

public class Board {
  private int boardSize = 3; // Size of the board
  private int[][] board; // actual board
  private int winner; // The winner variable

  // initializing the scores
  private int player1Score = 0;
  private int player2Score = 0;

  boolean toPrintOnCommandLine = false; // initializing boolean variable to print grid on command line

  private void initBoard() { // This method initializes the board and sets all values to 0
    for (int r = 0; r < boardSize; r++) {
      for (int c = 0; c < boardSize; c++) {
        board[r][c] = 0;
      }
    }
  }

  public int getPlayer1Score() { // Getter method for player 1 score
    return player1Score;
  }

  public int getPlayer2Score() { // Getter method for player 2 score
    return player2Score;
  }

  public Board(int boardSize) {
    this.boardSize = boardSize;

    board = new int[boardSize][boardSize];
    initBoard();
  }

  public void setBoardSize(int boardSize) {
    this.boardSize = boardSize;
  }

  public void reset() { // Reset method
    initBoard();
  }

  public int getWinner() { // Getter method for winner
    return winner;
  }

  public void assignOwner(int r, int c, int player) { // This method assigns a player to a cell
    board[r][c] = player;
    if (toPrintOnCommandLine) printBoard();
  }

  public boolean checkIfOwned(int r, int c) { // This method checks if a cell is occupied
    if (r >= 0 && r < board.length && c >= 0 &&
        c < board[r].length && board[r][c] == 0) return false;
    else return true;
  }

  public void updateScoreForPlayer(int player) { // This method updates the score
    switch (player) {
      case 1:
        player1Score++;
        break;
      case 2:
        player2Score++;
        break;
    }
  }

  public boolean checkForScratch() {
    int count = 0;

    for (int r = 0; r < boardSize; r++) {
      for (int c = 0; c < boardSize; c++) {
        if (checkIfOwned(r, c)) count++;
      }
    }

    if (count == Math.pow(boardSize, 2)) return true;

    return false;
  }

  public boolean checkForWin() { // This method checks if there is a winner

    // Checking horizontal matches
    for (int r = 0; r < board.length; r++) {
      int valueToCheck = board[r][0];
      if (valueToCheck != 0) {
        for (int c = 1; c < board.length; c++) {
          if (board[r][c] != valueToCheck) {
            break;
          } else if (c == boardSize-1) {
            winner = valueToCheck;
            updateScoreForPlayer(winner);
            return true;
          }
        }
      }
    }

    // Checking vertically
    for (int c = 0; c < board.length; c++) {
      int valueToCheck = board[0][c];
      if (valueToCheck != 0) {
        for (int r = 1; r < board.length; r++) {
          if (board[r][c] != valueToCheck) break;
          else if (r == boardSize-1) {
            winner = valueToCheck;
            updateScoreForPlayer(winner);
            return true;
          }
        }
      }
    }

    // Check diagonal (left to right)
    if (board[0][0] != 0) {
      for (int i = 1; i < board.length; i++) {
        if (board[i][i] != board[0][0]) break;
        else if (i == boardSize-1) {
          winner = board[0][0];
          updateScoreForPlayer(winner);
          return true;
        }
      }
    }

    // Checking diagonal (right to left)
    if (board[0][boardSize-1] != 0) {
      for (int r = 0; r < board.length; r++) {
        int c = (board.length-1) - r;
        if (board[r][c] != board[0][boardSize-1]) break;
        else if (r == boardSize-1) {
          winner = board[0][boardSize-1];
          updateScoreForPlayer(winner);
          return true;
        }
      }
    }

    return false;
  }

  public void visualizeBoardOnCommandLine() {
    toPrintOnCommandLine = true; // set value to true if method is called
  }

  public void printBoard() { // This method prints the board on the command line
    for (int r = 0; r < board.length; r++) {
      for (int c = 0; c < board[r].length; c++) {
        System.out.printf("%5d", board[r][c]);
      }
      System.out.println();
    }
    System.out.println();
  }
}
