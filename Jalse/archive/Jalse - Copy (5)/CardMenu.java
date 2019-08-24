import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;

abstract public class CardMenu implements Updatable { // updobable

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

  abstract public void update(long deltaTime);

  public void render(Graphics g, int cellIndex, double cellSize) {
    // simulates gridbaglayout with consistent cell size
    this.cellSize = cellSize;
    this.cellIndex = cellIndex;
    this.offset = cellIndex * 8;
  }

  public CardMenu(Controller controller) {
    this.controller = controller;
  }
}
