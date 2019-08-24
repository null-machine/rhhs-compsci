import java.awt.Color;

/**
* A data container for a well-rounded racer.
* @author Glen Wang
*/
public class Balance extends Model {

  /**
  * Constructs a balanced racer model.
  */
  public Balance() {
    speed = 1.0;
    control = 0.04;
    color = Color.YELLOW;
    size = 56;
    fragility = 700;
    calcDrag();
    stats[0] = "***";
    stats[1] = "***";
    stats[2] = "**";
  }
}
