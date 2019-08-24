import java.awt.Rectangle;
import java.awt.Color;

/**
* A singleton that optimizes particle spawning by making use of object pooling.
* @author Glen Wang
*/
public class ParticlePool {

  private static PoolingQueue<Particle> particles;
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

  public ParticlePool(int particleCount) {
    particles = new PoolingQueue<Particle>();
    for (int i = 0; i < particleCount; ++i) {
      particles.pool(new Particle());
    }
  }
}
