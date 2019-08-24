
// tracks global input
public class Arrows extends Controller {

  @Override
  public GeoVector getDir() {
    up = input.getKeys().contains(38);
    down = input.getKeys().contains(40);
    left = input.getKeys().contains(37);
    right = input.getKeys().contains(39);
    return super.getDir();
  }

  public Arrows(Input input) {
    super(input);
  }
}
