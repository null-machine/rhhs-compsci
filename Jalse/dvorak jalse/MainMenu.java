import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics;

import java.util.Random;

/**
* A screen that allows players to choose their racer model,
* set the track difficulty, and choose the number of bot racers.
* Generates the game after the players are ready.
* @author Glen Wang
* @version 1.0
* @date June 11, 2019
*/
public class MainMenu extends JPanel implements Updatable {

  private Master master;
  private GameState gameState;

  private Model[] models;

  private Selector selectorOne;
  private Selector selectorTwo;
  private Options options;

  private final int xCellCount = 26;
  private final int yCellCount = 14;

  private double getCellSize() {
    double xSize = getSize().width / xCellCount;
    double ySize = getSize().height / yCellCount;
    return (xSize < ySize) ? xSize : ySize;
  }

  private void addBots() {
    Random rnd = new Random();
    int rndIndex = rnd.nextInt(models.length);
    for(int i = rndIndex; (i < rndIndex + models.length) && (i < rndIndex + options.getBotCount()); ++i) {
      int modelIndex = (i < models.length) ? i : i - models.length;
      Bot bot = new Bot(models[modelIndex]);
      bot.setTag("B" + (modelIndex + 1));
      gameState.racers.add(bot);
    }
  }

  private void genMap() {
    AbstractMap abstractMap = new AbstractMap(options.getWidth(), options.getLength(), options.getPivotCount());
    WallMap wallMap = new WallMap(abstractMap, options.getWallWidth());

    gameState.walls = wallMap.getWalls();
    gameState.finish = wallMap.getFinish();

    for (Racer racer : gameState.racers) {
      racer.pathNode = abstractMap.getStart();
    }
  }

  @Override
  public void update(long deltaTime) {

    selectorOne.update(deltaTime);
    selectorTwo.update(deltaTime);
    options.update(deltaTime);
    repaint();

    if (selectorOne.getReady() && selectorTwo.getReady()) {

      gameState.clear();

      Racer racerOne = selectorOne.getRacer();
      Racer racerTwo = selectorTwo.getRacer();
      racerOne.setTag("P1");
      racerTwo.setTag("P2");
      gameState.racers.add(racerOne);
      gameState.racers.add(racerTwo);
      addBots();

      genMap();

      selectorOne.reset();
      selectorTwo.reset();

      master.loadRenderer(racerOne, racerTwo);
    }
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    setDoubleBuffered(true);
    setBackground(Color.BLACK);

    double cellSize = getCellSize();
    selectorOne.render(g, 0, cellSize);
    options.render(g, 1, cellSize);
    selectorTwo.render(g, 2, cellSize);

    repaint();
  }

  /**
  * Constructs a main menu that modifies a game state,
  * and communicates with a master.
  * @param gameState The current game state.
  * @param master The current master.
  */
  public MainMenu(GameState gameState, Master master) {
    this.master = master;
    this.gameState = gameState;
    gameState.controllerOne.awaitLift();
    gameState.controllerTwo.awaitLift();

    models = new Model[3];
    models[0] = new Control();
    models[1] = new Balance();
    models[2] = new Speed();

    selectorOne = new Selector(gameState.controllerOne, models);
    selectorTwo = new Selector(gameState.controllerTwo, models);
    options = new Options(gameState.mouse);
  }
}
