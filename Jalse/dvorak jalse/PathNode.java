/**
* A node used for bot navigation.
* @author Glen Wang
* @version 1.0
* @date June 11, 2019
*/
public class PathNode {

  // public properties because this is a collection
  public GeoVector pos;
  public PathNode prev, next;

  /**
	* Constructs a node with a position that is linked to specified nodes.
	* @param pos - The position of the node.
  * @param prev - The node ahead of this one.
  * @param next - The node behind this one.
	*/
  public PathNode(GeoVector pos, PathNode prev, PathNode next) {
    this.pos = pos;
    this.prev = prev;
    this.next = next;
  }
}
