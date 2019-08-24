import java.awt.Rectangle;

public class Particle extends Wall {

  private double size;
  private double currentSize;
  public void setSize(double size) {
    this.size = size;
    currentSize = size;
  }

  private GeoVector vel;
  public void setVel(GeoVector vel) { this.vel = vel; }

  private double shrinkage = 0.2;

  public void update(double deltaTime) {

    currentSize = (currentSize < 0.0) ? 0.0 : currentSize - shrinkage * deltaTime;
    setVel(vel.mult(currentSize / size));
    setPos(getPos().add(vel.mult(deltaTime)));

    // if (currentSize != 0.0) {
    //   System.out.println(currentSize);
    // }

    updateCollider(currentSize);
  }

  public Particle() { // spawning is done by particlepool
    super(GeoVector.zero, 0.0);
    setVel(GeoVector.zero);
  }
}
