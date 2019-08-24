/* State.java
 * @author Glen Wang
 * 04/20/19
 */

import java.util.ArrayList;
import java.util.Collections;
import java.awt.Point;

class State {

  public int[][] board = new int[25][17];
  public int player = 1;

  public State(int[][] board) {
    System.arraycopy(board, 0, this.board, 0, board.length);
  }

  public State(State oState) { // cloning constructor bc object.clone is bad
    System.arraycopy(oState.board, 0, this.board, 0, oState.board.length);
  }

  public void apply(Move move) {
    int target = board[move.target.x][move.target.y];
    board[move.target.x][move.target.y] = board[move.piece.x][move.piece.y];
    board[move.piece.x][move.piece.y] = target;
    return;
  }

  public double eval() {
    return 0.0;
  }

  // following three methods uses reference as return because in this case
  // it's more readable and avoids duplicate references

  // fills moves arraylist with a sorted list of moves, best ones first
  public void getMoves(ArrayList<Move> moves) {
    moves.clear();
    Point piece;
    for (int i = 0; i < board.length; ++i) {
      for (int j = 0; i < board[0].length; ++j) {
        if (board[i][j] == player) {
          piece = new Point(i, j);
          getSteps(moves, piece);
          getJumps(moves, piece);
        }
      }
    }
    Collections.sort(moves, Collections.reverseOrder()); // absolute power move
    // randomization is done by bot
  }

  // TODO only getjumps and steps from pieces that moved?

  private void getSteps(ArrayList<Move> moves, Point piece) {
    // add all possible moves to moves array
    // add jump path to each move
    // TODO
    return;
  }

  private void getJumps(ArrayList<Move> moves, Point piece) {
    // TODO
    return;
  }

  private boolean checkWin() {
    // TODO
    return false;
  }

  public int getDist(Point piece){
    boolean found = false;
    int currX = piece.x;
    int currY = piece.y;
    int distance = 0;
    while(!found){
      if(currX == 24 && currY == 12){
        found = true;
      }
      if(!found){
        //figure if on left or right of midline (y = 2x or currY = currX/2)
        //move down
        //else move towards midline
        if((currX/2)<currY){//right of midline
          if(currX + 1 < board.length && board[currX+1][currY]!=-1){
            currX++;
            distance++;
          } else{
            //currX++;
            currY--;
            distance++;
          }
        } else {
          if(currX+1<board.length && currY+1<board[currX].length && board[currX+1][currY+1]!=1){
            currX++;
            currY++;
            distance++;
          } else {
            currY++;
            distance++;
          }
        }
      }
    }
    return distance;
  }
}
