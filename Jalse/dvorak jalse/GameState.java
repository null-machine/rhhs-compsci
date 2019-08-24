import java.util.HashSet;
import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
* A data container holding shared information.
* One of these is created on initialization, and manipulated by all classes.
* @author Glen Wang
* @version 1.0
* @date June 11, 2019
*/
public class GameState {

	// properties public because this is a data container, like GeoVector
	public Set<Racer> racers;
  public ArrayList<Racer> racersDone;
  public HashSet<Wall> walls;
  public Wall finish;
  public PoolingQueue<Particle> particles;

	public Controller controllerOne;
	public Controller controllerTwo;
	public Controller mouse;

	/**
	* Prepares the game state for a new race track by
	* clearing the racers, walls and finish.
	*/
	public void clear() {
		racers.clear();
		racersDone.clear();
		walls.clear();
		finish = null;
	}

	/**
	* Constructs a new game state by populating all the persistent properties
	* and allocating memory for the others.
	*/
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
