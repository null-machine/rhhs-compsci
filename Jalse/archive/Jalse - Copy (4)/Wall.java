import java.awt.Rectangle;
import java.awt.Color;

/**
* A colored rectangle expressed with position and size,
* used primarily for collision detection.
* @author Glen Wang
*/
public class Wall {

  public Rectangle collider;
  public GeoVector pos; // TODO making these private....
  public GeoVector getPos() { return pos; }
  public void setPos(GeoVector pos) { this.pos = pos; }

  public Color color;
  public void setColor(Color color) { this.color = color; }

  protected void updateCollider(double size) { // TODO storing size as a property?
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
    return collider.hashCode() + color.hashCode();
  }

  /**
  * Constructs a wall with a position, size and color.
  * @param pos The position of the center of the wall.
  * @param size The size of the wall in pixels.
  * @param color TODO TODO TODO
  */
  public Wall(GeoVector pos, double size) { // TODO color in rectangle constructor
    this.pos = pos;
    collider = new Rectangle();
    updateCollider(size);
    color = new Color(255, 0, 144);
  }
}
