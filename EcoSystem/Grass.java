/**
 * Grass.java
 * Version 1.0
 * @Author: Jordan A.
 * @4/18/2019
 * This program simulates grass.
 */

class Grass extends Entity{

  Grass(Entity[][] map, int turns, int y, int x){
    
    setY(y);
    setX(x);
    setTurns(turns);
    
    if (turns > 0 || turns < 6){
      setHP(turns);
    } else if (turns >= 6 || turns <= 12){
      setHP(6);
    } else if (turns > 12 || turns < 18){
      setHP(18 - turns);
    } else if (turns >= 18){
      setHP(0);
    }
  }
}