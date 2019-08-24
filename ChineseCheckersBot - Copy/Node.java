/* Node.java
 * @author Glen Wang
 * 04/20/19
 */

import java.util.ArrayList;
import java.awt.Point;

class Node {
  public Move move;
  public double value;
  public int frequency;
  //public ArrayList<Node> children;
  public int childCount;
  public int root; // index in node array

  //public Node(int visits, double value, Move last_move, Players player)
  public Node(Move move, double value, int frequency) {
    this.move = move;
    this.value = value;
    this.frequency = frequency;
	}
}
