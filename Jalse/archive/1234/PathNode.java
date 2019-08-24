
public class PathNode {
  public GeoVector pos;
  public PathNode prev, next;
  public double prevDist, nextDist;

  public PathNode(GeoVector pos, PathNode prev, PathNode next, double prevDist, double nextDist) {
    this.pos = pos;
    this.prev = prev;
    this.next = next;
    this.prevDist = prevDist;
    this.nextDist = nextDist;
  }
}
