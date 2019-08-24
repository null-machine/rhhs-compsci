
public class PathNode {
  public GeoVector pos;
  public PathNode prev, next;

  public PathNode(GeoVector pos, PathNode prev, PathNode next) {
    this.pos = pos;
    this.prev = prev;
    this.next = next;
  }
}
