import java.awt.Color;

public class Control extends Model {

  public Control() {
    speed = 0.7;
    control = 0.2;
    color = Color.BLUE;
    size = 64;
    fragility = 30;
    calcDrag();
    stats[0] = "*";
    stats[1] = "****";
    stats[2] = "***";
  }
}
