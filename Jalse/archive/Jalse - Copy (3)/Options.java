import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;

import java.util.Random;

public class Options extends CardMenu {

  private final int[][] difficulty = {
    {7, 6, 4, 3}, // width
    {200, 150, 100, 50}, // length
    {2, 2, 2, 1} // wall width {2, 4, 6, 12}
  };
  private int difficultyIndex = 1;
  public int getWidth() { return difficulty[0][difficultyIndex]; }
  public int getLength() { return difficulty[1][difficultyIndex]; }
  public int getWallWidth() { return difficulty[2][difficultyIndex]; }

  private final int[] botCount = {0, 1 ,2, 3};
  private int botCountIndex = 3;
  public int getBotCount() { return botCount[botCountIndex]; }

  private void renderRow(Graphics g, double y) {
    for (int i = 3; i < 7; ++i) {
      renderBox(g, Color.LIGHT_GRAY, i + 0.25, y, 0.5, 0.5);
    }
  }

  @Override
  public void render(Graphics g, int cellIndex, double cellSize) {
    super.render(g, cellIndex, cellSize);
    renderBox(g, Color.LIGHT_GRAY, 2, 4, 6, 6);
    renderBox(g, Color.WHITE, 3, 5, 4, 4);
    renderText(g, Color.BLACK, 3, 5, 0.5, 0.6, "DIFFICULTY");
    renderText(g, Color.BLACK, 3, 7, 0.5, 0.6, "BOT COUNT");
    renderRow(g, 6.25);
    renderRow(g, 8.25);
    renderBox(g, Color.BLACK, difficultyIndex + 3.25, 6.25, 0.5, 0.5);
    renderBox(g, Color.BLACK, botCountIndex + 3.25, 8.25, 0.5, 0.5);
  }

  @Override
  public void update(long deltaTime) {
    GeoVector dir = controller.getDir();
    int category = (int)(dir.y / cellSize);
    int setting = (int)((dir.x - cellIndex) / cellSize - offset - 3); // 3 is cells from edge
    // System.out.println(category + " " + setting);
    if (setting >= 0 && setting <= 3) {
      switch (category) {
        case 6: // why cant jframe handle title bars nicely
        case 7:
          difficultyIndex = setting;
          break;
        case 8:
        case 9:
          botCountIndex = setting;
          break;
      }
    }
  }

  public Options(Controller controller) {
    super(controller);
  }
}
