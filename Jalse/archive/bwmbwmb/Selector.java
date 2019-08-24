import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Graphics;

import java.util.Random;

public class Selector {
  // three of these make up the menu
  // is rendered as a card

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

  private GeoVector cellSize;
  private Rectangle background;
  private Rectangle modelPreview;
  private Color modelColor;

  private GeoVector oldDir;

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
    System.out.println(this + "\t " + models[modelIndex] + "\t " + state);
  }

  public void render() {

  }

  public Selector(Controller controller, Model[] models) {
    this.controller = controller;
    this.models = models;
    state = State.AI;
    modelIndex = (int)(new Random().nextDouble() * models.length);
  }
}
