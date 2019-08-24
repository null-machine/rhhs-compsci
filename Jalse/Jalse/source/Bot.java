import java.util.Random;

/**
* A racer that's controlled by a bot with basic pathfinding.
* Gives players a head start for fairness.
* @author Glen Wang
* @version 1.0
* @date June 11, 2019
*/
public class Bot extends Racer {

  private final double maxReadyTime = 2000;
  private double readyTime;

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
    } else { // move to the next node or last node
      if (pathNode.next != null) {
        move(orthogonalize(pathNode.next.pos.sub(readPos)));
      } else {
        move(orthogonalize(pathNode.pos.sub(readPos)));
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
    readyTime += maxReadyTime * rnd.nextDouble();
  }
}
