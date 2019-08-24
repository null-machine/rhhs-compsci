
// tracks global input
public class WASD extends Controller {

  @Override
  public GeoVector getDir() {
    up = keys.contains(87);
    down = keys.contains(83);
    left = keys.contains(65);
    right = keys.contains(68);
    return super.getDir();
  }
}
