/* BoardState.java
 * @author Glen Wang
 * 04/20/19
 */

import java.util.ArrayList;
import java.awt.Point;

class State {

  public int[][] board; // point array?

  public State(cBoard) {
    board = new int[25][17];
    System.arraycopy(cBoard, 0, board, 0, cBoard.length);
  }

  public int goalDist(Point p) {
    // return steps til corner tip
    return 20;
  }

  public void apply(Move move) {
    int target = board[move.target.x][move.target.y];
    board[move.target.x][move.target.y] = board[move.piece.x][move.piece.y];
    board[move.piece.x][move.piece.y] = target;
    return;
  }

  private void getSteps(ArrayList<Move> moves, Point piece) {
    moves.add(new Move());
  }

  public void getMoves(ArrayList<Move> moves) { // uses reference as return
    moves.clear;
    Point piece;
    for (int i = 0; i < board.length; ++i) {
      for (int j = 0; i < board[0].length; ++j) {
        if (board[i][j] == 1) { // if current player
          piece = new Point(i, j);
          getSteps(moves, piece);
          getJumps(moves, piece);
        }
      }
    }
    Collections.sort(moves); // absolute power move
    // add random switching of ties
  }

  private List<Point> visited;
  private void getJumps(ArrayList<Move> moves, Point piece) {
    // add brute here
    // exclude backwards
    moves.add(new Move());
  }

}
