//AUTHOR: SUNNY

public class Stairs extends Entity {
  
  int dir;
  GameLauncher game;
  int tx, ty;
  /*create a stairs object
     *@param game the game launcher
     *@param dir the direction the stair is facing
     *@param tx where to transport the player in x dir
     *@param ty where to transport the player in y dir
     */
  public Stairs(GameLauncher game, int dir, int tx, int ty) {
    super("", "", 1, 0, 10000000);//create a new entity
    hostile = false;//player does not fight the stairs
    this.game = game;
    this.dir = dir;
    this.frames = new Bitmap[1];//create a bitmap that array to display stairs
    if (dir == 0){
      this.frames[0] = Art.sprites[4][6];
    } else{
      this.frames[0] = Art.sprites[4][5];
    }
    
    
    
    
    this.tx = tx;
    this.ty = ty;
  }
  
   /*interact
     *@param player the player object the stair is interacting with
     */
  public void interact(Player player) {
    switch(dir) {
      case 0:
        game.upFloor(tx, ty);
        break;
      case 1:
        game.downFloor(tx, ty);
        break;
    }
    player.lastMove = System.currentTimeMillis() + 1500;
  } 
}
