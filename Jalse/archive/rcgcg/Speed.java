import java.awt.Color;

public class Speed extends Model {

  public Speed() {
    speed = 1.5;
    control = 0.03;
    color = Color.RED;
    size = 64;
    fragility = 50;
    calcDrag();
  }
}
