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

  public void update(long deltaTime) {

    currentSize = (currentSize < -1.0) ? -1.0 : currentSize - shrinkage * deltaTime;
    setVel(vel.mult(currentSize / size));
    setPos(getPos().add(vel.mult(deltaTime)));

    updateCollider(currentSize);
  }

  public Particle() {
    super(GeoVector.zero, 0.0);
    setVel(GeoVector.zero);
  }
}
