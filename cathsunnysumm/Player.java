//AUTHOR: SUNNY

import java.util.HashMap;
import java.util.Map;


public class Player extends Entity {//player inherits from the entity class
  
  private static final int DIRECTION_UP = 0, DIRECTION_DOWN = 1, DIRECTION_LEFT = 2, DIRECTION_RIGHT = 3;
  private HashMap<String, Integer> playerStats = new HashMap<String, Integer>();//string will be what the stat is, integer is the value of the stat
  int playerFacingDirection = DIRECTION_UP;
  private int frame = 0;//the frame that will be rendered
  public long lastMove = System.currentTimeMillis();//record last time we moved player
  public long frameTick = System.currentTimeMillis();//last time our frame was updated
  private short moveInterval = 100; //time diff in millis between frames
  
  private static Bitmap[][] frames = {
    { Art.sprites[9][19], Art.sprites[8][19] },//player facing up
    { Art.sprites[9][19], Art.sprites[8][19] },//player facing left
    { Art.sprites[9][19], Art.sprites[8][19] },//player facing down
    { Art.sprites[9][19], Art.sprites[8][19] }//player facing down
  };
  
  public boolean canmove = true;//if the player can actually move. will turn it false when the user uses the shop, so as they are using keyboard they don't move the player
  
  private InputHandler input;
  private GameLauncher game;
  public Level level;//know which level the player is on
  
  
  /* create a player object
   * @param input give input handler to move player
   * @param game game object
   * @param level to access object information
   */
  public Player(InputHandler input, GameLauncher game, Level level) {
    super("Player", "", 1, 10, 10);
    
    
    putProperty("stat.hp", 15897);
    
    putProperty("stat.attack", 680);
    putProperty("stat.defense", 561);
    
    
    
    this.game = game;
    this.input = input;
    this.level = level;
    hostile = false;//entity variable that ensures it doesn't fight itself
  }
  
   /* give player stats into hashmap
   * @param key the key to the hashmap
   * @param value value to the corresponding key
   */
  public void putProperty(String key, int value) {
    playerStats.put(key, value);
    if(playerStats.get(key) < 0) {
      playerStats.put(key, 0);
    }
    
  }
  
  
 /* level up player
   
   */
  public void levelUp() {
    playerStats.put("stat.hp", playerStats.get("stat.hp") + 750);
    playerStats.put("stat.attack", playerStats.get("stat.attack") + 7);
    playerStats.put("stat.defense", playerStats.get("stat.defense") + 7);
  }
  
  /* get the stats of player when needed for combat
   * @param key the specific stat we want to know
   
   */
  public int getProperty(String key) {
    return playerStats.get(key);
  }
  
