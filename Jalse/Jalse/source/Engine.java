/**
* A physics manager that moves objects and handles collisions.
* @author Glen Wang
* @version 1.0
* @date June 11, 2019
*/
public class Engine implements Updatable {

  private Master master;
  private GameState gameState;

  private void updateParticles(long deltaTime) {
    for (Particle particle : gameState.particles) {
      particle.update(deltaTime);
    }
  }

  private void checkCollisions(Racer racer) {
    racer.setColliding(false);
    for (Wall wall : gameState.walls) {
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
    for (Racer racer : gameState.racers) {
      allWon = false; // loop is only entered if racers remain
      racer.update(deltaTime);
      checkCollisions(racer);
      if (gameState.finish.getCollider().intersects(racer.getCollider())) {
        racer.win();
        gameState.racersDone.add(racer);
      }
    }
    for (Racer racer : gameState.racersDone) {
      gameState.racers.remove(racer);
    }
    if (allWon) {
      master.loadResults();
    }
    updateParticles(deltaTime);
  }

  /**
	* Constructs an engine that simulates a game state,
  * and communicates with a master.
	* @param gameState The current game state.
  * @param master The current master.
	*/
  public Engine(GameState gameState, Master master) {
    this.gameState = gameState;
    this.master = master;
    gameState.controllerOne.awaitLift();
    gameState.controllerTwo.awaitLift();
  }
}
