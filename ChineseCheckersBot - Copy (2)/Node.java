/* Node.java
 * @author Glen Wang
 * 04/20/19
 */

import java.util.ArrayList;
import java.awt.Point;

class Node {
  public Move move;
  public double value; // not int bc propagation affects it too
  public int frequency;
  //public ArrayList<Node> children;

  // indices in tree array
  public int childStart;
  public int childCount;
  public int root;

  public Node(Move move, double value, int frequency) {
    this.move = move;
    this.value = value;
    this.frequency = frequency;
	}
}
