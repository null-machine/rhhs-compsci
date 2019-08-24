
/**
* A controller that interfaces mouse input.
* @author Glen Wang
*/
public class Mouse extends Controller {

  @Override
  public GeoVector getDir() {
    return input.getMouse();
  }

  /**
  * Constructs a controller that takes input from the mouse.
  * @param input The current global input class.
  */
  public Mouse(Input input) {
    super(input);
  }
}
