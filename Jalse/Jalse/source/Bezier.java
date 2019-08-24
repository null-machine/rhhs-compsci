import java.lang.System;

/**
* A dynamically evaluated, nth-order bezier.
* @author Glen Wang
* @version 1.0
* @date June 11, 2019
*/
public class Bezier {

  private GeoVector[] pivots;

  /**
  * Recursively evaluates the bezier at a percent.
  * Lower percents return points on the bezier closer to
  * the starting pivot, and vice versa.
  * @param percent The percent to evaluate.
  * @return The location on the bezier at that percent.
  */
  public GeoVector eval(double percent) {
    return evalRec(percent, pivots);
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

  /**
  * Rotates this over its starting pivot by an angle.
  * @param ang The angle in radians.
  */
  public void rotate(double ang) {
    for (int i = 1; i < pivots.length; ++i) {
      pivots[i] = pivots[i].sub(pivots[0]);
      pivots[i] = pivots[i].rotate(ang);
      pivots[i] = pivots[i].add(pivots[0]);
    }
  }

  /**
  * Constructs a new bezier from pivots.
  * @param pivots The pivots of this bezier.
  */
  public Bezier(GeoVector[] pivots) {
    this.pivots = pivots;
  }
}
