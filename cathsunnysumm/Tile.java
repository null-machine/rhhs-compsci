//AUTHOR: SUNNY

public class Tile {
  
  public static final int SIZE = 32;
  
  public static Tile[] tiles = new Tile[30];
  
  protected boolean solid = false;
  protected String name;
  
  protected Bitmap[] frames;
  protected int currentFrame = 0;
  protected int currentInterval = 500;
  protected long longTimer = System.currentTimeMillis();
  
  public byte id;
  
  
  /*intiate tile class
     *@param id find the id of the tile
     *@param name get the name of the tile
     *@param isSolid condition of whether the player can move through the solid or not
     *@param frames the frames of the tile (could be an moving tile like entity etc)
     */
  public Tile(int id, String name, boolean isSolid, String frames) {
    
    
    if(tiles[id] != null) {
      throw new RuntimeException("Duplicated Tile ID! " + id);
    } else{
      //store the frames of the tiles we want into a bitmap array
      tiles[id] = this;
      this.id = (byte) id;
      this.name = name;
      String[] framelets = frames.split(">");
      this.frames = new Bitmap[framelets.length];
      for(int i = 0; i < framelets.length; i++) {
        String[] ind = framelets[i].split(",");
        int fx = Integer.parseInt(ind[0]);
        int fy = Integer.parseInt(ind[1]);
        this.frames[i] = Art.sprites[fx][fy];
      }
      solid = isSolid;
      
    }
  }
  
   /*render the tile onto screen
     *@param screen screen object to print on
     *@param x x location of placement
     *@param y y location of placement
     */
  public void render(Screen screen, int x, int y) {
    screen.render(frames[currentFrame], x, y);
    //count the time and make the bitmap array change images to make it look like moving
    if(System.currentTimeMillis() - longTimer > currentInterval) {
      if(currentFrame >= frames.length - 1) {
        currentFrame = 0;
      } else {
        currentFrame++;
      }
      longTimer += currentInterval;
    }
  }
  
  
  /*checks if it is solid
     *@return screen screen object to print on
     */
  public boolean isSolid() {
    return solid;
  }
}
