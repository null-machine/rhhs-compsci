import java.awt.Rectangle;
import java.awt.Color;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

// trades initial loading time for performant particle effects and trails
public class ParticlePool {

  // public static ParticlePool instance;

  private static Queue<Particle> particles;
  public static Queue<Particle> getParticles() { return particles; }

  public static void spawn(GeoVector pos, GeoVector vel, double size, Color color) {
    Particle particle = particles.remove();
    particle.setPos(pos);
    particle.setVel(vel);
    particle.setSize(size);
    particle.setColor(color);
    particles.add(particle);
  }

  public ParticlePool(int particleCount) {
    particles = new ConcurrentLinkedQueue<Particle>();
    for (int i = 0; i < particleCount; ++i) {
      particles.add(new Particle());
    }
  }
}
