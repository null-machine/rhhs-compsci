import java.awt.Color;

/**
* A data container for racer statistics.
* @author Glen Wang
*/
abstract public class Model {

  public double speed; // maximum speed
  public double control; // capability to accelerate
  public double drag; // linear air resistance
  public int fragility; // how long it takes to recover

  public int size; // size of its hitbox
  public Color color; // color of its hitbox

  public String[] stats = new String[3];
  // speed, accel, repair

  protected void calcDrag() {
    drag = speed / (speed + control);
  }
}
