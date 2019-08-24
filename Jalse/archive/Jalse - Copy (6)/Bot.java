import java.awt.Rectangle;
import java.awt.Color;

import java.util.Random;

/**
* A racer that's controlled by a bot basic pathfinding.
* Gives players a head start for fairness.
* @author Glen Wang
*/
public class Bot extends Racer {

  // not final because it's randomized
  private double readyTime = 1000;

  private GeoVector orthogonalize(GeoVector vector) {
    return vector.normalize().round().normalize();
  }

  @Override
  public void update(long deltaTime) {
    readPos = getPos();
    if (readyTime > 0) {
      readyTime -= deltaTime;
      return;
    }

    if (getDamage() > 0) {
      repair(deltaTime);
    } else {
      if (pn.next != null) {
        move(orthogonalize(pn.next.pos.sub(readPos)));
      } else {
        move(orthogonalize(pn.pos.sub(readPos)));
      }
    }
    updatePos(deltaTime);
    updateNode();
    updateCollider(model.size);
  }

  @Override
  public int hashCode() {
    return model.hashCode();
  }

  /**
  * Constructs a bot with a model.
  * @param model The model of this bot.
  */
  public Bot(Model model) {
    super(null, model);
    Random rnd = new Random();
    readyTime += readyTime * rnd.nextDouble();
  }
}
