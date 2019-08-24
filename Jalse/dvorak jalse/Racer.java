/**
* A moving wall controlled by players and bots.
* It leaves particle trails when it moves and
* particle bursts when it collides.
* It behaves according to statistics provided by its model.
* @author Glen Wang
* @version 1.0
* @date June 11, 2019
*/
public class Racer extends Wall implements Updatable {

  // related to particle effects
  private final int SPARK_COUNT = 12;
  private final double SPARK_MAG = 4;
  private final int WIN_COUNT = 48;
  private final double WIN_MAG = 8;

  private Controller controller;
  protected PathNode pathNode;
  protected GeoVector readPos; // read only position
  protected GeoVector acc;
  private GeoVector vel;

  /**
  * Gets this racer's velocity.
  * @return This racer's current velocity.
  */
  public GeoVector getVel() {
    return vel;
  }

  protected Model model;

  /**
  * Returns this racer's model.
  * @return This racer's model.
  */
  public Model getModel() {
    return model;
  }

  private int damage;

  /**
  * Gets the amount of damage this racer has to repair before it can move.
  * @return This racer's damage.
  */
  public int getDamage() {
    return damage;
  }

  private boolean colliding;

  /**
  * Gets whether this racer is inside a wall.
  * @return Whether this racer is inside a wall.
  */
  public boolean getColliding() {
    return colliding;
  }

  /**
  * Sets whether this racer is inside a wall.
  * @param colliding Whether this racer is inside a wall.
  */
  public void setColliding(boolean colliding) {
    this.colliding = colliding;
  }

  private String tag;

  /**
  * Gets this racer's tag.
  * @return This racer's tag.
  */
  public String getTag() {
    return tag;
  }

  /**
  * Sets this racer's tag.
  * @param tag This racer's tag.
  */
  public void setTag(String tag) {
    this.tag = tag;
  }

  // end of attributes, start of methods

  protected void repair(long deltaTime) {
    acc = GeoVector.zero;
    damage = (deltaTime < damage) ? (int)(damage - deltaTime) : 0;
    setColor(model.color.darker().darker());
  }

  protected void move(GeoVector dir) {
    acc = dir;
    acc = acc.mult(model.control);
    setColor(model.color);
    ParticlePool.spawn(readPos, vel.neg(), model.size, model.color);
  }

  protected void updatePos(long deltaTime) {
    vel = vel.add(acc);
    vel = vel.mult(model.drag);
    setPos(readPos.add(vel.mult(deltaTime)));
  }

  protected void updateNode() { // finds closest node
    double currDist = readPos.dist(pathNode.pos);
    double nextDist = (pathNode.next != null) ? readPos.dist(pathNode.next.pos) : Double.POSITIVE_INFINITY;
    double prevDist = (pathNode.prev != null) ? readPos.dist(pathNode.prev.pos) : Double.POSITIVE_INFINITY;

    while (nextDist < currDist) {
      pathNode = pathNode.next;
      currDist = readPos.dist(pathNode.pos);
      nextDist = (pathNode.next != null) ? readPos.dist(pathNode.next.pos) : Double.POSITIVE_INFINITY;
    }
    while (prevDist < currDist) {
      pathNode = pathNode.prev;
      currDist = readPos.dist(pathNode.pos);
      prevDist = (pathNode.prev != null) ? readPos.dist(pathNode.prev.pos) : Double.POSITIVE_INFINITY;
    }
  }

  @Override
  public void update(long deltaTime) {
    readPos = getPos();
    if (damage > 0) {
      repair(deltaTime);
    } else {
      move(controller.getDir());
    }
    updatePos(deltaTime);
    updateNode();
    updateCollider(model.size);
  }

  /**
  * Plays the racer collision particle effects and
  * bounces it backwards.
  */
  public void collide() {
    if (damage == 0) {
      for (int i = 0; i < SPARK_COUNT; ++i) {
        ParticlePool.spawn(readPos, GeoVector.zero.randInRadius(SPARK_MAG), model.size, model.color);
      }
      damage = model.fragility;
      model.soundCollide();
    }
    vel = vel.neg();
    GeoVector dir = pathNode.pos.sub(readPos).normalize();
    setPos(readPos.add(dir));
  }

  /**
  * Plays the racer win particle effects and resets it.
  */
  public void win() {
    GeoVector winVel = GeoVector.up.mult(WIN_MAG);
    double winAng = Math.PI * 2 / WIN_COUNT;
    for (int i = 0; i < WIN_COUNT; ++i) {
      ParticlePool.spawn(readPos, winVel.rotate(i * winAng), model.size, model.color);
    }
    damage = 0;
    setColor(model.color);
    model.soundWin();
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof Racer)) {
      return false;
    }
    Racer other = (Racer)o;
    return (tag == other.tag);
  }

  @Override
  public int hashCode() {
    return tag.hashCode();
  }

  /**
  * Constructs a racer controlled by a controller and
  * with the abilities of a model.
  * @param controller The controller that moves this racer.
  * @param model The model from which this racer gets its stats.
  */
  public Racer(Controller controller, Model model) {
    super(GeoVector.zero, model.size, model.color);

    this.controller = controller;
    this.model = model;

    acc = GeoVector.zero;
    vel = GeoVector.zero;
  }
}
