import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;

/**
* A screen that displays which order the racers finished in.
* Loads the main menu after a delay has passed and a button is pressed.
* @author Glen Wang
* @version 1.0
* @date June 11, 2019
*/
public class Results extends JPanel implements Updatable {

  private Master master;
  private GameState gameState;

  private final long minWait = 1000;
  private long currentWait;

  private final int sizeScale = 12;
  private int size;

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
      if (!(gameState.controllerOne.getDir().equals(GeoVector.zero) && gameState.controllerTwo.getDir().equals(GeoVector.zero))) {
        master.loadMenu();
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
    int yPos = (getSize().height - size * gameState.racersDone.size()) / 2;

    g.setColor(Color.WHITE);
    g.setFont(new Font("Consolas", Font.BOLD, size));
    g.drawString("RESULTS", xPos, yPos);

    g.setFont(new Font("Consolas", Font.PLAIN, size));
    for (int i = 0; i < gameState.racersDone.size(); ++i) {
      Racer racer = gameState.racersDone.get(i);
      g.setColor(racer.getColor());
      g.drawString((i + 1) + ": " + racer.getTag(), xPos, yPos + (i + 2) * size);
    }

    repaint();
  }

  /**
  * Constructs a results menu that displays information about a game state,
  * and communicates with a master.
  * @param gameState The current game state.
  * @param master The current master.
  */
  public Results(GameState gameState, Master master) {
    this.master = master;
    this.gameState = gameState;
    currentWait = minWait;
    gameState.controllerOne.awaitLift();
    gameState.controllerTwo.awaitLift();
    updateSize();
  }
}
