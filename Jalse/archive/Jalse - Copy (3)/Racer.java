import java.awt.Rectangle;
import java.awt.Color;

public class Racer extends Wall {

  private Controller controller;
  public Model model;

  protected GeoVector acc;
  public GeoVector vel;

  public int damage;
  public boolean colliding;
  public boolean won;

  private int collisions;
  private double finishTime;
  private int placement;

  public PathNode pn;

  private int sparkCount = 12;
  private double sparkMag = 4;
  private int winCount = 48;
  private double winMag = 8;

  public String tag;
  public void setTag(String tag) { this.tag = tag; }

  protected void repair(long deltaTime) {
    acc = GeoVector.zero;
    damage = (deltaTime < damage) ? (int)(damage - deltaTime) : 0;
    color = model.color.darker().darker();
  }

  protected void move(GeoVector dir) {
    acc = dir;
    acc = acc.mult(model.control);
    color = model.color;
    ParticlePool.spawn(pos, vel.neg(), model.size, model.color);
  }

  protected void updatePos(long deltaTime) {
    vel = vel.add(acc);
    vel = vel.mult(model.drag);
    pos = pos.add(vel.mult(deltaTime));
  }

  protected void updateNode() {
    double currDist = pos.dist(pn.pos);
    double nextDist, prevDist;
    nextDist = (pn.next != null) ? pos.dist(pn.next.pos) : Double.POSITIVE_INFINITY;
    prevDist = (pn.prev != null) ? pos.dist(pn.prev.pos) : Double.POSITIVE_INFINITY;
    double closest = Math.min(currDist, Math.min(nextDist, prevDist));

    if (closest == nextDist) {
      pn = pn.next;
    } else if (closest == prevDist) {
      pn = pn.prev;
    }
  }

  public void update(long deltaTime) {
    if (damage > 0) {
      repair(deltaTime);
    } else {
      move(controller.getDir());
    }
    updatePos(deltaTime);
    updateNode();
    updateCollider(model.size);
  }

  public void collide() {
    if (damage == 0) {
      for (int i = 0; i < sparkCount; ++i) {
        ParticlePool.spawn(pos, GeoVector.zero.randInRadius(sparkMag), model.size, model.color);
      }
    }
    vel = vel.neg();
    setPos(pos.add(pn.pos.sub(pos).normalize())); // shift towards current node
    damage = model.fragility;
  }

  public void win() {
    GeoVector winVel = GeoVector.up.mult(winMag);
    double winAng = Math.PI * 2 / winCount;
    for (int i = 0; i < winCount; ++i) {
      ParticlePool.spawn(pos, winVel.rotate(i * winAng), model.size, model.color);
    }
    damage = 0;
    color = model.color; // damage and color reset for case where racer bounces into finish
    won = true;
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof Racer)) {
      return false;
    }
    Racer other = (Racer)o;
    return (tag == other.tag);
  }

  @Override
  public int hashCode() {
    return tag.hashCode();
  }

  public Racer(Controller controller, Model model) {
    super(GeoVector.zero, model.size);

    this.controller = controller;
    this.model = model;

    acc = GeoVector.zero;
    vel = GeoVector.zero;
  }
}
