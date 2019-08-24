/**
* A controller that interfaces input from arrow keys.
* @author Glen Wang
* @version 1.0
* @date June 11, 2019
*/
public class Arrows extends Controller {

  @Override
  public GeoVector getDir() {
    up = input.getKeys().contains(38);
    down = input.getKeys().contains(40);
    left = input.getKeys().contains(37);
    right = input.getKeys().contains(39);
    return super.getDir();
  }

  /**
  * Constructs controller that takes input from arrow keys.
  * @param input The current global input class.
  */
  public Arrows(Input input) {
    super(input);
  }
}
