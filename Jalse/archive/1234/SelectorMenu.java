import javax.swing.JPanel;

import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Graphics;

import java.util.ArrayList;

// chara selection
public class SelectorMenu extends JPanel {

  // E:\Resources\jdk-12.0.1\bin\javac.exe *.java & E:\Resources\jdk-12.0.1\bin\java.exe Launcher.java

  private Jalse jalse;

  private ArrayList<Racer> racers;
  private Selector[] selectors;
  private Model[] models;
  private double cellSize;
  private final int xCellCount = 26;
  private final int yCellCount = 14;

  private boolean running = true;

  private double getCellSize() {
    double xSize = getSize().width / xCellCount;
    double ySize = getSize().height / yCellCount;
    return (xSize < ySize) ? xSize : ySize;
  }

  public SelectorMenu(Jalse jalse) {
    this.jalse = jalse;

    selectors = new Selector[2];
    models = new Model[3];
    models[0] = new Control();
    models[1] = new Balance();
    models[2] = new Speed();

    Input input = new Input();
    selectors[0] = new Selector(new WASD(input), models);
    selectors[1] = new Selector(new Arrows(input), models);
    // selectors[2] = new Selector(new WASD(input), models);

    Thread updater = new Thread(new Updater());
    updater.start();
  }

  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    setDoubleBuffered(true);
    setBackground(Color.BLACK);

    for (int i = 0; i < selectors.length; ++i) {
      selectors[i].render(g, i, getCellSize());
    }
    repaint();
  }

  private class Updater implements Runnable {
    public void run() {
      boolean allReady = false;
      while (!allReady) {
        allReady = true;
        for (int i = 0; i < selectors.length; ++i) {
          Selector selector = selectors[i];
          selector.update();
          if (selector.getRacer() == null) {
            allReady = false;
          }
        }
        repaint();
        try { Thread.sleep(20); } catch (Exception exc) {}
      }

      Racer racer;
      racers = new ArrayList<Racer>();
      for (int i = 0; i < selectors.length; ++i) {
        racer = selectors[i].getRacer();
        racer.setTag(i);
        racers.add(selectors[i].getRacer());
      }
      jalse.startEngine(racers);
    }
  }
}
