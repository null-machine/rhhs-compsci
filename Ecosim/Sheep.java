/**
 * Sheep.java
 */

class Sheep extends Animal {

  Sheep(int x,int y,int health) {
    super(x,y,health);
  }

  public void socialize() {
    if(this.getHealth() > 20 && this.getTarget().getHealth() > 20) {
      this.getTarget().setChecked(true);
      this.addHealth(-10);
      this.getTarget().addHealth(-10);
      int[] spawnPos=Ecosystem.findEmpty(/*add coords*/);
      Ecosystem.spawn(spawnPos[0],spawnPos[1],new Sheep(spawnPos[0],spawnPos[1],20)); ///TODO
    }
  }

  public void check() {
    super.check();
    if(this.getTarget() != this) {
      if (this.getTarget() == null) {
        move();
      } else if (this.getTarget() instanceof Plant) {
        eat();
      } else if (this.getTarget() instanceof Sheep) {
        socialize();
      }
    }
  }
}
