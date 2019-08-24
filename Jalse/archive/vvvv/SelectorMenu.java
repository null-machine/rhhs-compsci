import javax.swing.JPanel;

import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Graphics;

import java.util.ArrayList;

// chara selection
public class SelectorMenu extends JPanel {

  private Jalse jalse;

  private ArrayList<Racer> racers;
  private Selector[] selectors;
  private Model[] models;
  private int xGrid;
  private int yGrid;

  private boolean running = true;

  public SelectorMenu(Jalse jalse) {
    this.jalse = jalse;

    selectors = new Selector[2];
    models = new Model[3];
    models[0] = new Control();
    models[1] = new Balance();
    models[2] = new Speed();

    Input input = new Input();
    selectors[0] = new Selector(new Arrows(input), models);
    selectors[1] = new Selector(new WASD(input), models);
    // selectors[2] = new Selector(new WASD(input), models);

    Thread updater = new Thread(new Updater());
    updater.start();
  }

  private class Updater implements Runnable {
    public void run() {
      boolean allReady;
      while (racers == null) {
        // selector.render(g);
        allReady = true;
        for (int i = 0; i < selectors.length; ++i) {
          Selector selector = selectors[i];
          selector.update();
          selector.render();
          if (selector.getRacer() == null) {
            allReady = false;
          }
        }
        if (allReady) {
          racers = new ArrayList<Racer>();
          for (int i = 0; i < selectors.length; ++i) {
            racers.add(selectors[i].getRacer());
          }
          jalse.startEngine(racers);
        }
      }
    }
  }
}
