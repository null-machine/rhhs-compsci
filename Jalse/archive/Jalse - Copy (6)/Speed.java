import java.awt.Color;

/**
* A data container for a fast but challenging racer.
* @author Glen Wang
*/
public class Speed extends Model {

  /**
  * Constructs a speedy racer model.
  */
  public Speed() {
    speed = 1.5;
    control = 0.03;
    color = Color.RED;
    size = 48;
    fragility = 1000;
    calcDrag();
    stats[0] = "*****";
    stats[1] = "*";
    stats[2] = "*";
  }
}
