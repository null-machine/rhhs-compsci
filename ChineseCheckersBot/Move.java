/* Move.java
 * @author Glen Wang
 * 04/20/19
 */

import java.util.ArrayList;
import java.awt.Point;

class Move {

  public Point piece;
  public Point target;
  public ArrayList<Point> jumpPath;

  public Move(Point piece, Point target) {
    this.piece = piece;
    this.target = target;
  }
}
