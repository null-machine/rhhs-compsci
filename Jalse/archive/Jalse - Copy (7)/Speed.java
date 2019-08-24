import java.awt.Color;

/**
* A model for a fast but challenging racer.
* @author Glen Wang
*/
public class Speed extends Model {

  /**
  * Constructs a speedy racer model.
  */
  public Speed() {
    speed = 1.4;
    control = 0.03;
    color = new Color(249, 0, 96);
    size = 48;
    fragility = 1000;
    calcDrag();
    stats[0] = "*****";
    stats[1] = "*";
    stats[2] = "*";
  }
}
