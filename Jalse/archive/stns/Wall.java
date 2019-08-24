import java.awt.Rectangle;
import java.awt.Color;

class Wall {

  public Rectangle collider;
  public GeoVector pos;
  public Color color;

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof Wall)) {
      return false;
    }
    Wall other = (Wall)o;
    return collider.equals(other.collider);
  }

  @Override
  public int hashCode() {
    return collider.hashCode();
  }

  public Wall(GeoVector pos, int size) { // add color later
    this.pos = pos;
    double halfSize = (double)(size / 2);
    collider = new Rectangle((int)(pos.x - halfSize), (int)(pos.y - halfSize), size, size);
    color = Color.GRAY;
  }
}
