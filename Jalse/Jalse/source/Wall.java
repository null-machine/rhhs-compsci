import java.awt.Rectangle;
import java.awt.Color;

/**
* A colored rectangle expressed with position and size,
* used primarily for collision detection.
* @author Glen Wang
* @version 1.0
* @date June 11, 2019
*/
public class Wall {

  private GeoVector pos;

  /**
  * Gets the position of this wall.
  * @return The position of the center of this wall's collider.
  */
  public GeoVector getPos() {
    return pos;
  }

  /**
  * Sets the position of this wall.
  * @param pos The position of the center of this wall's collider.
  */
  public void setPos(GeoVector pos) {
    this.pos = pos;
  }

  private Rectangle collider;

  /**
  * Gets the collider of this wall.
  * @return A rectangle representing this wall's collider.
  */
  public Rectangle getCollider() {
    return this.collider;
  }

  protected void updateCollider(double size) { // behaves like setter
    double halfSize = size / 2.0;
    collider.x = (int)(pos.x - halfSize);
    collider.y = (int)(pos.y - halfSize);
    collider.height = (int)size;
    collider.width = (int)size;
  }


  private Color color;

  /**
  * Gets the color of this wall.
  * @return The color of this wall.
  */
  public Color getColor() {
    return this.color;
  }

  /**
  * Sets the color of this wall.
  * @param color The color of this wall.
  */
  public void setColor(Color color) {
    this.color = color;
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
  * Constructs a wall with position, size and color.
  * @param pos The position of the center of the wall.
  * @param size The size of the wall in pixels.
  * @param color The color of the wall.
  */
  public Wall(GeoVector pos, double size, Color color) {
    this.pos = pos;
    this.color = color;
    collider = new Rectangle();
    updateCollider(size);
  }

  /**
  * Constructs a black wall with position and size.
  * @param pos The position of the center of the wall.
  * @param size The size of the wall in pixels.
  */
  public Wall(GeoVector pos, double size) {
    this.pos = pos;
    color = Color.BLACK;
    collider = new Rectangle();
    updateCollider(size);
  }
}
