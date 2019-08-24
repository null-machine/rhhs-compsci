/**
 * Entity.java
 * Version 1.0
 * @Author: Jordan A.
 * @4/17/2019
 * A class that determines what traits all entities should have
 */

abstract class Entity{
  private int health;
  private int turns;
  private int y;
  private int x;

  void setHP(int hp){
    this.health = hp; //sets the starting hp of the entity
  }

  void adjustHP(int hp){
    this.health += hp; //mods hp values for adding hp values or taking damage
  }

  int returnHP() {
    return this.health; //returns the hp value for comparing
  }

  void setTurns(int turns){
    this.turns = turns;
  }

  int returnTurns(){
    return this.turns;
  }

  int returnY(){
    return this.y;
  }

  int returnX(){
    return this.x;
  }

  void setX(int x){
    this.x = x;
  }

  void setY(int y){
    this.y = y;
  }

  void killEntity(int hp){
    if (hp <= 0){
      //kill
    }
  }



}
