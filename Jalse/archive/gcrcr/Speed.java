import java.awt.Color;

public class Speed extends Model {

  public Speed() {
    speed = 1.5;
    control = 0.04;
    color = Color.RED;
    size = 64;
    fragility = 60;
    calcDrag();
    stats[0] = "*****";
    stats[1] = "*";
    stats[2] = "*";
  }
}
