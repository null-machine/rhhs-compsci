/**
* A wall for visual effects instead of collision.
* It shrinks and slows down over time.
* @author Glen Wang
* @version 1.0
* @date June 11, 2019
*/
public class Particle extends Wall implements Updatable {

  private final double SHRINKAGE = 0.2; // particle lifetime proportional to size

  private double size;
  private double currentSize;

  /**
  * Sets the size of the particle.
  * @param size The size of the particle.
  */
  public void setSize(double size) {
    this.size = size;
    currentSize = size;
  }

  private GeoVector vel;

  /**
  * Sets the velocity of the particle.
  * @param vel The velocity of the particle.
  */
  public void setVel(GeoVector vel) {
    this.vel = vel;
  }

  @Override
  public void update(long deltaTime) {
    currentSize = (currentSize < -1.0) ? -1.0 : currentSize - SHRINKAGE * deltaTime;
    setVel(vel.mult(currentSize / size));
    setPos(getPos().add(vel.mult(deltaTime)));

    updateCollider(currentSize);
  }

  /**
  * Constructs an invisible particle.
  */
  public Particle() {
    super(GeoVector.zero, 0.0);
    setVel(GeoVector.zero);
  }
}
