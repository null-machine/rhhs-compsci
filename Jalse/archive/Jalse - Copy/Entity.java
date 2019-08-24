import java.awt.Rectangle;
import java.awt.Color;

public class Wall {

  // physical properties are public because literally everything uses them
  public Rectangle collider;
  public Vector pos; // center of box
  public Color color;

  public Entity(Vector position, Vector size) {
    collider = new Rectangle((int)(position.x - size.x / 2), (int)(position.y - size.y / 2), (int)size.x, (int)size.y);
    pos = position;
    color = Color.GRAY;
  }
}
