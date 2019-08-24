/**
 * Ecosystem.java
 * Version 1.0
 * Author: @Jordan A.
 * @ 04/17 - 05/06
 * This program generates the map, and contains the code for interactions.
 */

import java.awt.Image;
import javax.imageio.*;
import javax.swing.*;
import java.awt.*;

class Ecosystem{
  
  
  
  Ecosystem(Entity[][] map, int mapSize, int sheepCount, int sheepHP, int wolfCount, int wolfHP, int grassCount, int grassRate){

    //put base stuff here
    while (sheepCount >= 1 && wolfCount >= 1 && grassCount >= 1){
      
      //grid.refresh();
      try{ Thread.sleep(1); }catch(Exception e) {};
      
      for (int i = 0; i < mapSize; i++){
        for (int j = 0; j < mapSize; j++){
          // pulls out a chance to spawn grass on any dirt tile
          if (map[i][j] instanceof Dirt){
            growGrass(map, i, j, grassRate);
          } 
          // generates a new grass tile with one more turn
          // as well as adjusting hp based on turn calculations
          else if (map[i][j] instanceof Grass){
            int grassTurn = map[i][j].returnTurns();
            map[i][j] = new Grass(map, grassTurn +1, i, j);
          } 
          // rolls to move the sheep in any direction
          else if (map[i][j] instanceof Sheep){
            sheepMovement(map, mapSize, i, j);
          } 
          // rolls to move the wolf, as well as checks for combat
          else if (map[i][j] instanceof Wolf){
            wolfMovement(map, mapSize, i, j);
          } 
        }
      }
      
      // delete any entity with an HP of 0
      for (int i = 0; i < mapSize; i++){
        for (int j = 0; j < mapSize; j++){
          int tileHP = map[i][j].returnHP();
          if (tileHP <= 0){
            map[i][j] = new Dirt();
          }
        }
      }
      
      //grid.refresh();
      
      // display stuff here
      
    }
  }
  
  /**
   * sheepMovement() randomly determines which direction the sheep will move in
   * @param map used as the main Entity array
   * @param size used as the maximum size of the array
   */
  
  public static void sheepMovement(Entity[][] map, int size, int y, int x){
    int random = (int)(Math.random() * 5);
    int sheepHP = map[y][x].returnHP();
    if (random == 0){
      map[y][x] = new Sheep(sheepHP-1, y, x);
    } else if (random == 1 && y > 0){
      Sheep.sheepMoveUp(map, y, x);
    } else if (random == 2 && y < size){
      Sheep.sheepMoveDown(map, y, x);
    } else if (random == 3 && x > 0){
      Sheep.sheepMoveLeft(map, y, x);
    } else if (random == 4 && x < size){
      Sheep.sheepMoveRight(map, y, x);
    } else {
      sheepMovement(map, size, y, x);
    }
  }
  
  /**
   * wolfMovement randomly determins which direction the wolf will move in
   * additionally it also checks to see if it steps on another wolf
   * @param map used as the main Entity array
   * @param size used as the maximum size of the array
   */
  
  public static void wolfMovement(Entity[][] map, int size, int y, int x){
    int random = (int)(Math.random() * 5);
    int wolfHP = map[y][x].returnHP();
    if (random == 0){
      map[y][x] = new Wolf(wolfHP - 1, y, x);
    } else if (random == 1){
      Wolf.wolfMoveUp(map, y, x);
    } else if (random == 2){
      Wolf.wolfMoveDown(map, y, x);
    } else if (random == 3){
      Wolf.wolfMoveLeft(map, y, x);
    } else if (random == 4){
      Wolf.wolfMoveRight(map, y, x);
    } else {
      wolfMovement(map, size, y, x);
    }
  }
  
  /**
   * growGrass() gets a random integer on a range of 1 to grassRate;
   * if the integer is 1, then grass will spawn
   * @param map used as the main Entity array
   * @param i piggybacks off the i variable from grassRespawn()
   * @param j piggybacks off the j variable from grassRespawn()
   */
  
  public static void growGrass(Entity[][] map, int i, int j, int grassRate){
    int random = (int)(Math.random() * grassRate) + 1;
    if (random == 1){
      map[i][j] = new Grass(map, 0, i, j);
    }
  } // end of growGrass
  
  /**
   * wolfCombat() compares the HP values of two wolves; the weaker wolf takes 10 damage
   * @param wolf1 indicates who the aggressor is
   * @param wolf2 indicates who the targeted wolf is
   */
  
  public static void wolfCombat(Wolf wolf1, Wolf wolf2){
    if (wolf1.returnHP() > wolf2.returnHP()){
      wolf2.adjustHP(-10); // if aggressor is stronger than target
    } else if (wolf1.returnHP() < wolf2.returnHP()){
      wolf1.adjustHP(-10); // if target is stronger than aggressor
    } else if (wolf1.returnHP() == wolf2.returnHP()){
      wolf1.adjustHP(-10); // if both are equal
      wolf2.adjustHP(-10); 
    }
  } // end of wolfCombat
  
  
  
  
}