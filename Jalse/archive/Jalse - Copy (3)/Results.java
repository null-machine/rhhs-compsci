import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;

public class Results extends JPanel implements Updatable {

  private Master engine;
  private GameState gs;

  private long minWait = 1000;
  private long currentWait;

  private int size = 48;
  private int sizeScale = 12;

  private void updateSize() {
    int horzSize = getSize().width / sizeScale;
    int vertSize = getSize().height / sizeScale;
    size = horzSize > vertSize ? vertSize : horzSize;
  }

  @Override
  public void update(long deltaTime) {
    updateSize();
    currentWait -= deltaTime;
    if (currentWait <= 0) {
      if (!(gs.controllerOne.getDir().equals(GeoVector.zero) && gs.controllerTwo.getDir().equals(GeoVector.zero))) {
        engine.loadMenu();
        currentWait = minWait;
      }
    }
    repaint();
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    setDoubleBuffered(true);
    setBackground(Color.BLACK);

    int xPos = getSize().width / 2 - size * 2;
    int yPos = (getSize().height - size * gs.racersDone.size()) / 2;

    g.setColor(Color.WHITE);
    g.setFont(new Font("Consolas", Font.BOLD, size));
    g.drawString("RESULTS", xPos, yPos);

    g.setFont(new Font("Consolas", Font.PLAIN, size));
    for (int i = 0; i < gs.racersDone.size(); ++i) {
      Racer racer = gs.racersDone.get(i);
      g.setColor(racer.color);
      g.drawString((i + 1) + ": " + racer.tag, xPos, yPos + (i + 2) * size);
    }

    repaint();
  }

  /**
  * Results
  * @param gs, the current game state
  * @param engine, the game engine
  */
  public Results(GameState gs, Master engine) {
    this.engine = engine;
    this.gs = gs;
    currentWait = minWait;
  }
}
