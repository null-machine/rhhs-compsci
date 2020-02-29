/**
 * Ecosystem.java
 * 
 * @version 1.0
 * @author Sarita Sou
 * @since Dec 9, 2018
 * 
 * An ecosystem storing initial conditions.
 */

import java.util.ArrayList;

class Ecosystem {
  /// reordered and grouped variables, getters, setters to match classes
  public static Being[][] map=new Being[25][25]; /// gave everything a galaxy brain for a Lot more readability
  /// deleted removeBeing() because they delete themselves now
  /// added this to make initialization clearer
  public static void spawnBeing(int x,int y,Being being) {
    ///if(map[x][y]!=null) System.out.println("warning: " + being + " replaces " + map[x][y]);
    map[x][y]=being;
  }
  
  
  private int plantHealth; /// tempted to rename these to initPlantHealth
  private int sheepHealth;
  private int wolfHealth;
  private int plantSpawnRate;
  
  private int turn; /// declared inside constructor
  
  /// deleted x, y
  
  private static String extinct; /// replaced bool running with this bc checking for null is a thing
  public String getExtinct() {
    return this.extinct;
  }
  
  /// formalized method comments
  
  /**
   * Constructs and populates the ecosystem.
   * 
   * @param plant spawn rate, initial healths, initial populations
   */
  Ecosystem(int plantAmount,int plantHealth,int plantSpawnRate,int sheepAmount,int sheepHealth,int wolfAmount,int wolfHealth) {
    this.plantSpawnRate = plantSpawnRate;
    int[] spawnPos;
    for (int i=0;i<plantAmount;i++) {
	  spawnPos=findEmpty();
      if(spawnPos != null) {
        spawnBeing(spawnPos[0],spawnPos[1],new Plant(spawnPos[0],spawnPos[1],plantHealth));
      }
    }
    for (int i=0;i<sheepAmount;i++) {
      spawnPos=findEmpty();
      if(spawnPos != null) {
        spawnBeing(spawnPos[0],spawnPos[1],new Sheep(spawnPos[0],spawnPos[1],sheepHealth));
      }
    }
    for (int i=0;i<wolfAmount;i++) {
      spawnPos=findEmpty();
      if(spawnPos != null) {
        spawnBeing(spawnPos[0],spawnPos[1],new Wolf(spawnPos[0],spawnPos[1],wolfHealth));
      }
    }
    
    turn = 1;
  }
  
  /**
   * @param Two int arrays of size two containing x and y coordinates bounding a rectangular area. If unspecified, uses the entire map.
   * @return If there's an empty space in the area, returns its x and y coordinates in an integer array of size two; else returns null.
   */
  public int[] findEmpty(int[] start, int[] end) {
    ArrayList<int[]> empties = new ArrayList<int[]>();
    for (int i=start[0];i<end[0];i++) {
      for (int j=start[1];j<end[1];j++) {
        if (map[i][j]==null) {
          empties.add( new int[] {i,j} );
        }
      }
    }
    if (empties.size()!=0) {
      return empties.get((int)(Math.random()*empties.size()));
    } else {
	  return null;
	}
  }
  public static int[] findEmpty() {
    ArrayList<int[]> empties = new ArrayList<int[]>();
    for (int i=0;i<map.length;i++) {
      for (int j=0;j<map[0].length;j++) {
        if (map[i][j]==null) {
          empties.add( new int[] {i,j} );
        }
      }
    }
    if (empties.size()!=0) {
      return empties.get((int)(Math.random()*empties.size()));
    } else {
	  return null;
	}
  }
  
  /**
   * Sets updated to false for each Being in the ecosystem.
   */
  public void readyBeings() { 
    for(int i=0;i<map.length;i++) {
      for(int j=0;j<map[0].length;j++) {
		if(map[i][j] instanceof Being) {
		  map[i][j].setUpdated(false);
		}
      }
    }
  }
  
  /**
   * Calls update for each Being in the ecosystem.
   */
  public void updateBeings() {
    for(int i=0;i<map.length;i++) {
      for(int j=0;j<map[0].length;j++) {
		if((map[i][j] instanceof Being)&&(!map[i][j].getUpdated())) {
		  map[i][j].update();
		}
      }
    }
  }
  
  /**
   * Spawns plants in the ecosystem according to the plant spawn rate.
   */
  public void spawnPlants() {
    int[] spawnPos;
    for (int i=0;i<plantSpawnRate;i++) {
      spawnPos=findEmpty();
      if(spawnPos != null) {
        spawnBeing(spawnPos[0],spawnPos[1],new Plant(spawnPos[0],spawnPos[1],sheepHealth));
      }
    }
  }
  
  /**
   * Updates the extinct string.
   * @param The class of Being to check for extinction.
   */
  public static void checkExtinct(Class being) { /// only checks on death
    extinct = being.toString();
    for(int i=0;i<map.length;i++) {
      for(int j=0;j<map[0].length;j++) {
        if(map[i][j] != null && map[i][j].getClass() == being) {
	      extinct = null;
	    }
      }
	}
  }
  
  /// replaced newTurn and moveAnimals into spawnPlants and updateActors (not respectively)
}
