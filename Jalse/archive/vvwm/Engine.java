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

  // E:\Resources\jdk-12.0.1\bin\javac.exe Jalse.java & E:\Resources\jdk-12.0.1\bin\java.exe Jalse.java
  // E:\Resources\jdk-12.0.1\bin\java.exe -jar E:\violetumleditor-2.1.0.jar

  private List<Racer> racers;
  private Set<Wall> walls;
  private Queue<Particle> particles;
  private Wall finish;

  private boolean running = true;
  private final int deltaTime = 20;

  private GeoVector cameraPos;
  private double cameraZoom;

  private void paintRacers(Graphics g) {
    for (Racer racer : racers) {
      cameraPos = racer.pos.sub(new GeoVector(getSize().width / 2 - racer.collider.width / 2, getSize().height / 2 - racer.collider.width / 2));
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
    g.fillRect(rect.x - (int)cameraPos.x, rect.y - (int)cameraPos.y, rect.width, rect.height);
  }

  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    setDoubleBuffered(true);
    setBackground(Color.BLACK);

    paintRect(g, finish.collider, finish.color);
    paintWalls(g);
    paintRacers(g);
    paintParticles(g);

    // paintColliders(g, walls);
    // paintColliders(g, racers);
    repaint();
  }

  public Engine(List<Racer> racers, GridMap gm) {
    this.racers = racers;
    walls = gm.walls;
    finish = gm.finish;
    particles = ParticlePool.getParticles();

    cameraPos = GeoVector.zero;

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
