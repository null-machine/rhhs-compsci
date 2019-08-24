/* CCAlgo.java
 * @author Glen Wang
 * 04/20/19
 */

import java.util.ArrayList;
import java.awt.Point;

class CCAlgo {

  private int[][] board;

  //private int[][] adjacencies = new int[2][6];
  private ArrayList<Point> visited = new ArrayList<Point>();
  private ArrayList<Point> bestMove = new ArrayList<Point>();
  private double bestDist;

  private Point[] pieces = new Point[10];
  private Point goal;

  private double getProgress(Point piece) {
    // return distance from piece to deepest corner of goal triangle
    // maybe find distance in normal steps instead but i'll do that later
    return Math.sqrt(Math.pow(goal.x - piece.x, 2) + Math.pow(goal.y - piece.y, 2));
  }

  private boolean jumpable(Point piece, int dX, int dY) {
    Point adjacent = new Point(piece.x + dX / 2, piece.y + dY / 2);
    if (
      (board[adjacent.x][adjacent.y] != 0) &&
      (board[piece.x + dX][piece.y + dY] == 0) &&
      (!visited.contains(new Point(dX, dY)))
    ) {
      return true;
    } else {
      return false;
    }
  }

  private void greedyPath(Point piece) {
    visited.add(piece);
    double dist = getProgress(piece);

    if (dist < bestDist) {
      bestDist = dist;
      bestMove = visited;
    }

    if (jumpable(piece, 2, 0)) {
      greedyPath(new Point(piece.x + 2, piece.y)); //dont forget to update board
    }
    if (jumpable(piece, -2, 0)) {
      greedyPath(new Point(piece.x - 2, piece.y));
    }
    if (jumpable(piece, -2, -2)) {
      greedyPath(new Point(piece.x - 2, piece.y - 2));
    }
    if (jumpable(piece, 2, 2)) {
      greedyPath(new Point(piece.x + 2, piece.y + 2));
    }
    if (jumpable(piece, 0, 2)) {
      greedyPath(new Point(piece.x, piece.y + 2));
    }
    if (jumpable(piece, 0, - 2)) {
      greedyPath(new Point(piece.x, piece.y - 2));
    }
  }

  private String getMove() {
    for (Point piece : pieces) {
      greedyPath(piece);
    }
    String res = "";
    for (Point pos : bestMove) {
        res += pos.x + " " + pos.y + " ";
    }
    return res;
  }
  // for dynamic code, store x arrays of bestmove, and calculate x turns into the future
  // use the one with the greatest sum of progression
}
