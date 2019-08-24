import java.util.List;
import java.util.Set;
import java.util.Queue;
// import java.util.Collection;

import javax.swing.JPanel;

import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Graphics;

// runs the game by rendering and simulating physics
public class Engine extends JPanel {

  // E:\Resources\jdk-12.0.1\bin\javac.exe Jalse.java & E:\Resources\jdk-12.0.1\bin\java.exe Launcher.java

  private List<Racer> racers;
  private Set<Wall> walls;
  private Queue<Particle> particles;
  private Wall finish;

  private boolean running = true;
  private final int deltaTime = 20;

  private GeoVector camPos;
  private double camZoom;

  private GridMap gm;

  private void paintRacers(Graphics g) {
    for (Racer racer : racers) {
      paintRect(g, racer.collider, racer.color);
    }
  }

  private void paintWalls(Graphics g) {
    for (Wall wall : walls) {
      paintRect(g, wall.collider, wall.color);
    }
  }

  private void paintParticles(Graphics g) {
    for (Particle particle : particles) {
      paintRect(g, particle.collider, particle.color);
    }
  }

  private void paintIteratable(Graphics g, Iterta)

  private void paintRect(Graphics g, Rectangle rect, Color color) {
    g.setColor(color);
    g.fillRect(
      (int)(camZoom * (rect.x - camPos.x)),
      (int)(camZoom * (rect.y - camPos.y)),
      (int)(camZoom * rect.width),
      (int)(camZoom * rect.height)
    );
  }

  private GeoVector getRacerBounds(List<Racer> racers) {
    double maxX = 0;
    double maxY = 0;
    GeoVector racerPos;
    for (int i = 0; i < racers.size(); ++i) {
      racerPos = racers.get(i).pos.sub(camPos).abs();
      if (racerPos.x > maxX) {
        maxX = racerPos.x;
      }
      else if (racerPos.y > maxY) {
        maxY = racerPos.y;
      }
    }
    return new GeoVector(maxX, maxY);
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
    double startZoom = 0.5;
    double farZoom = 0.1;

    camZoom = startZoom;
    if (racerDimen.x > screenDimen.x * deadZone) {
      camZoom -= (racerDimen.x - screenDimen.x * deadZone) / screenDimen.x / 4.0;
    }
    if (racerDimen.y > screenDimen.y * deadZone) {
      camZoom -= (racerDimen.y - screenDimen.y * deadZone) / screenDimen.y / 4.0;
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

    paintRect(g, finish.collider, finish.color);
    paintWalls(g);
    paintRacers(g);
    paintParticles(g);
    updateCamera();

    // paintColliders(g, walls);
    // paintColliders(g, racers);
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
            racers.remove(racer);
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
