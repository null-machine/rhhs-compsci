/* Move.java
 * @author Glen Wang
 * 04/20/19
 */

import java.util.ArrayList;
import java.awt.Point;

class Move implements Comparable<Move> {

  public Point piece;
  public Point target;
  public ArrayList<Point> jumpPath;

  public Move(Point piece, Point target) {
    this.piece = piece;
    this.target = target;
  }

  public int getScore() {
    // TODO
    // return steps til corner tip of piece position - steps til corner tip of target position
    // eric's working on this
    return 0;
  }

  public int compareTo(Move other) {
    return this.getScore() - other.getScore();
  }
}
