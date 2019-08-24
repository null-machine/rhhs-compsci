
// tracks global input
public class WASD extends Controller {

  @Override
  public GeoVector getDir() {
    // up = input.getKeys().contains(87);
    // down = input.getKeys().contains(83);
    // left = input.getKeys().contains(65);
    // right = input.getKeys().contains(68);

    // up = input.getKeys().contains(80);
    // down = input.getKeys().contains(85);
    // left = input.getKeys().contains(69);
    // right = input.getKeys().contains(73);

    up = input.getKeys().contains(44);
    down = input.getKeys().contains(79);
    left = input.getKeys().contains(65);
    right = input.getKeys().contains(69);

    return super.getDir();
  }

  public WASD(Input input) {
    super(input);
  }
}
