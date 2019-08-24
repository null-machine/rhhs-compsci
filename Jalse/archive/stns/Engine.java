import java.util.List;
import java.util.Set;
import java.util.Collection;

import javax.swing.JPanel;

import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Graphics;

// manages physics and rendering
class Engine extends JPanel {

  // E:\Resources\jdk-12.0.1\bin\javac.exe Jalse.java & E:\Resources\jdk-12.0.1\bin\java.exe Jalse.java
  // E:\Resources\jdk-12.0.1\bin\java.exe -jar E:\violetumleditor-2.1.0.jar

  private List<Player> players;
  private Set<Wall> walls;
  private Wall finish;
  // private Set<Particle> particles;
  private int wallDamage;

  private boolean running = true;
  private final int deltaTime = 20;

  private GeoVector cameraPos;
  private double cameraZoom;

  private void collide(Player player, Wall wall) {
    // shift out and apply force
    GeoVector dir = player.pos.sub(wall.pos);

    System.out.print("collision ");
    player.pos = player.pos.add(dir.mult(0.1));

    double absX = Math.abs(dir.x);
    double absY = Math.abs(dir.y);
    if (absX > absY) {
      // collided on side
      player.vel.x = -player.vel.x;
      System.out.print("side ");
      dir.print();
    } else if (absX < absY) {
      // collided on top
      player.vel.y = -player.vel.y;
      System.out.println("top ");
      dir.print();
    } else {
      // collided on corner
      System.out.println("never gonna give you up");
      player.vel.x = -player.vel.x;
      player.vel.y = -player.vel.y;
    }

  }

  private void paintPlayers(Graphics g) {
    Rectangle collider;
    for (Player player : players) {
      collider = player.collider;
      g.setColor(player.color);
      cameraPos = player.pos.sub(new GeoVector(640, 320));
      g.fillRect(collider.x - (int)cameraPos.x, collider.y - (int)cameraPos.y, collider.width, collider.height);
    }
  }

  private void paintWalls(Graphics g) {
    Rectangle collider;
    for (Wall wall : walls) {
      collider = wall.collider;
      g.setColor(wall.color);
      g.fillRect(collider.x - (int)cameraPos.x, collider.y - (int)cameraPos.y, collider.width, collider.height);
    }
  }

  // private <T> void paintColliders(Graphics g, Collection<T> entities) {
  //   Rectangle collider;
  //   for (T entity : entities) {
  //     collider = ((Wall)entity).collider;
  //     g.setColor(((Wall)entity).color);
  //     g.fillRect(collider.x - (int)cameraPos.x, collider.y - (int)cameraPos.y, collider.width, collider.height);
  //   }
  // }

  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    setDoubleBuffered(true);
    setBackground(new Color(108, 91, 84));

    paintWalls(g);
    paintPlayers(g);

    g.setColor(Color.GREEN);
    g.fillRect(finish.collider.x - (int)cameraPos.x, finish.collider.y - (int)cameraPos.y, finish.collider.width, finish.collider.height);
    // paintColliders(g, walls);
    // paintColliders(g, players);

    repaint();
  }

  public Engine(List<Player> players, Set<Wall> walls, Wall finish, int wallDamage) {
    this.players = players;
    this.walls = walls;
    this.finish = finish;
    this.wallDamage = wallDamage;
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
        for (int i = 0; i < players.size(); ++i) {
          Player player = players.get(i);

          if (player.collider.intersects(finish.collider)) {
            players.remove(player);
            // player = null;
          }

          // check wall collisions
          for (Wall wall : walls) {
            if (!player.collided && player.collider.intersects(wall.collider)) {
              collide(player, wall);
              player.damage = wallDamage;
            }
          }


          // check player collisions

          // move players
          player.update(deltaTime);
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
