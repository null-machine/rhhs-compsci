/**
* A controller that interfaces input from
* the global input class.
* @author Glen Wang
* @version 1.0
* @date June 11, 2019
*/
abstract public class Controller {

  protected Input input;

  protected boolean up;
  protected boolean down;
  protected boolean left;
  protected boolean right;

  protected boolean disabled;

  /**
  * Makes this controller wait for all input to be
  * lifted before recieving input again.
  */
  public void awaitLift() {
    disabled = (up || down || left || right);
  }

  /**
  * Gets the direction from this controller's input.
  * @return A vector representing this controller's input.
  */
  public GeoVector getDir() {
    if (disabled) {
      awaitLift();
      return GeoVector.zero;
    }
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

  /**
  * Constructs a controller that takes input.
  * @param input The current global input class.
  */
  public Controller(Input input) {
    this.input = input;
  }
}
