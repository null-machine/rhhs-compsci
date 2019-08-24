import java.util.List;
import java.util.Set;

import javax.swing.JPanel;

import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Graphics;

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

  private GridMap gm;

  private void paintRect(Graphics g, Rectangle rect, Color color) {
    g.setColor(color);
    g.fillRect(
    (int)(camZoom * (rect.x - camPos.x)),
    (int)(camZoom * (rect.y - camPos.y)),
    (int)(camZoom * rect.width),
    (int)(camZoom * rect.height)
    );
  }

  private <T> void paintIterable(Graphics g, Iterable<T> walls) {
    for (T wall : walls) {
      paintRect(g, ((Wall)wall).collider, ((Wall)wall).color);
    }
  }

  private GeoVector getRacerBounds(List<Racer> racers) {
    GeoVector pos = racers.get(0).pos;
    double maxX = pos.x;
    double maxY = pos.y;
    double minX = pos.x;
    double minY = pos.y;
    GeoVector racerPos;
    for (int i = 0; i < racers.size(); ++i) {
      racerPos = racers.get(i).pos.copy();
      if (racerPos.x > maxX) {
        maxX = racerPos.x;
      }
      if (racerPos.y > maxY) {
        maxY = racerPos.y;
      }
      if (racerPos.x < minX) {
        minX = racerPos.x;
      }
      if (racerPos.y < minY) {
        minY = racerPos.y;
      }
    }
    return new GeoVector(maxX - minX, maxY - minY);
  }

  private void updateCamera() {
    GeoVector racerPos;
    camPos = GeoVector.zero;
    for (int i = 0; i < racers.size(); ++i) {
      racerPos = racers.get(i).pos.copy();
      camPos = camPos.add(racerPos);
    }
    camPos = camPos.mult(1.0 / racers.size());

    GeoVector racerDimen = getRacerBounds(racers);
    GeoVector screenDimen = new GeoVector(getSize().width, getSize().height);

    double deadZone = 0.5;
    double closeZoom = 1;
    double farZoom = 0.08;

    camZoom = closeZoom;
    GeoVector delta = racerDimen.sub(screenDimen.mult(deadZone));
    if (delta.x < 0) {
      delta.x = 0;
    }
    if (delta.y < 0) {
      delta.y = 0;
    }
    // delta.print();
    // System.out.println(delta.mag());
    if (delta.x > 0) {
      camZoom -= Math.sqrt(delta.x / screenDimen.x) / 3;
    }
    if (delta.y > 0) {
      camZoom -= Math.sqrt(delta.y / screenDimen.y) / 3;
    }
    if (camZoom < farZoom) {
      camZoom = farZoom;
    }
    camPos = camPos.sub(new GeoVector(getSize().width / 2 / camZoom, getSize().height / 2 / camZoom));
  }

  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    setDoubleBuffered(true);
    setBackground(Color.BLACK);

    updateCamera();
    paintIterable(g, walls);
    paintIterable(g, racers);
    paintIterable(g, particles);
    // paintRacerTags();
    // TODO:
    // funk king zoom
    // racer tags
    // option menu with difficulty, length, bot count, play button
    // scoring and bots
    // win screen
    // switch to custom queue
    // a metric fuckton of refactoring, documentation and polish
    //
    paintRect(g, finish.collider, finish.color);

    repaint();
  }

  public Engine(List<Racer> racers, GridMap gm) {
    this.racers = racers;
    this.gm = gm;
    walls = gm.walls;
    finish = gm.finish;
    particles = ParticlePool.getParticles();

    camPos = GeoVector.zero;
    camZoom = 0.5;

    Thread rendUpdater = new Thread(new RendUpdater());
    Thread physUpdater = new Thread(new PhysUpdater());
    physUpdater.setPriority(Thread.MAX_PRIORITY);

    rendUpdater.start();
    physUpdater.start();
  }

  private class PhysUpdater implements Runnable {
    public void run() { // throws InterruptedException
      while (running) {
        for (int i = 0; i < racers.size(); ++i) {
          Racer racer = racers.get(i);

          if (racer.won) {
            continue;
          }

          racer.colliding = false;
          for (Wall wall : walls) {
            if (racer.collider.intersects(wall.collider)) {
              if (!racer.colliding) {
                racer.collide(wall.getPos());
              }
              racer.colliding = true;
            }
          }

          if (finish.collider.contains(racer.collider)) {
            racer.win();
          }

          racer.update(deltaTime);
        }
        for (Particle particle : particles) {
          particle.update(deltaTime);
        }
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
