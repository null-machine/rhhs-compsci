import java.awt.Color;

public class Speed extends Model {

  public Speed() {
    speed = 2.0;
    control = 0.03;
    color = Color.RED;
    size = 64;
    fragility = 40;
    calcDrag();
  }
}
