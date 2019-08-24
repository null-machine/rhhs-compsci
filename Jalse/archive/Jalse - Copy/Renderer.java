import javax.swing.JPanel;
import java.util.List;
import java.util.HashSet;

import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Graphics;

// draws the game
public class Renderer extends JPanel {

  private List<Entity> Players;
  private HashSet<Rectangle> map;
  private int deltaTime;

  private void update() {
    while (true) { // while game runs
      repaint();
      try{ Thread.sleep(deltaTime); } catch (Exception exc) {}
    }
  }

  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    setDoubleBuffered(true);
    setBackground(new Color(108, 91, 84));

    // paint Players
    Rectangle collider;
    for (Entity Player : Players) {
      collider = Player.collider;
      g.setColor(Player.color);
      g.fillRect(collider.x, collider.y, collider.width, collider.height);
    }

    // paint map
    // g.setColor(Color.GRAY);
    // for (Rectangle tile : map) {
    //   g.fillRect(tile.x, tile.y, tile.width, tile.height);
    // }

    repaint();
  }

  public Renderer(List<Entity> entities) {
    this.Players = entities;
    Thread updater = new Thread(new Runnable() { public void run() { update(); } } );
    updater.start();
  }
}
