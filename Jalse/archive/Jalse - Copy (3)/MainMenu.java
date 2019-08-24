import javax.swing.JPanel;

import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Graphics;

import java.util.Set;
import java.util.Random;

// sets map and racers
public class MainMenu extends JPanel implements Updatable { // refactor into mainmenu later

  // E:\Resources\jdk-12.0.1\bin\javac.exe *.java & E:\Resources\jdk-12.0.1\bin\java.exe Launcher.java

  private Master engine;
  private GameState gs;

  private Model[] models;

  private Selector selectorOne;
  private Selector selectorTwo;
  private Options options;

  private final int xCellCount = 26; // supports three cardmenus
  private final int yCellCount = 14; // TODO add cellsize * 14 - getheight before divide?
  private double cellSize;

  private double getCellSize() {
    double xSize = getSize().width / xCellCount;
    double ySize = getSize().height / yCellCount;
    return (xSize < ySize) ? xSize : ySize;
  }

  private void addRacer(Selector player, int tag) {
    Racer racer = player.getRacer();
    racer.setTag("P" + tag);
    gs.racers.add(racer);
  }

  private void addBots() {
    Random rnd = new Random();
    int rndIndex = rnd.nextInt(models.length);
    for(int i = rndIndex; (i < rndIndex + models.length) && (i < rndIndex + options.getBotCount()); ++i) {
      int modelIndex = (i < models.length) ? i : i - models.length;
      Bot bot = new Bot(models[modelIndex]);
      bot.setTag("B" + (modelIndex + 1));
      gs.racers.add(bot);
    }
  }

  private void genMap() {
    AbstractMap bm = new AbstractMap(options.getWidth(), options.getLength(), 6);
    // 6 is pivot count; loosely related to bezier curviness
    WallMap gm = new WallMap(bm, options.getWallWidth());

    gs.walls = gm.walls;
    gs.finish = gm.finish;

    for (Racer racer : gs.racers) {
      racer.pn = bm.start;
    }
  }

  @Override
  public void update(long deltaTime) {

    selectorOne.update(deltaTime);
    selectorTwo.update(deltaTime);
    options.update(deltaTime);
    repaint();

    if (selectorOne.getReady() && selectorTwo.getReady()) {

      gs.clear();

      Racer racerOne = selectorOne.getRacer();
      Racer racerTwo = selectorTwo.getRacer();
      racerOne.setTag("P1");
      racerTwo.setTag("P2");
      gs.racers.add(racerOne);
      gs.racers.add(racerTwo);
      addBots();

      genMap();

      selectorOne.reset();
      selectorTwo.reset();

      engine.loadRenderer(racerOne, racerTwo);
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

  public MainMenu(GameState gs, Master engine) {
    this.engine = engine;
    this.gs = gs;

    models = new Model[3];
    models[0] = new Control();
    models[1] = new Balance();
    models[2] = new Speed();

    Input input = new Input();
    selectorOne = new Selector(gs.controllerOne, models);
    selectorTwo = new Selector(gs.controllerTwo, models);
    options = new Options(gs.mouse);
  }
}
