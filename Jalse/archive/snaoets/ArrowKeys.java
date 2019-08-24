
// tracks global input
public class ArrowKeys extends Controller {

  @Override
  public GeoVector getDir() {
    up = keys.contains(38);
    down = keys.contains(40);
    left = keys.contains(37);
    right = keys.contains(39);
    return super.getDir();
  }
}
