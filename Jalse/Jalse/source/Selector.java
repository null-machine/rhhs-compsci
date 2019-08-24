import java.awt.Color;
import java.awt.Graphics;

import java.util.Random;

/**
* A section of the menu that allows players to choose their racer model.
* It scales with screen size to keep everything operational.
* @author Glen Wang
* @version 1.0
* @date June 11, 2019
*/
public class Selector extends CardMenu {

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
  private State oldState;
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

  /**
  * Gets whether this has selected a model.
  * @return Whether this has selected a model.
  */
  public boolean getReady() {
    return (state == State.READY);
  }

  /**
  * Gets a racer with the model this has selected.
  * @return A racer with this selector's model.
  */
  public Racer getRacer() {
    return new Racer(controller, models[modelIndex]);
  }

  /**
  * Resets this selector so that it can select a new model.
  */
  public void reset() {
    state = State.WAITING;
    oldState = State.WAITING;
    controller.awaitLift();
  }

  @Override
  public void render(Graphics g, int cellIndex, double cellSize) {
    super.render(g, cellIndex, cellSize);

    switch (state) { // hard code is virtually impossible to avoid for gui this specialized
      case WAITING:
        renderBox(g, Color.GRAY, 2, 2, 6, 10);
        renderBox(g, Color.LIGHT_GRAY, 3, 5, 4, 2);
        renderText(g, Color.BLACK, 3, 5, 0.5, 0.6, "MOVE DOWN");
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
        renderText(g, Color.BLACK, 3, 8, 0.2, 0.6, "SPEED:  " + models[modelIndex].stats[0]);
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

  @Override
  public void update(long deltaTime) {
    GeoVector dir = controller.getDir();
    setState((int)dir.y);
    switch (state) {
      case WAITING:
        break;
      case MODELS:
        setModelIndex((int)dir.x);
        break;
      case READY:
        if (oldState != State.READY) {
          models[modelIndex].soundSelect();
        }
        break;
    }
    oldState = state;
    oldDir = dir;
  }

  /**
  * Constructs a selector that responds to a controller,
  * and lets players choose between models.
  * @param controller This selector's controller.
  * @param models The models this selector cycles between.
  */
  public Selector(Controller controller, Model[] models) {
    super(controller);
    this.models = models;
    modelIndex = (int)(new Random().nextDouble() * models.length);
    reset();
  }
}
