import java.awt.Rectangle;
import java.awt.Color;

// trades initial loading time for performant particle effects and trails
public class ParticlePool {

  private static PoolingQueue<Particle> particles;
  public static PoolingQueue<Particle> getParticles() { return particles; }

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
