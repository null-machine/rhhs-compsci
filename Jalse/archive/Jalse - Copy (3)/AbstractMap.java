import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

/**
 * AbstractMap
 * An abstract representation of the race track.
 * @author Glen Wang
 **/
public class AbstractMap {

  public double width;
  public Bezier track;
  public PathNode start;

  public AbstractMap(double width, double length, int pivotCount) {
    this.width = width;
    GeoVector start = GeoVector.zero;
    GeoVector end = new GeoVector(length, 0.0);

    track = createPath(start, end, pivotCount);
    Random rnd = new Random();
    track.rotate(rnd.nextDouble() * Math.PI * 2);

    this.start = graphBezier(track, width);
  }

  private PathNode graphBezier(Bezier bez, double width) {
    GeoVector startPos = bez.eval(0.0);
    GeoVector endPos = bez.eval(1.0);
    double dist = startPos.dist(endPos);

    PathNode start = new PathNode(startPos, null, null);
    PathNode end = new PathNode(endPos, start, null);
    start.next = end;

    graphBezierRec(bez, width, start, end, 0.0, 1.0);

    return start;
  }

  private void graphBezierRec(Bezier bez, double width, PathNode prev, PathNode next, double prevPercent, double nextPercent) {
    double mid = (prevPercent + nextPercent) / 2.0;
    GeoVector midPos = bez.eval(mid);
    double prevDist = midPos.dist(prev.pos);
    double nextDist = midPos.dist(next.pos);

    PathNode pn = new PathNode(midPos, prev, next);
    prev.next = pn;
    next.prev = pn;

    if (prevDist > width) {
      graphBezierRec(bez, width, prev, pn, prevPercent, mid);
    }
    if (nextDist > width) {
      graphBezierRec(bez, width, pn, next, mid, nextPercent);
    }
  }

  private Bezier createPath(GeoVector start, GeoVector end, int pivotCount) {
    GeoVector[] pivots = new GeoVector[pivotCount];

    pivots[0] = start;
    pivots[pivotCount - 1] = end;
    GeoVector center = start.lerp(end, 0.5);
    double radius = (start.sub(center)).mag();

    for (int i = 1; i < pivotCount - 1; ++i) {
      pivots[i] = center.randInRadius(radius);
    }
    Arrays.sort(pivots, 1, pivotCount - 2, new PivotSorter());

    return new Bezier(pivots);
  }

  private class PivotSorter implements Comparator<GeoVector> {
    public int compare(GeoVector one, GeoVector two) {
      // return (one.x).compareTo(two.x);
      return (int)(one.x - two.x);
    }
  }
}
