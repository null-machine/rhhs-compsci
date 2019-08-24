import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;

/**
* A card-like panel on the main menu.
* Contains methods for grid-based rendering and
* interfaces with a controller.
* @version 1.0
* @date June 11, 2019
*/
abstract public class CardMenu implements Updatable {

  protected Controller controller;
  protected GeoVector oldDir;

  protected int cellIndex;
  protected double cellSize;
  protected int offset;

  protected void renderBox(Graphics g, Color color, double x, double y, double w, double h) {
    g.setColor(color);
    g.fillRect(
      (int)(cellSize * (x + offset)),
      (int)(cellSize * y),
      (int)(cellSize * w),
      (int)(cellSize * h)
    );
  }

  protected void renderText(Graphics g, Color color, int x, int y, double xPrecision, double yPrecision, String text) {
    g.setColor(color);
    g.setFont(new Font("Consolas", Font.PLAIN, (int)(cellSize / 2)));
    g.drawString(
    text,
      (int)(cellSize * (x + offset) + cellSize * xPrecision),
      (int)(cellSize * y + cellSize * yPrecision)
    );
  }

  /**
  * Updates proportions to fit on screen and paints graphics.
  * @param g The graphics used to draw.
  * @param cellIndex The index of this card.
  * @param cellSize The size of each drawing cell.
  */
  protected void render(Graphics g, int cellIndex, double cellSize) {
    this.cellSize = cellSize;
    this.cellIndex = cellIndex;
    this.offset = cellIndex * 8;
  }

  @Override
  abstract public void update(long deltaTime);

  /**
  * Constructs a new CardMenu that responds to a controller.
  * @param controller The controller that this responds to.
  */
  public CardMenu(Controller controller) {
    this.controller = controller;
  }
}
