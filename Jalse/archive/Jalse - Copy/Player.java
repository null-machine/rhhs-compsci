import java.awt.Rectangle;
import java.awt.Color;

// a player
public class Player extends Entity implements Comparable<Player> {

  private double speed;
  private double control;

  private double drag;
  private Vector vel;
  private Vector acc;

  public void update(double deltaTime) {
    acc = Vector.zero;
    if (Input.keys.contains(37)) { // left
      acc = acc.add(new Vector(-1, 0));
    }
    if(Input.keys.contains(38)) { // up
      acc = acc.add(new Vector(0, -1));
    }
    if(Input.keys.contains(39)) { // right
      acc = acc.add(new Vector(1, 0));
    }
    if(Input.keys.contains(40)) { // down
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

  public int compareTo(Player Player) {
    return 0;
  }
}
