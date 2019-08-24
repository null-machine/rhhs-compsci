import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;

import java.util.Random;

public class Selector {

  private Controller controller;
  private Racer racer;
  public Racer getRacer() { return racer; }

  private Model[] models;
  private int modelIndex;
  private void setModelIndex(int dir) {
    if (oldDir == GeoVector.zero) {
      modelIndex += dir;
      if (modelIndex > models.length - 1) {
        modelIndex = 0;
      } else if (modelIndex < 0) {
        modelIndex = models.length - 1;
      }
    }
  }

  private enum State { AI, MODELS, READY }
  private State state;
  private void setState(int dir) {
    if (oldDir == GeoVector.zero) {
      int newState = state.ordinal() + dir;
      if (newState < 0) {
        newState = 0;
      } else if (newState >= State.values().length) {
        newState = State.values().length - 1;
      } else {
        state = State.values()[state.ordinal() + dir];
      }
    }
  }

  private GeoVector oldDir;
  private double cellSize;
  private int index; // rename to cellindex

  protected void renderBox(Graphics g, Color color, int x, int y, int w, int h) {
    int offset = index * 8;
    g.setColor(color);
    g.fillRect(
      (int)(cellSize * (x + offset)),
      (int)(cellSize * y),
      (int)(cellSize * w),
      (int)(cellSize * h)
    );
  }

  protected void renderText(Graphics g, Color color, int x, int y, double xPres, double yPres, String text) {
    int offset = index * 8;
    g.setColor(color);
    g.setFont(new Font("Consolas", Font.BOLD, (int)(cellSize / 2)));
    g.drawString(
      text,
      (int)(cellSize * (x + offset) + cellSize * xPres),
      (int)(cellSize * y + cellSize * yPres)
    );
  }

  public void render(Graphics g, int index, double cellSize) {
    // simulates gridbaglayout with consistent cell size
    this.cellSize = cellSize;
    this.index = index;

    switch (state) {
      case AI:
        renderBox(g, Color.GRAY, 2, 2, 6, 10);
        renderBox(g, Color.LIGHT_GRAY, 3, 5, 4, 2);
        renderText(g, Color.BLACK, 3, 5, 0.5, 0.6, "MOVE DOWN"); // set by controller
        renderText(g, Color.BLACK, 3, 6, 0.5, 0.6, " TO JOIN");
        break;
      case MODELS:
        renderBox(g, Color.LIGHT_GRAY, 2, 2, 6, 10);
        renderBox(g, Color.WHITE, 3, 3, 4, 1);
        renderBox(g, Color.WHITE, 3, 8, 4, 3);
        renderBox(g, models[modelIndex].color, 4, 5, 2, 2);
        renderText(g, Color.BLACK, 2, 5, 1.0, 1.2, "<");
        renderText(g, Color.BLACK, 6, 5, 0.7, 1.2, ">");
        renderText(g, Color.BLACK, 3, 3, 0.5, 0.6, "PLAYER ONE"); // set by list index
        renderText(g, Color.BLACK, 3, 8, 0.2, 0.6, "SPEED:  " + models[modelIndex].stats[0]); // set by model
        renderText(g, Color.BLACK, 3, 9, 0.2, 0.6, "ACCEL:  " + models[modelIndex].stats[1]);
        renderText(g, Color.BLACK, 3, 10, 0.2, 0.6, "REPAIR: " + models[modelIndex].stats[2]);
        renderText(g, Color.WHITE, 3, 12, 0.2, 0.6, "MOVE DOWN TO READY");
        break;
      case READY:
        renderBox(g, Color.WHITE, 2, 2, 6, 10);
        renderText(g, Color.BLACK, 4, 6, 0.5, 0.6, "READY");
        break;
    }
  }

  public void update() {
    GeoVector dir = controller.getDir();
    setState((int)dir.y);
    switch (state) {
      case AI:
        break;
      case MODELS:
        racer = null;
        setModelIndex((int)dir.x);
        break;
      case READY:
        racer = new Racer(controller, models[modelIndex]);
        break;
    }
    oldDir = dir;
  }

  public Selector(Controller controller, Model[] models) {
    this.controller = controller;
    this.models = models;
    state = State.AI;
    modelIndex = (int)(new Random().nextDouble() * models.length);
  }
}
