import javax.swing.JPanel;

import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;

/**
* A screen that renders the game from a single racer's perspective.
* It zooms proportional to the racer's speed,
* and looks slightly ahead of racer's movement.
* When the racer crashes, it shakes the screen proportional to
* the racer's fragility.
* @author Glen Wang
*/
public class Renderer extends JPanel implements Updatable {

  private GameState gs;

  private Racer focus;
  public void setFocus(Racer focus) { this.focus = focus; }

  private double camZoom;
  private GeoVector camPos;
  private GeoVector oldCamPos;
  private double zoomPercent;

  private void paintWall(Graphics g, Wall wall) {
    Rectangle rect = wall.collider;
    g.setColor(wall.color);
    g.fillRect(
    (int)(camZoom * (rect.x - camPos.x)),
    (int)(camZoom * (rect.y - camPos.y)),
    (int)(camZoom * rect.width + 1),
    (int)(camZoom * rect.height + 1)
    );
  }

  private <T> void paintIterable(Graphics g, Iterable<T> walls) {
    for (T wall : walls) {
      paintWall(g, (Wall)wall);
      // paintRect(g, ((Wall)wall).collider, ((Wall)wall).color);
    }
  }

  private void paintTags(Graphics g, Iterable<Racer> racers) {
    g.setColor(Color.BLACK);
    g.setFont(new Font("Consolas", Font.PLAIN, 24));

    for (Racer racer : gs.racers) {
      g.drawString(
      "" + racer.tag,
      (int)(camZoom * (racer.pos.x - camPos.x) - 12),
      (int)(camZoom * (racer.pos.y - camPos.y) + 7)
      );
    }
  }

  private final double shakeMag = 0.05;
  private void shakeCamera() {
    double shakeRadius = focus.damage * focus.damage / focus.model.fragility * shakeMag;
    camPos = camPos.randInRadius(shakeRadius);
  }

  private final double startZoom = 1.0; // TODO make constants UPPERCASE
  private final double zoomMag = 0.33;
  private final double offsetMag = 300;
  private final double smoothing = 0.01;
  private void updateCamera(double deltaTime) {
    camZoom = startZoom - focus.vel.mag() * zoomMag;
    camPos = focus.pos.add(focus.vel.mult(offsetMag));

    GeoVector screenCenter = new GeoVector(getSize().width, getSize().height);
    screenCenter = screenCenter.mult(0.5 / camZoom);
    camPos = oldCamPos.lerp(camPos, deltaTime * smoothing);
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
    paintTags(g, gs.racers);
    paintWall(g, gs.finish);

    repaint();
  }

  public Renderer(GameState gs) {
    this.gs = gs;

    camPos = GeoVector.zero;
    oldCamPos = camPos.copy();
    camZoom = 0.5;
  }
}
