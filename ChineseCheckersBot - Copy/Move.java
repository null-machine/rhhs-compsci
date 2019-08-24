/* BoardState.java
 * @author Glen Wang
 * 04/20/19
 */

import java.awt.Point;

class Move implements Comparable<Move> {

  public Point piece;
  public Point target;
  public ArrayList<Point> jumpPath; // accessed directly

  public Move(Point piece, Point target) {
    this.piece = piece;
    this.target = target;
  }

  public Move(Point piece) {
    this.piece = piece;
  }

  public int getScore() {
    // return num moves to get to opposite tip for piece - moves to get to opposite tip for target
    return 0;
  }

  @override
  public int compareTo(Move other) {
   return Move.getScore() - other.getScore();
  }
}