  /* update player variable
   
   */
  public void tick() {
    if(!canmove) lastMove = System.currentTimeMillis();
    if(input.up.down) {//if the up button is pressed down
      if(!canmove) {//check if the player can actually move
        return;
      }
      this.playerFacingDirection = DIRECTION_UP;//make the player face the upward direction
      move(DIRECTION_UP, 1);//move the player up 1 space
    } else if(input.down.down) {
      if(!canmove) return;
      this.playerFacingDirection = DIRECTION_DOWN;
      move(DIRECTION_DOWN, 1);
    } else if(input.left.down) {
      if(!canmove) return;
      this.playerFacingDirection = DIRECTION_LEFT;
      move(DIRECTION_LEFT, 1);
    } else if(input.right.down) {
      if(!canmove) return;
      this.playerFacingDirection = DIRECTION_RIGHT;
      move(DIRECTION_RIGHT, 1);
    } else {   
      if(System.currentTimeMillis() - lastMove > moveInterval) {
        lastMove += moveInterval;//used to update the time of the last time we moved. we don't want the player to zoom through the screen
      }
    }
    
    //if the animation is at its second frame and the time different is greater than 200ms, switch to first frame
    if(frame == 1 && System.currentTimeMillis() - frameTick > 200) {
      frame = 0;
      frameTick = System.currentTimeMillis();
    }
  }
  //used to move the player
  /* 
   *@param dir the direction the player moves
   *@param the amount the player will move in that direction
   */
  public void move(int dir, int amount) {
    if(!canmove) {
      return;
    }
    
    //if we are trying to move when the time between moves is too short, we don't move
    if(System.currentTimeMillis() - lastMove < moveInterval) {
      return;
    }
    
    
    int intendedDirection = -1;//this is the direction the player intends to move. might not be able to move cuz a tile is blocking the player
    if(dir == DIRECTION_LEFT) {
      intendedDirection = DIRECTION_LEFT;
      if(checkIfCanMove(DIRECTION_LEFT)) {
        x -= amount;//move the player to the left direction by subtracting from x
        frame = 1;//update animation
        frameTick = System.currentTimeMillis();//update the frame tick so the animation can alternate
        
      }
    }
    if(dir == DIRECTION_RIGHT) {
      intendedDirection = DIRECTION_RIGHT;
      if(checkIfCanMove(DIRECTION_RIGHT)) {
        x += amount;
        frame = 1;
        frameTick = System.currentTimeMillis();
      }
    }
    if(dir == DIRECTION_UP) {
      intendedDirection = DIRECTION_UP;
      if(checkIfCanMove(DIRECTION_UP)) { 
        y -= amount;
        frame = 1;
        frameTick = System.currentTimeMillis();
      }
    }
    if(dir == DIRECTION_DOWN) {
      intendedDirection = DIRECTION_DOWN;
      if(checkIfCanMove(DIRECTION_DOWN)) {
        y += amount;
        frame = 1;
        frameTick = System.currentTimeMillis();
      }
    }
    
    //find the enemy that the player is facing. if the player is not facing an enemy, getEntityAt will return null
    Entity facing = level.getEntityAt(intendedDirection, x, y);
    if(facing != null && facing.hostile) {//if monster exists and the entity we are facing is hostil
      move(invertDir(intendedDirection), 1);//move to the square right beside it
      facing.interact(this);//interact with the hostil player
    } 
    if(facing instanceof Stairs) {
      ((Stairs) facing).interact(this);
    }
    if(facing instanceof NPC) {
      move(invertDir(intendedDirection), 1);
      (facing).interact(this);
      
      
      
    }
    lastMove += moveInterval;
  }
  
  //used to move the player
  /* 
   *@param dir invert direction of player
   */
  public int invertDir(int dir) {//get opposite direction
    if(dir == DIRECTION_UP) {
      return DIRECTION_DOWN;
    }
    if(dir == DIRECTION_DOWN) {
      return DIRECTION_UP;
    }
    if(dir == DIRECTION_LEFT) {
      return DIRECTION_RIGHT;
    }
    if(dir == DIRECTION_RIGHT) {
      return DIRECTION_LEFT;
    }
    return 0;
  }
  
  
  /* 
   *@param dir check if player can move in that direction
   * @return true or false if they can move
   */
  private boolean checkIfCanMove(int dir) {
    if(dir == DIRECTION_LEFT) {//if the direction we are trying to move is left
      
      //in the tile class get the tile object that is directly to the left of the player
      Tile tileBlockingMove = level.getTileAt(x - 1, y);
      if(tileBlockingMove != null && !tileBlockingMove.isSolid()) {//if that tile exists and it is NOT a solid
        return true;//the player can move
      }
      else {
        return false;//if it is either a solid or it does not exist, the player can't move
      }
    }
    if(dir == DIRECTION_RIGHT) {
      Tile tileBlockingMove = level.getTileAt(x + 1, y);
      if(tileBlockingMove != null && !tileBlockingMove.isSolid()) {
        return true;
      }
      else {
        return false;
      }
    }
    if(dir == DIRECTION_UP) {
      Tile tileBlockingMove = level.getTileAt(x, y - 1);
      if(tileBlockingMove != null && !tileBlockingMove.isSolid()){
        return true;
      }
      else {
        return false;
      }
    }
    if(dir == DIRECTION_DOWN) {
      Tile tileBlockingMove = level.getTileAt(x, y + 1);
      if(tileBlockingMove != null && !tileBlockingMove.isSolid()){
        return true;
      }
      else {
        return false;
      }
    }
    return false;
  }
  
  
  /* render the player to our screen
   *@param screen check if player can move in that direction
   * @return true or false if they can move
   */
  public void render(Screen screen) {
    //render the characters frame animation at a position on the screen
    screen.render(frames[playerFacingDirection][frame], x * Tile.SIZE + Level.X_OFFSET, y * Tile.SIZE + Level.Y_OFFSET);
  }
  
}
