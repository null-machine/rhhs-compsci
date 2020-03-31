// Author : SUNNY 

import java.util.HashMap;
import java.util.Map;



public class Entity implements Cloneable {
  
  public static Map<String, Entity> entities = new HashMap<String, Entity>();
  //class variables
  public int x, y, w = 32, h = 32;
  public int hp, attack, defense;
  public String name, frame;
  public Bitmap[] frames;
  public boolean removed = false;
  public boolean hostile = true;
  
  private int frameInterval = 250, framesOfAnimation = 0;
  private long frameTimer = System.currentTimeMillis();
  //HP AND DEF STATS ARE SCRAPPED IDEAS. NOT USED IN PROGRAMMING ANYMORE
  /* make an entity object
   * @param name of our entity
   * @param frame how many frames this enemy will go through
   * other variables are no longer used but thought to use in the beginning of our project
   * 
   */
  public Entity(String name, String frame, int hp, int atk, int def) {
    this.name = name;
    this.hp = hp;
    attack = atk;
    defense = def;
    this.frame = frame;
    
    if(frame.equals("")){//if the xml file does not specifie a frame
      return;
    }
    int[] frames = new int[2];
    //splits the frames into two index values
    frames[0] = Integer.parseInt(frame.split(",")[0]);
    frames[1] = Integer.parseInt(frame.split(",")[1]);
    this.frames = new Bitmap[2];//make a bitmap array to store the frames
    this.frames[0] = Art.sprites[frames[0]][frames[1]];//first frame
    this.frames[1] = Art.sprites[frames[0] + 1][frames[1]];//second frame is right to the first frame
  }
  
   /* set position of an entity object
   * @param x location of entity at x location
   * @param y location of entity at y location
   */
  public void setPos(int x, int y) {
    this.x = x;
    this.y = y;
  }
  
  /* render the enetity onto the screen
   * @param screen the screen object we are rendering onto
   */
  public void render(Screen screen) {
    screen.render(frames[framesOfAnimation], x * Tile.SIZE + Level.X_OFFSET, y * Tile.SIZE + Level.Y_OFFSET);
  }
  /* remove the entity
   */
  public void remove() {
    removed = true;
    if(hostile) {
      
      UI.verbose(name + " was slain!");
    }
  }
  /* update the entity's frame animation
   */
  public void tick() {
    if(System.currentTimeMillis() - frameTimer > 2 * frameInterval) {
      frameTimer = System.currentTimeMillis();
    }
    if(System.currentTimeMillis() - frameTimer > frameInterval && frames != null)  {
      if(framesOfAnimation >= frames.length - 1) framesOfAnimation = 0;
      else framesOfAnimation++;
      frameTimer += frameInterval;
    }
  }
    /* create a new instance of the entity class
     * @param key find the entity in the hashmap
   */
  public static Entity newInstance(String key) {
    try {
      return (Entity) entities.get(key).clone();
    } catch (CloneNotSupportedException e) {
      e.printStackTrace();
    }
    return null;
  }
  
  private Player player;
  
  /* let entity interact with the player
     * @param player the player object u are interacting with
   */
  public void interact(Player player) {
    if(hostile) {
      this.player = player;
      UI.combat(player, this);
    }
    
    
  }
  
  /* render entity onto screen
     * @param screen the screen object
     * @param i y coord location
     * @param j coord location
   */
  public void render(Screen screen, int i, int j) {
    screen.render(frames[framesOfAnimation], i, j);
  }
  
}
