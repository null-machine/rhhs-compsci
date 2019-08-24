import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class BezierMap {

  public Bezier track;

  public BezierMap(double length, int pivotCount) {
    GeoVector start = GeoVector.zero;
    GeoVector end = new GeoVector(length, 0.0);

    track = createPath(start, end, pivotCount);

    Random rnd = new Random();
    track.rotate(rnd.nextDouble() * Math.PI * 2);
  }

  private Bezier createPath(GeoVector start, GeoVector end, int pivotCount) {
    GeoVector[] pivots = new GeoVector[pivotCount];

    pivots[0] = start;
    pivots[pivotCount - 1] = end;
    GeoVector center = start.lerp(end, 0.5);
    double halfLength = (start.sub(center)).mag();

    for (int i = 1; i < pivotCount; ++i) {
      pivots[i] = center.add(center.randInRadius(halfLength));
    }

    Arrays.sort(pivots, new PivotSorter());

    return new Bezier(pivots);
  }

  private class PivotSorter implements Comparator<GeoVector> {
    public int compare(GeoVector one, GeoVector two) {
      // return (one.x).compareTo(two.x);
      return (int)(one.x - two.x);
    }
  }
}
