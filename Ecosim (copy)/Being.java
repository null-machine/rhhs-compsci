/**
 * Being.java
 * 
 * @version 1.0
 * @author Sarita Sou
 * @since Dec 9, 2018
 * 
 * A basic lifeform. Contains information shared by plants and animals. 
 */

abstract class Being {
  
  private int health;
  public int getHealth() {
    return this.health;
  }
  public void addHealth(int health) {
    this.health+=health;
    if(this.health<=0) {
      this.die();
    }
  }
  
  private int x;
  public int getX() {
    return this.x;
  }
  public void setX(int x) {
    this.x = x;
  }
  private int y;
  public int getY() {
    return this.y;
  }
  public void setY(int y) {
    this.y = y;
  }
  
  private boolean updated;
  public boolean getUpdated() {
    return this.updated;
  }
  public void setUpdated(boolean updated) {
    this.updated=updated;
  }
  
  /**
  * Removes this from the map and prompts an extinction check.
  */
  public void die() {
    Ecosystem.map[x][y]=null;
    Ecosystem.checkExtinct(this.getClass());
  }
  
  ///abstract public void update();
  
 /**
  * Chooses between the possible actions a Being can take each turn. Overriden.
  */
  public void update() {
    setUpdated(true);
  }
  
  Being(int x,int y,int health) {
    this.x=x;
    this.y=y;
    this.health=health;
    //this.setUpdated(true);
  }
}
