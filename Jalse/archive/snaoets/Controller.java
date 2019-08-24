
// tracks global input
abstract public class Controller extends Input {

  protected boolean up;
  protected boolean down;
  protected boolean left;
  protected boolean right;

  public GeoVector getDir() {

    GeoVector dir = GeoVector.zero;
    if (up) {
      dir = dir.add(new GeoVector(0, -1));
    }
    if (down) {
      dir = dir.add(new GeoVector(0, 1));
    }
    if (left) {
      dir = dir.add(new GeoVector(-1, 0));
    }
    if (right) {
      dir = dir.add(new GeoVector(1, 0));
    }

    return dir;
  }
}
