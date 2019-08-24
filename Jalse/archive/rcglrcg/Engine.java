import java.util.List;
import java.util.Set;
import java.util.Queue;
// import java.util.Collection;

import javax.swing.JPanel;

import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Graphics;

// manages physics and rendering
public class Engine extends JPanel {

  // E:\Resources\jdk-12.0.1\bin\javac.exe Jalse.java & E:\Resources\jdk-12.0.1\bin\java.exe Launcher.java
  // E:\Resources\jdk-12.0.1\bin\java.exe -jar E:\violetumleditor-2.1.0.jar

  private List<Racer> racers;
  private Set<Wall> walls;
  private Queue<Particle> particles;
  private Wall finish;

  private boolean running = true;
  private final int deltaTime = 10;

  private GeoVector camPos;
  private double camZoom;

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

  private void paintRect(Graphics g, Rectangle rect, Color color) {
    g.setColor(color);
    g.fillRect(
      (int)(camZoom * (rect.x) - camPos.x * camZoom),
      (int)(camZoom * (rect.y) - camPos.y * camZoom),
      (int)(camZoom * rect.width),
      (int)(camZoom * rect.height)
    );
  }

  private void updateCamera() {
    camPos = GeoVector.zero;
    camZoom = 1;
    double racerDists = 1;

    Racer racer;
    for (int i = 0; i < racers.size(); ++i) {
      racer = racers.get(i);
      camPos = camPos.add(racer.pos);
      for (int j = 0; j < racers.size(); ++j) {
        racerDists += racer.pos.dist(racers.get(j).pos);
      }
    }
    camZoom -= Math.sqrt(racerDists) * 0.008;
    camPos = camPos.mult(1.0 / racers.size());
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
