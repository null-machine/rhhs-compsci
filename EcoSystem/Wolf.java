/**
 * Wolf.java
 * Version 1.0
 * Author: Jordan A.
 * @4/18/2019
 * A class for wolves
 */

class Wolf extends Entity{
  Wolf(int hp, int y, int x){ // creates a wolf with a set amount of hp
    setHP(hp);
    setY(y);
    setX(x);
    returnHP();
  }
  
  boolean combatCheck(Entity[][] map, Wolf wolf1, Wolf wolf2){ // returns true if a fight is initiated
    int wolf1y = wolf1.returnY();
    int wolf1x = wolf1.returnX();
    int wolf2y = wolf2.returnY();
    int wolf2x = wolf2.returnX();
    
    if (wolf1y == wolf2y && wolf1x == wolf2x){
      return true;
    } else {
      return false;
    }
  }
  
  static void wolfMoveUp(Entity[][] map, int y, int x){
    if (map[y-1][x] instanceof Sheep){
      int nutValue = (map[y-1][x].returnHP()) / 2;
      int wolfHP = map[y][x].returnHP();
      
      map[y-1][x] = new Wolf(wolfHP-1+nutValue, y-1, x);
      map[y][x] = new Dirt();
    } else {
      int wolfHP = map[y][x].returnHP();
      
      map[y-1][x] = new Wolf(wolfHP-1, y-1, x);
      map[y][x] = new Dirt();
    }
  }
  
  static void wolfMoveDown(Entity[][] map, int y, int x){
    if (map[y+1][x] instanceof Sheep){
      int nutValue = (map[y+1][x].returnHP()) / 2;
      int wolfHP = map[y][x].returnHP();
      
      map[y+1][x] = new Wolf(wolfHP-1+nutValue, y+1, x);
      map[y][x] = new Dirt();
    } else {
      int wolfHP = map[y][x].returnHP();
      
      map[y+1][x] = new Wolf(wolfHP-1, y+1, x);
      map[y][x] = new Dirt();
    }
  }
  
  static void wolfMoveLeft(Entity[][] map, int y, int x){
    if (map[y][x-1] instanceof Sheep){
      int nutValue = (map[y][x-1].returnHP()) / 2;
      int wolfHP = map[y][x].returnHP();
      
      map[y][x-1] = new Wolf(wolfHP-1+nutValue, y, x-1);
      map[y][x] = new Dirt();
    } else {
      int wolfHP = map[y][x].returnHP();
      
      map[y][x-1] = new Wolf(wolfHP-1, y, x-1);
      map[y][x] = new Dirt();
    }
  }
  
  static void wolfMoveRight(Entity[][] map, int y, int x){
    if (map[y][x+1] instanceof Sheep){
      int nutValue = (map[y][x+1].returnHP()) / 2;
      int wolfHP = map[y][x].returnHP();
      
      map[y][x+1] = new Wolf(wolfHP-1+nutValue, y, x+1);
      map[y][x] = new Dirt();
    } else {
      int wolfHP = map[y][x].returnHP();
      
      map[y][x+1] = new Wolf(wolfHP-1, y, x+1);
      map[y][x] = new Dirt();
    }
  }
}