import java.awt.Rectangle;
import java.awt.Color;

/**
* A singleton that optimizes particle spawning by making use of object pooling.
* @author Glen Wang
*/
public class ParticlePool {

  private static PoolingQueue<Particle> particles;

  /**
  * Gets the queue of particles this spawns from.
  * @return A queue that contains all of this particle pool's particles.
  */
  public static PoolingQueue<Particle> getParticles() { return particles; }

  /**
  * Spawns a particle with an initial velocity, position, size and color.
  * @param pos The initial position.
  * @param vel The initial velocity.
  * @param size The initial size.
  * @param color The particle's color.
  */
  public static void spawn(GeoVector pos, GeoVector vel, double size, Color color) {
    Particle particle = particles.cycle();
    particle.setPos(pos);
    particle.setVel(vel);
    particle.setSize(size);
    particle.setColor(color);
  }

  /**
  * Constructs a particle pool with the specified number of particles.
  * @param particleCount The number of particles.
  */
  public ParticlePool(int particleCount) {
    particles = new PoolingQueue<Particle>();
    for (int i = 0; i < particleCount; ++i) {
      particles.pool(new Particle());
    }
  }
}
