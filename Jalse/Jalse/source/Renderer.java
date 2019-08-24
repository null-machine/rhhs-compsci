import javax.swing.JPanel;

import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;

/**
* A screen that renders the game from a single racer's perspective.
* It zooms proportional to the racer's speed and
* looks slightly ahead of racer's movement.
* When the racer crashes, it shakes the screen proportional to
* the racer's fragility.
* @author Glen Wang
* @version 1.0
* @date June 11, 2019
*/
public class Renderer extends JPanel implements Updatable {

  // camera specs
  private final double START_ZOOM = 1.1;
  private final double ZOOM_MAG = 0.33;
  private final double OFFSET_MAG = 300;
  private final double SMOOTHING = 0.01;
  private final double SHAKE_MAG = 0.05;
  private final int TAG_FONT_SIZE = 24;

  private GameState gameState;

  private Racer focus;
  public void setFocus(Racer focus) {
    this.focus = focus;
  }

  private double camZoom;
  private GeoVector camPos;
  private GeoVector oldCamPos;

  private void paintWall(Graphics g, Wall wall) {
    Rectangle rect = wall.getCollider();
    g.setColor(wall.getColor());
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
    }
  }

  private void paintTags(Graphics g, Iterable<Racer> racers) {
    g.setColor(Color.BLACK);
    g.setFont(new Font("Consolas", Font.PLAIN, TAG_FONT_SIZE));

    for (Racer racer : gameState.racers) {
      g.drawString(
      "" + racer.getTag(),
      (int)(camZoom * (racer.getPos().x - camPos.x) - 12),
      (int)(camZoom * (racer.getPos().y - camPos.y) + 7)
      ); // 12 and 7 are hard-coded text centering
    }
  }

  private void shakeCamera() {
    double damage = focus.getDamage();
    double shakeRadius = damage * damage / focus.getModel().fragility * SHAKE_MAG;
    camPos = camPos.randInRadius(shakeRadius);
  }

  private void updateCamera(double deltaTime) {

    // adjust zoom and offset based on velocity
    GeoVector dir = focus.getVel();
    camZoom = START_ZOOM - dir.mag() * ZOOM_MAG;
    camPos = focus.getPos().add(dir.mult(OFFSET_MAG));

    // smooth position
    camPos = oldCamPos.lerp(camPos, deltaTime * SMOOTHING);
    oldCamPos = camPos.copy();

    shakeCamera();

    // center position
    GeoVector screenCenter = new GeoVector(getSize().width, getSize().height);
    screenCenter = screenCenter.mult(0.5 / camZoom);
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

    paintIterable(g, gameState.walls);
    paintIterable(g, gameState.racers);
    paintIterable(g, gameState.particles);
    paintTags(g, gameState.racers);
    paintWall(g, gameState.finish);

    repaint();
  }

  /**
  * Constructs a renderer that displays a game state from
  * the perspective of a focal racer.
  * @param gameState The current game state.
  */
  public Renderer(GameState gameState) {
    this.gameState = gameState;

    camPos = GeoVector.zero;
    oldCamPos = camPos.copy();
  }
}
