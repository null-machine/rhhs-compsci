import java.awt.Rectangle;
import java.awt.Color;

public class Bot extends Racer {

  @Override
  public void update(double deltaTime) {
    if (damage > 0) {
      repair();
    } else {
      if (pn.next != null) {
        // move orthogonally
        move(pn.next.pos.sub(pos).normalize().round().normalize());
      } else {
        move(pn.pos.normalize().round().normalize());
      }
    }
    updatePos(deltaTime);
    updateNode();
    updateCollider(model.size);
  }

  public Bot(Model model) {
    super(null, model);
  }
}
