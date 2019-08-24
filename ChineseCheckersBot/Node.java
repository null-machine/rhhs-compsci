/* Node.java
 * @author Glen Wang
 * 04/20/19
 */

import java.util.ArrayList;
import java.awt.Point;

class Node implements Comparable<Node> {
  public Move move;
  public double value;
  //public int frequency;
  public ArrayList<Node> children;

  public Node(Move move, double value) {
    this.move = move;
    this.value = value;
	}

  public int compareTo(Node other) {
    return (int)Math.ceil(this.value - other.value);
  }
}
