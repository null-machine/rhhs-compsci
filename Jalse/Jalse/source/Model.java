import java.awt.Color;
import java.util.Random;

/**
* A data container for racer statistics.
* @author Glen Wang
* @version 1.0
* @date June 11, 2019
*/
abstract public class Model {

  public double speed; // maximum speed
  public double control; // capability to accelerate
  public double drag; // linear air resistance
  public int fragility; // how long it takes to recover

  public int size; // size of its hitbox
  public Color color; // color of its hitbox

  public String[] stats = new String[3];
  // speed, accel, repair

  private Random rand = new Random();

  protected void calcDrag() {
    drag = speed / (speed + control);
  }

  protected double randMod(double mag) {
    return rand.nextDouble() * mag - mag;
  }

  /**
  * Plays this racer's sound effect for when
  * this racer collides with a wall.
  */
  abstract public void soundCollide();

  /**
  * Plays this racer's sound effect for when
  * this racer reaches the finish.
  */
  abstract public void soundWin();

  /**
  * Plays this racer's sound effect for when
  * this racer is selected from the main menu.
  */
  abstract public void soundSelect();
}
