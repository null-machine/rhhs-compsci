//AUTHOR: SUNNY

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class Level {
 //associate an integer with a level
 public static Map<Integer, Level> levels = new HashMap<Integer, Level>();
        //which floor this level is on
 public int floor;
 //the number of tiles on that level
 public int[][] tiles;
 
 private List<Entity> entities = new ArrayList<Entity>();//list of entities on this floor
 public Player player;//player on that level
        
        //how offset the tiles are because the level will not be rendered at (0,0)
 public static final int Y_OFFSET = 1 * Tile.SIZE, X_OFFSET = 1 * Tile.SIZE;
 
 public Level() {
  tiles = new int[12][13];
 }
 
 public void render(Screen screen) {
  for(int i = 0; i < tiles.length; i++) 
   for(int j = 0; j < tiles[i].length; j++) {
    if(Tile.tiles[tiles[i][j]] == null) {
                                    continue; //if tile doesn't exist, just continue
                                }
                                
                                //if tile id does exist in our this.tiles[][] array, render on screen
    Tile.tiles[tiles[i][j]].render(screen, X_OFFSET + i * Tile.SIZE, Y_OFFSET + j * Tile.SIZE);
   }
  
  for(Entity e : entities) {
   e.render(screen);
   
  }
 }
 
 public void tick() {
  for(int i = 0; i < entities.size(); i++) {
   entities.get(i).tick();
   if(entities.get(i).removed) {
    entities.remove(i);
   }
  }
 }

 public void addEntity(Entity entity) {
  entities.add(entity);
  if(entity instanceof Player) {//check if the enetity we are adding is a player. if so, set the object variable for player equal to that new entity
   this.player = (Player) entity;
   entities.add(entity);
  }

 }

 public Tile getTileAt(int i, int j) {
            
                //if the tile has an x value greater than 12 or greater that 13, the tile is not in the screen
  if(i < 0 || i >= tiles.length || j < 0 || j > tiles.length) {
                    return null;
                }
  return Tile.tiles[tiles[i][j]];//if it does exist, return the tile object
 }

 public Entity getEntityAt(int dir, int x, int y) {
  for(Entity entity : entities) {
                    //if the entity we are looking at is in the same x and y position we are searching for
   if(entity.x == x && entity.y == y && ((entity instanceof NPC) 
                                )) {
                            return entity;
                        }
  }
  for(Entity entity : entities) {
   if(entity.x == x && entity.y == y && !(entity instanceof Player)) {
                            return entity;
                        }
  }
  return null;
 }

 public List<Entity> getEntities() {
  return entities;
 }

}
