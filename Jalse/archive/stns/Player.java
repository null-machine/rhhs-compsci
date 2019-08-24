import java.awt.Rectangle;
import java.awt.Color;

class Player extends Wall implements Comparable<Player> {

  private static final int playerSize = 64;

  private final int moveLeft = 37;
  private final int moveUp = 38;
  private final int moveRight = 39;
  private final int moveDown = 40;

  private double speed;
  private double control;

  private double drag;
  public GeoVector vel;
  private GeoVector acc;

  public int damage;
  public boolean collided;

  private void move(double deltaTime) {
    acc = GeoVector.zero;
    if (Input.keys.contains(moveLeft)) {
      acc = acc.add(new GeoVector(-1, 0));
    }
    if(Input.keys.contains(moveUp)) {
      acc = acc.add(new GeoVector(0, -1));
    }
    if(Input.keys.contains(moveRight)) {
      acc = acc.add(new GeoVector(1, 0));
    }
    if(Input.keys.contains(moveDown)) {
      acc = acc.add(new GeoVector(0, 1));
    }

    acc = acc.normalize();
    // acc.print();
    // System.out.println(acc.mag());
    acc = acc.mult(control);
  }

  public void update(double deltaTime) {
    vel = vel.add(acc);
    vel = vel.mult(drag);
    pos = pos.add(vel.mult(deltaTime));
    int halfSize = playerSize / 2;
    collider.x = (int)pos.x - halfSize;
    collider.y = (int)pos.y - halfSize;
    collided = false;
    if (damage > 0) {
      acc = GeoVector.zero;
      --damage;
    } else {
      move(deltaTime);
    }
  }

  public Player(GeoVector position, double speed, double control) {
    super(position, playerSize);
    damage = 0;
    acc = GeoVector.zero;
    vel = GeoVector.zero;
    this.speed = speed;
    this.control = (control > speed) ? this.speed : control;
    drag = this.speed / (this.speed + this.control);
  }

  public int compareTo(Player player) {
    return 0;
  }
}
