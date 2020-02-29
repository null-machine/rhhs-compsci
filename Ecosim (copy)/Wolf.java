/**
 * Wolf.java
 * 
 * @version 1.0
 * @author Sarita Sou
 * @since Dec 9, 2018
 * 
 * A carnivore.
 */

class Wolf extends Animal implements Comparable<Wolf> {
  
  Wolf(int x,int y,int health) {
    super(x,y,health);
    ///this.setPrey(Sheep);
  }
  
  /**
   * @param The other wolf.
   * @return This wolf's health minus the other wolf's health. 
   */
  public int compareTo(Wolf wolf) {
    return this.getHealth()-wolf.getHealth();
  }
  
  /**
   * Fights another wolf. The weaker one takes ten damage. In a tie, both take five damage.
   * @override
   */
  public void socialize() {
	  
    this.getTarget().setUpdated(true);
    int fightResult=this.compareTo((Wolf)this.getTarget());
    if(fightResult > 0) {
      this.getTarget().addHealth(-10);
    } else if(fightResult < 0) {
      this.addHealth(-10);
    } else {
      this.getTarget().addHealth(-5);
      this.addHealth(-5);
      //System.out.println("wolf brawl");
    }
  }
  
  /**
   * Depending on target, either stays still, moves over null or Plant, eats a Sheep or breeds with another Wolf.
   */
  public void update() {
    super.update();
    if (this.getTarget() == null || this.getTarget() instanceof Plant) {
      move();
    } else if (this.getTarget() instanceof Sheep) {
      eat();
    } else if (this.getTarget() instanceof Wolf) {
      socialize();
    }
    System.out.println(this.getHealth());
  }
  
  /// replaced eat and interact with update
  
}
