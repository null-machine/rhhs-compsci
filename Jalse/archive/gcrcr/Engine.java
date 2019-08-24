import java.util.List;
import java.util.Set;

import javax.swing.JPanel;

import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;

// runs the game by rendering and simulating physics
public class Engine extends JPanel {

  // E:\Resources\jdk-12.0.1\bin\javac.exe *.java & E:\Resources\jdk-12.0.1\bin\java.exe Launcher.java

  private List<Racer> racers;
  private Set<Wall> walls;
  private PoolingQueue<Particle> particles;
  private Wall finish;

  private boolean running = true;
  private final int deltaTime = 20;

  private GeoVector camPos;
  private double camZoom;
  private double zoomPercent;

  private GridMap gm;

  // painting

  private void paintRect(Graphics g, Rectangle rect, Color color) {
    g.setColor(color);
    g.fillRect(
    (int)(camZoom * (rect.x - camPos.x)),
    (int)(camZoom * (rect.y - camPos.y)),
    (int)(camZoom * rect.width + 1),
    (int)(camZoom * rect.height + 1)
    );
  }

  private <T> void paintIterable(Graphics g, Iterable<T> walls) {
    for (T wall : walls) {
      paintRect(g, ((Wall)wall).collider, ((Wall)wall).color);
    }
  }

  private void paintRacerTags(Graphics g, List<Racer> racers) {
    g.setColor(Color.GRAY);
    g.setFont(new Font("Consolas", Font.BOLD, 24));

    for (Racer racer : racers) {
      g.drawString(
      "" + racer.tag,
      (int)(camZoom * (racer.pos.x - camPos.x) - 7),
      (int)(camZoom * (racer.pos.y - camPos.y) + 7)
      );
    }
  }

  // camera

  private GeoVector getRacerBounds(List<Racer> racers) {
    GeoVector pos = racers.get(0).pos;
    GeoVector min = pos.copy();
    GeoVector max = pos.copy();
    GeoVector racerPos;
    for (Racer racer : racers) {
      racerPos = racer.pos;
      min.x = (racerPos.x < min.x) ? racerPos.x : min.x;
      min.y = (racerPos.y < min.y) ? racerPos.y : min.y;
      max.x = (racerPos.x > max.x) ? racerPos.x : max.x;
      max.y = (racerPos.y < max.y) ? racerPos.y : max.y;
    }
    return new GeoVector(max.x - min.x, max.y - min.y);
  }

  private void captureRacers() {
    double minZoom = 0.5;
    double maxX = getSize().width / Math.abs(finish.pos.x);
    double maxY = getSize().height / Math.abs(finish.pos.y);
    double maxZoom = (maxX < maxY) ? maxX : maxY;
    double zoomScale = 2; // wont be necessary after node bounds
    maxZoom /= zoomScale;
    GeoVector bounds = getRacerBounds(racers);
    double zoomMult = bounds.mag() / finish.pos.mag();
    zoomMult *= zoomScale;
    zoomMult = (zoomMult > 1.0) ? 1.0 : zoomMult;
    camZoom = minZoom - (minZoom - maxZoom) * zoomMult;
    zoomPercent = (minZoom - camZoom) / (minZoom - maxZoom);
  }

  private void centerRacers() {
    camPos = GeoVector.zero;
    for (Racer racer : racers) {
      camPos = camPos.add(racer.pos);
    }
    camPos = camPos.mult(1.0 / racers.size());

    GeoVector center = GeoVector.zero.lerp(finish.pos, 0.5);
    GeoVector dir = camPos.sub(center);
    camPos = camPos.sub(dir.mult(zoomPercent));

    camPos = camPos.sub(new GeoVector(getSize().width / 2 / camZoom, getSize().height / 2 / camZoom));
  }

  private void updateCamera() {
    captureRacers();
    centerRacers();
  }

  // standard

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    setDoubleBuffered(true);
    setBackground(Color.BLACK);

    updateCamera();
    paintIterable(g, walls);
    paintIterable(g, racers);
    paintIterable(g, particles);
    paintRacerTags(g, racers);
    // TODO:
    // option menu with difficulty, length, bot count, play button
    // win screen
    // a metric fuckton of refactoring, documentation and polish
    paintRect(g, finish.collider, finish.color);

    repaint();
  }

  public Engine(List<Racer> racers, GridMap gm) {
    this.racers = racers;
    racers.add(new Bot(new Balance()));
    racers.add(new Bot(new Control()));
    // racers.add(new Bot(new Speed()));
    this.gm = gm;
    walls = gm.walls;
    finish = gm.finish;
    particles = ParticlePool.getParticles();

    camPos = GeoVector.zero;
    camZoom = 0.5;

    for (Racer racer : racers) {
      racer.pn = gm.bm.start;
    }

    Thread rendUpdater = new Thread(new RendUpdater());
    rendUpdater.start();

    Thread physUpdater = new Thread(new PhysUpdater());
    physUpdater.setPriority(Thread.MAX_PRIORITY);
    physUpdater.start();
  }

  private class PhysUpdater implements Runnable {

    private void updateParticles(double deltaTime) {
      for (Particle particle : particles) {
        particle.update(deltaTime);
      }
    }

    private void checkCollisions(Racer racer) {
      racer.colliding = false;
      for (Wall wall : walls) {
        if (racer.collider.intersects(wall.collider)) {
          if (!racer.colliding) {
            racer.collide();
          }
          racer.colliding = true;
        }
      }
    }

    public void run() {
      try { Thread.sleep(1000); } catch (Exception exc) {}
      while (running) {
        for (int i = 0; i < racers.size(); ++i) {
          Racer racer = racers.get(i);
          if (racer.won) {
            continue;
          }
          checkCollisions(racer);
          racer.update(deltaTime);
          if (finish.collider.contains(racer.collider)) {
            racer.win();
          }
        }
        updateParticles(deltaTime);
        try { Thread.sleep(deltaTime); } catch (Exception exc) {}
      }
    }
  }

  private class RendUpdater implements Runnable {
    public void run() {
      while (running) {
        repaint();
      }
    }
  }
}
