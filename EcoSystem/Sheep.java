/**
 * Sheep.java
 * Version 1.0
 * @Author: Jordan A.
 * @4/17/2019
 * A class for sheep
 */

class Sheep extends Entity{
  Sheep(int hp, int y, int x){
    setHP(hp);
    setY(y);
    setX(x);
    returnHP();
  }
  
  void consumeGrass(int nutValue){
    this.adjustHP((nutValue / 2)+(nutValue%2));
  }
  
  static void sheepMoveUp(Entity[][] map, int y, int x){
    if (map[y-1][x] instanceof Grass){
      int nutValue = map[y-1][x].returnHP();
      int sheepHP = map[y][x].returnHP();
      
      map[y-1][x] = new Sheep(sheepHP-1+nutValue, y-1, x);
      map[y][x] = new Dirt();
    } else {
      int sheepHP = map[y][x].returnHP();
      
      map[y-1][x] = new Sheep(sheepHP-1, y-1, x);
      map[y][x] = new Dirt();
    }
  }
  
  static void sheepMoveDown(Entity[][] map, int y, int x){
    if (map[y+1][x] instanceof Grass){
      int nutValue = map[y+1][x].returnHP();
      int sheepHP = map[y][x].returnHP();
      
      map[y+1][x] = new Sheep(sheepHP-1+nutValue, y+1, x);
      map[y][x] = new Dirt();
    } else {
      int sheepHP = map[y][x].returnHP();
      
      map[y+1][x] = new Sheep(sheepHP-1, y+1, x);
      map[y][x] = new Dirt();
    }
  }
  
  static void sheepMoveLeft(Entity[][] map, int y, int x){
    if (map[y][x-1] instanceof Grass){
      int nutValue = map[y][x-1].returnHP();
      int sheepHP = map[y][x].returnHP();
      map[y][x-1] = new Sheep(sheepHP-1+nutValue, y, x-1);
      map[y][x] = new Dirt();
    } else {
      int sheepHP = map[y][x].returnHP();
      map[y][x-1] = new Sheep(sheepHP-1, y, x-1);
      map[y][x] = new Dirt();
    }
  }
  
  static void sheepMoveRight(Entity[][] map, int y, int x){
    if (map[y][x+1] instanceof Grass){
      int nutValue = map[y][x+1].returnHP();
      int sheepHP = map[y][x].returnHP();
      map[y][x+1] = new Sheep(sheepHP-1+nutValue, y, x+1);
      map[y][x] = new Dirt();
    } else {
      int sheepHP = map[y][x].returnHP();
      map[y][x+1] = new Sheep(sheepHP-1, y, x+1);
      map[y][x] = new Dirt();
    }
  }
  
}