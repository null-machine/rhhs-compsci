/**
 * Ecosystem.java
 */

import java.util.ArrayList;

class Ecosystem {
  public static Organism[][] map=new Organism[25][25];
  public static void spawn(int x,int y,Organism being) {
    map[x][y]=being;
  }


  private int plantHealth;
  private int sheepHealth;
  private int wolfHealth;
  private int plantSpawnRate;
  private int turn;
  private static String extinct;

  public String getExtinct() {
    return this.extinct;
  }

  Ecosystem(int plantAmount,int plantHealth,int plantSpawnRate,int sheepAmount,int sheepHealth,int wolfAmount,int wolfHealth) {
    this.plantSpawnRate = plantSpawnRate;
    int[] spawnPos;
    for (int i=0;i<plantAmount;i++) {
	  spawnPos=findEmpty();
      if(spawnPos != null) {
        spawn(spawnPos[0],spawnPos[1],new Plant(spawnPos[0],spawnPos[1],plantHealth));
      }
    }
    for (int i=0;i<sheepAmount;i++) {
      spawnPos=findEmpty();
      if(spawnPos != null) {
        spawn(spawnPos[0],spawnPos[1],new Sheep(spawnPos[0],spawnPos[1],sheepHealth));
      }
    }
    for (int i=0;i<wolfAmount;i++) {
      spawnPos=findEmpty();
      if(spawnPos != null) {
        spawn(spawnPos[0],spawnPos[1],new Wolf(spawnPos[0],spawnPos[1],wolfHealth));
      }
    }

    turn = 1;
  }


  public static void checkExtinct(Class being) {
    extinct = being.toString();
    for(int i=0;i<map.length;i++) {
      for(int j=0;j<map[0].length;j++) {
        if(map[i][j] != null && map[i][j].getClass() == being) {
	         extinct = null;
	      }
      }
	  }
  }

  public void readyOrganisms() {
    for(int i=0;i<map.length;i++) {
      for(int j=0;j<map[0].length;j++) {
		if(map[i][j] instanceof Organism) {
		  map[i][j].setChecked(false);
		}
      }
    }
  }

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

  public void checkOrganisms() {
    for(int i=0;i<map.length;i++) {
      for(int j=0;j<map[0].length;j++) {
		if((map[i][j] instanceof Organism)&&(!map[i][j].getChecked())) {
		  map[i][j].check();
		}
      }
    }
  }

  public void spawnPlants() {
    int[] spawnPos;
    for (int i=0;i<plantSpawnRate;i++) {
      spawnPos=findEmpty();
      if(spawnPos != null) {
        spawn(spawnPos[0],spawnPos[1],new Plant(spawnPos[0],spawnPos[1],sheepHealth));
      }
    }
  }
}
