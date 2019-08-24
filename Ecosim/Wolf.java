/**
 * Wolf.java
 */

class Wolf extends Animal implements Comparable<Wolf> {

  Wolf(int x,int y,int health) {
    super(x,y,health);
  }

  public int compareTo(Wolf wolf) {
    return this.getHealth()-wolf.getHealth();
  }

  public void socialize() {
    this.getTarget().setChecked(true);
    int fightResult=this.compareTo((Wolf)this.getTarget());
    if(fightResult > 0) {
      this.getTarget().addHealth(-10);
    } else if(fightResult < 0) {
      this.addHealth(-10);
    } else {
      this.getTarget().addHealth(-5);
      this.addHealth(-5);
    }
  }

  public void check() {
    super.check();
    if (this.getTarget() == null || this.getTarget() instanceof Plant) {
      move();
    } else if (this.getTarget() instanceof Sheep) {
      eat();
    } else if (this.getTarget() instanceof Wolf) {
      socialize();
    }
    System.out.println(this.getHealth());
  }
}
