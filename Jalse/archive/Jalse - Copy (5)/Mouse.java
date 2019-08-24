
// tracks global input
public class Mouse extends Controller {

  @Override
  public GeoVector getDir() {
    return input.getMouse();
  }

  /**
  * Constructs a mouse controller that takes input.
  * @param input The current global input class.
  */
  public Mouse(Input input) {
    super(input);
  }
}
