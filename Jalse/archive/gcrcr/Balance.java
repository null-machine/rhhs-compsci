import java.awt.Color;

public class Balance extends Model {

  public Balance() {
    speed = 1.0;
    control = 0.05;
    color = Color.YELLOW;
    size = 64;
    fragility = 60;
    calcDrag();
    stats[0] = "***";
    stats[1] = "***";
    stats[2] = "**";
  }
}
