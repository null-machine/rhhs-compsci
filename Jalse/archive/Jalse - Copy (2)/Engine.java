import java.util.List;
import java.util.Set;

import javax.swing.JPanel;

import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Graphics;

// manages physics and rendering
public class Engine extends JPanel {

  // E:\Resources\jdk-12.0.1\bin\javac.exe Jalse.java & E:\Resources\jdk-12.0.1\bin\java.exe Jalse.java
  // E:\Resources\jdk-12.0.1\bin\java.exe -jar E:\violetumleditor-2.1.0.jar

  private List<Player> players;
  private Set<Wall> rendWalls;
  private Set<Wall> physWalls;
  // private Set<Particle> particles;

  private boolean running = true;
  private final int deltaTime = 20;

  private Vector cameraPos;
  // private double cameraZoom;

  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    setDoubleBuffered(true);
    setBackground(new Color(108, 91, 84));

    Rectangle collider;
    for (Player player : players) {
      collider = player.collider;
      g.setColor(player.color);
      g.fillRect(collider.x, collider.y, collider.width, collider.height);
    }

    repaint();
  }

  public Engine(List<Player> players) {
    this.players = players;

    Thread rendUpdater = new Thread(new RendUpdater());
    Thread physUpdater = new Thread(new PhysUpdater());
    physUpdater.setPriority(Thread.MAX_PRIORITY);

    rendUpdater.start();
    physUpdater.start();
  }

  private class PhysUpdater implements Runnable {
    public void run() {
      while (running) {
        for (int i = 0; i < players.size(); ++i) {
          players.get(i).update(deltaTime);
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
