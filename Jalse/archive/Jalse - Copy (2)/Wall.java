import java.awt.Rectangle;
import java.awt.Color;

public class Wall {

  public Rectangle collider;
  public Vector pos;
  public Color color;

  public Wall(Vector position, Vector size) {
    collider = new Rectangle((int)(position.x - size.x / 2), (int)(position.y - size.y / 2), (int)size.x, (int)size.y);
    pos = position;
    color = Color.GRAY;
  }
}
