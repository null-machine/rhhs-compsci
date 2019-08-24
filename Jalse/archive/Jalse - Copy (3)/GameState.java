import java.util.HashSet;
import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
* GameState.java
* A data container holding shared information.
* One of these is created on initialization, and manipulated by all classes.
* @author Glen Wang
*/
public class GameState {
	public Set<Racer> racers;
  public ArrayList<Racer> racersDone;
  public HashSet<Wall> walls;
  public Wall finish;
  public PoolingQueue<Particle> particles;

	public Controller controllerOne;
	public Controller controllerTwo;
	public Controller mouse;

	public void clear() {
		racers.clear();
		racersDone.clear();
		walls.clear();
		finish = null;
	}

	public GameState() {
		// racers = new HashSet<Racer>();
		racers = ConcurrentHashMap.newKeySet();
		racersDone = new ArrayList<Racer>();
		walls = new HashSet<Wall>();
		ParticlePool pp = new ParticlePool(200); // just has to be big
		particles = pp.getParticles();

		Input input = new Input();
		controllerOne = new WASD(input);
		controllerTwo = new Arrows(input);
		mouse = new Mouse(input);
	}
}
