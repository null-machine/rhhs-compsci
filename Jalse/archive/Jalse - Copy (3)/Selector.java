import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;

import java.util.Random;

public class Selector extends CardMenu {

  public Racer getRacer() { return new Racer(controller, models[modelIndex]); }

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

  private enum State { WAITING, MODELS, READY }
  private State state;
  public boolean getReady() { return (state == State.READY); }
  public void reset() { state = State.WAITING; }
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

  @Override
  public void render(Graphics g, int cellIndex, double cellSize) {
    super.render(g, cellIndex, cellSize);

    switch (state) { // hard code is virtually impossible to avoid for gui this specialized
      case WAITING:
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
        renderText(g, Color.BLACK, 3, 3, 0.5, 0.6, "PLAYER " + (cellIndex / 2 + 1));
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

  public void update(long deltaTime) { // make deltatime long?
    GeoVector dir = controller.getDir();
    setState((int)dir.y);
    switch (state) {
      case WAITING:
        break;
      case MODELS:
        setModelIndex((int)dir.x);
        break;
      case READY:
        break;
    }
    oldDir = dir;
  }

  public Selector(Controller controller, Model[] models) {
    super(controller);
    this.models = models;
    state = State.WAITING;
    modelIndex = (int)(new Random().nextDouble() * models.length);
  }
}
