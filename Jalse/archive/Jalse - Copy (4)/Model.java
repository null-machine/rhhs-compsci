import java.awt.Color;

abstract public class Model {

  public double speed;
  public double control;
  public double drag;
  public int fragility;

  public int size;
  public Color color;

  public String[] stats = new String[3];
  // speed, accel, repair

  protected void calcDrag() {
    drag = speed / (speed + control);
  }
}
