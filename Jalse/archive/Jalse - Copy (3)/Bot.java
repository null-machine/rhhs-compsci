import java.awt.Rectangle;
import java.awt.Color;

import java.util.Random;

public class Bot extends Racer {

  private double readyTime = 1000;

  @Override
  public void update(long deltaTime) {
    if (readyTime > 0) {
      readyTime -= deltaTime;
      return;
    }
      if (damage > 0) {
        repair(deltaTime);
      } else {
        if (pn.next != null) {
          // move orthogonally
          move(pn.next.pos.sub(pos).normalize().round().normalize());
        } else {
          move(pn.pos.sub(pos).normalize().round().normalize());
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

  public Bot(Model model) {
    super(null, model);
    Random rnd = new Random();
    readyTime += readyTime * rnd.nextDouble();
  }
}
