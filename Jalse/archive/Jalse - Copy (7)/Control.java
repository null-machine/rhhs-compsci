import java.awt.Color;

/**
* A model for a safe but slow racer.
* @author Glen Wang
*/
public class Control extends Model {

  /**
  * Constructs an easy-to-control racer model.
  */
  public Control() {
    speed = 0.8;
    control = 0.06;
    color = new Color(158, 0, 248);
    size = 64;
    fragility = 200;
    calcDrag();
    stats[0] = "*";
    stats[1] = "****";
    stats[2] = "***";
  }
}
