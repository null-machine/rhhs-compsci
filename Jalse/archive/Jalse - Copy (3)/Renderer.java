import javax.swing.JPanel;

import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;

// renders the game
public class Renderer extends JPanel implements Updatable {

  private GameState gs;

  private Racer focus;
  public void setFocus(Racer focus) { this.focus = focus; }

  private double camZoom;
  private GeoVector camPos;
  private GeoVector oldCamPos;
  private double zoomPercent;

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

  private void paintRacerTags(Graphics g, Iterable<Racer> racers) {
    g.setColor(Color.GRAY);
    g.setFont(new Font("Consolas", Font.PLAIN, 24));

    for (Racer racer : gs.racers) {
      g.drawString(
      "" + racer.tag,
      (int)(camZoom * (racer.pos.x - camPos.x) - 7),
      (int)(camZoom * (racer.pos.y - camPos.y) + 7)
      );
    }
  }

  private void shakeCamera() {
    double shakeMag = focus.damage * focus.damage / focus.model.fragility / 20;
    camPos = camPos.randInRadius(shakeMag);
  }

  private void updateCamera(double deltaTime) {
    camZoom = 1.0 - focus.vel.mag() / 3;
    camPos = focus.pos.add(focus.vel.mult(200));

    GeoVector screenCenter = new GeoVector(getSize().width, getSize().height);
    screenCenter = screenCenter.mult(0.5 / camZoom);
    camPos = oldCamPos.lerp(camPos, deltaTime / 100);
    oldCamPos = camPos.copy();

    if (focus.damage > 0) {
      shakeCamera();
    }

    camPos = camPos.sub(screenCenter);
  }

  @Override
  public void update(long deltaTime) {
    updateCamera(deltaTime);
    repaint();
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    setDoubleBuffered(true);
    setBackground(Color.BLACK);

    paintIterable(g, gs.walls);
    paintIterable(g, gs.racers);
    paintIterable(g, gs.particles);
    paintRacerTags(g, gs.racers);
    paintRect(g, gs.finish.collider, gs.finish.color);

    repaint();
  }

  public Renderer(GameState gs) {
    this.gs = gs;

    camPos = GeoVector.zero;
    oldCamPos = camPos.copy();
    camZoom = 0.5;
  }
}
