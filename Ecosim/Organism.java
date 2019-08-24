/**
 * Organism.java
 */

abstract class Organism {

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

  private boolean checked;
  public boolean getChecked() {
    return this.checked;
  }
  public void setChecked(boolean checked) {
    this.checked=checked;
  }

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

  Organism(int x,int y,int health) {
    this.x=x;
    this.y=y;
    this.health=health;
  }

  public void die() {
    Ecosystem.map[x][y]=null;
    Ecosystem.checkExtinct(this.getClass());
  }

  public void check() {
    setChecked(true);
  }

}
