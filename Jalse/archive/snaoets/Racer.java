import java.awt.Rectangle;

public class Racer extends Wall {

  private Controller controller;
  private Model model;

  private GeoVector acc;
  public GeoVector vel;

  private int damage;
  public boolean collided;

  private int sparkCount = 10;
  private double sparkMag = 4;

  private int winCount = 48;
  private double winMag = 9;

  public void win() {
    GeoVector winVel = new GeoVector(0.0, winMag);
    double winAng = Math.PI * 2 / winCount;
    for (int i = 0; i < winCount; ++i) {
      ParticlePool.spawn(pos, winVel.rotate(i * winAng), model.size, model.color);
    }
  }

  public void collide() {
    if (damage == 0) {
      for (int i = 0; i < sparkCount; ++i) {
        ParticlePool.spawn(pos, GeoVector.zero.randInRadius(sparkMag), model.size, model.color);
      }
    }
    damage = model.fragility;
    collided = true;
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
      // ParticlePool.spawn(pos, GeoVector.zero, model.size, model.color);
      ParticlePool.spawn(pos, vel.neg(), model.size, model.color);
    }
    collided = false;

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
