
// tracks global input
public class Mouse extends Controller {

  @Override
  public GeoVector getDir() {
    return input.getMouse();
  }

  public Mouse(Input input) {
    super(input);
  }
}
