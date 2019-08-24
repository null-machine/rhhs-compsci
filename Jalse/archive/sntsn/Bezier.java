import java.lang.System;

public class Bezier {

  private GeoVector[] pivots;
  public GeoVector start;
  public GeoVector end;

  public Bezier(GeoVector[] pivots) {
    this.pivots = pivots;
    start = pivots[0];
    end = pivots[pivots.length - 1];
  }

  public GeoVector eval(double percent) {
    return evalRec(percent, pivots);
  }

  // rotates bezier over starting point
  public void rotate(double ang) {
    for (int i = 1; i < pivots.length; ++i) {
      pivots[i] = pivots[i].sub(pivots[0]);
      pivots[i] = pivots[i].rotate(ang);
      pivots[i] = pivots[i].add(pivots[0]);
    }
  }

  private GeoVector evalRec(double percent, GeoVector[] pivots) {
    if (pivots.length == 1) {
      return pivots[0];
    }

    GeoVector[] tempPivots = new GeoVector[pivots.length - 1];
    System.arraycopy(pivots, 0, tempPivots, 0, pivots.length - 1);
    GeoVector start = evalRec(percent, tempPivots);
    System.arraycopy(pivots, 1, tempPivots, 0, pivots.length - 1);
    GeoVector end = evalRec(percent, tempPivots);

    return start.lerp(end, percent);
  }
}
