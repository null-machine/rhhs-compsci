import java.awt.Rectangle;
import java.awt.Color;

public class Player extends Wall implements Comparable<Player> {

  private final int moveLeft = 37;
  private final int moveUp = 38;
  private final int moveRight = 39;
  private final int moveDown = 40;

  private double speed;
  private double control;

  private double drag;
  private Vector vel;
  private Vector acc;

  public void update(double deltaTime) {
    acc = Vector.zero;
    if (Input.keys.contains(moveLeft)) {
      acc = acc.add(new Vector(-1, 0));
    }
    if(Input.keys.contains(moveUp)) {
      acc = acc.add(new Vector(0, -1));
    }
    if(Input.keys.contains(moveRight)) {
      acc = acc.add(new Vector(1, 0));
    }
    if(Input.keys.contains(moveDown)) {
      acc = acc.add(new Vector(0, 1));
    }

    // acc = acc.normalize();
    acc = acc.mult(control);
    vel = vel.add(acc);
    vel = vel.mult(drag);
    pos = pos.add(vel.mult(deltaTime));

    collider.x = (int)pos.x;
    collider.y = (int)pos.y;
  }

  public Player(Vector position, Vector size, double speed, double control) {
    super(position, size);
    acc = Vector.zero;
    vel = Vector.zero;
    this.speed = speed;
    this.control = control;
    drag = speed / (speed + control);
  }

  public int compareTo(Player player) {
    return 0;
  }
}
