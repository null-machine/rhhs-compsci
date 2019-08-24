import java.awt.Color;
import java.awt.Graphics;

/**
* A section of the menu that allows players to choose the number of bots and difficulty.
* It scales with screen size to keep everything operational.
* @author Glen Wang
* @version 1.0
* @date June 11, 2019
*/
public class Options extends CardMenu {

  private final int[][] difficulty = {
    {7, 6, 4, 3}, // track width: smaller width means less room for error
    {200, 150, 100, 50}, // track length: harder difficulties have shorter tracks to minimize frustration
    {2, 2, 2, 1}, // wall width: mostly aesthetic but one is minimum
    {4, 8, 10, 12} // pivot count: loosely relates to the curviness of the track
  };
  private int difficultyIndex;

  /**
  * Gets the width of the chosen difficulty.
  * @return The width of the race track.
  */
  public int getWidth() {
    return difficulty[0][difficultyIndex];
  }

  /**
  * Gets the length of the chosen difficulty.
  * @return The length of the race track.
  */
  public int getLength() {
    return difficulty[1][difficultyIndex];
  }

  /**
  * Gets the wall width of the chosen difficulty.
  * @return The wall width of the race track.
  */
  public int getWallWidth() {
    return difficulty[2][difficultyIndex];
  }

  /**
  * Gets the curviness of the chosen difficulty.
  * @return The curviness of the race track.
  */
  public int getPivotCount() {
    return difficulty[3][difficultyIndex];
  }


  private final int[] botCount = {0, 1 ,2, 3};
  private int botCountIndex;

  /**
  * Gets the chosen number of bot racers.
  * @return The number of bot racers.
  */
  public int getBotCount() {
    return botCount[botCountIndex];
  }

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
    int setting = (int)((dir.x - cellIndex) / cellSize - offset - 3);
    // 3 is number of cells from card edge
    if (setting >= 0 && setting <= 3) { // if clicked on options
      switch (category) {
        case 6: case 7: // clicked on difficulty
          difficultyIndex = setting;
        break;
        case 8: case 9: // clicked on bot count
          botCountIndex = setting;
        break;
      }
    }
  }

  /**
  * Constructs an options menu that responds to a controller.
  * @param controller This menu's controller.
  */
  public Options(Controller controller) {
    super(controller);
    difficultyIndex = 1;
    botCountIndex = 3;
  }
}
