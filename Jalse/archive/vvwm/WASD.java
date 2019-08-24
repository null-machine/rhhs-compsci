
// tracks global input
public class WASD extends Controller {

  @Override
  public GeoVector getDir() {
    up = input.getKeys().contains(87);
    down = input.getKeys().contains(83);
    left = input.getKeys().contains(65);
    right = input.getKeys().contains(68);
    return super.getDir();
  }

  public WASD(Input input) {
    super(input);
  }
}
