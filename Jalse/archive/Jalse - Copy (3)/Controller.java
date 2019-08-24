
// tracks global input
abstract public class Controller {

  protected Input input;

  protected boolean up;
  protected boolean down;
  protected boolean left;
  protected boolean right;

  public GeoVector getDir() {

    GeoVector dir = GeoVector.zero;
    if (up) {
      dir = dir.add(GeoVector.up);
    }
    if (down) {
      dir = dir.add(GeoVector.down);
    }
    if (left) {
      dir = dir.add(GeoVector.left);
    }
    if (right) {
      dir = dir.add(GeoVector.right);
    }
    dir = dir.normalize();

    return dir;
  }

  public Controller(Input input) {
    this.input = input;
  }
}
