/**
* A controller that interfaces WASD input.
* @author Glen Wang
* @version 1.0
* @date June 11, 2019
*/
public class WASD extends Controller {

  @Override
  public GeoVector getDir() {
    // QWERTY (WASD)
    up = input.getKeys().contains(87);
    down = input.getKeys().contains(83);
    left = input.getKeys().contains(65);
    right = input.getKeys().contains(68);

    // DVORAK (,AOE)
    // up = input.getKeys().contains(44);
    // down = input.getKeys().contains(79);
    // left = input.getKeys().contains(65);
    // right = input.getKeys().contains(69);

    return super.getDir();
  }

  /**
  * Constructs a controller that takes input from WASD keys.
  * @param input The current global input class.
  */
  public WASD(Input input) {
    super(input);
  }
}
