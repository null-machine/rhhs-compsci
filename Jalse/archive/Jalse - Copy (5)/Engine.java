import java.util.List;
import java.util.Set;

/**
* A physics manager that moves objects and handles collisions.
* @author Glen Wang
*/
public class Engine implements Updatable {

  private Master master;
  private GameState gs;

  private void updateParticles(long deltaTime) {
    for (Particle particle : gs.particles) {
      particle.update(deltaTime);
    }
  }

  private void checkCollisions(Racer racer) {
    boolean colliding = racer.getColliding();
    racer.setColliding(false);
    for (Wall wall : gs.walls) {
      if (racer.getCollider().intersects(wall.getCollider())) {
        if (!racer.getColliding()) {
          racer.collide();
        }
        racer.setColliding(true);
      }
    }
  }

  @Override
  public void update(long deltaTime) {
    boolean allWon = true;
    for (Racer racer : gs.racers) {
      allWon = false; // loop is only entered if racers remain
      racer.update(deltaTime);
      checkCollisions(racer);
      if (gs.finish.getCollider().intersects(racer.getCollider())) {
        racer.win();
        gs.racersDone.add(racer);
      }
    }
    for (Racer racer : gs.racersDone) {
      gs.racers.remove(racer);
    }
    if (allWon) {
      master.loadResults();
    }
    updateParticles(deltaTime);
  }

  /**
	* Constructs an master that simulates a game state,
  * and communicates with a master.
	* @param gs The current game state.
  * @param master The current master.
	*/
  public Engine(GameState gs, Master master) {
    this.gs = gs;
    this.master = master;
    gs.controllerOne.awaitLift();
    gs.controllerTwo.awaitLift();
  }
}
