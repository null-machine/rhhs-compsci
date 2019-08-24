import java.awt.Rectangle;

public class Racer extends Wall {

  private Controller controller;
  private Model model;

  private GeoVector acc;
  private GeoVector vel;

  private int damage;
  public boolean colliding;
  public boolean won;

  private int sparkCount = 12;
  private double sparkMag = 4;

  private int winCount = 48;
  private double winMag = 8;

  public int tag;
  public void setTag(int tag) { this.tag = tag; }

  public void win() {
    GeoVector winVel = new GeoVector(0.0, winMag);
    double winAng = Math.PI * 2 / winCount;
    for (int i = 0; i < winCount; ++i) {
      ParticlePool.spawn(pos, winVel.rotate(i * winAng), model.size, model.color);
    }
    won = true;
  }

  public void collide(GeoVector wallPos) {
    if (damage == 0) {
      // for (int i = 0; damage == 0 && i < sparkCount; ++i) {
      for (int i = 0; i < sparkCount; ++i) {
        ParticlePool.spawn(pos, GeoVector.zero.randInRadius(sparkMag), model.size, model.color);
      }
    }
    vel = vel.neg();
    setPos(pos.add(pos.sub(wallPos).normalize()));
    damage = model.fragility;
  }

  public void update(double deltaTime) {

    if (damage > 0) {
      acc = GeoVector.zero;
      --damage;
      color = model.color.darker();
    } else {
      acc = controller.getDir();
      acc = acc.normalize();
      acc = acc.mult(model.control);
      color = model.color;
      ParticlePool.spawn(pos, GeoVector.zero, model.size, model.color);
      // ParticlePool.spawn(pos, vel.neg(), model.size, model.color);
      // ParticlePool.spawn(pos, vel, model.size, model.color);
    }

    vel = vel.add(acc);
    vel = vel.mult(model.drag);
    pos = pos.add(vel.mult(deltaTime));

    updateCollider(model.size);
  }

  public Racer(Controller controller, Model model) {
    super(GeoVector.zero, model.size);

    this.controller = controller;
    this.model = model;

    acc = GeoVector.zero;
    vel = GeoVector.zero;
  }
}
