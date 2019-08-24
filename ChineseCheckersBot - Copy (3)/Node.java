/* Node.java
 * @author Glen Wang
 * 04/20/19
 */

import java.util.ArrayList;
import java.awt.Point;

class Node {
  public Move move;
  public double value;
  //public int frequency;
  public ArrayList<Node> children;

  public Node(Move move, double value) {
    this.move = move;
    this.value = value;
	}
}
