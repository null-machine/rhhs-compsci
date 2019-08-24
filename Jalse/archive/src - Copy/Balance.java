import java.awt.Color;

/**
* A model for a well-rounded racer.
* @author Glen Wang
*/
public class Balance extends Model {

  /**
  * Constructs a balanced racer model.
  */
  public Balance() {
    speed = 1.0;
    control = 0.04;
    color = new Color(0, 248, 104);
    size = 56;
    fragility = 700;
    calcDrag();
    stats[0] = "***";
    stats[1] = "***";
    stats[2] = "**";
  }
}
