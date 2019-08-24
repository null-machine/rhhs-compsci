import java.awt.Rectangle;
import java.awt.Color;

public class Wall {

  public Rectangle collider;
  public GeoVector pos;
  public GeoVector getPos() { return pos; }
  public void setPos(GeoVector pos) { this.pos = pos; }

  public Color color;
  public void setColor(Color color) { this.color = color; }

  protected void updateCollider(double size) {
    double halfSize = size / 2.0;
    collider.x = (int)(pos.x - halfSize);
    collider.y = (int)(pos.y - halfSize);
    collider.height = (int)size;
    collider.width = (int)size;

  }

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

  public Wall(GeoVector pos, double size) { // add color later
    this.pos = pos;
    collider = new Rectangle();
    updateCollider(size);
    color = Color.GRAY;
  }
}
