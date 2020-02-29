/**
 * Sheep.java
 * 
 * @version 1.0
 * @author Sarita Sou
 * @since Dec 9, 2018
 * 
 * A herbivore.
 */

class Sheep extends Animal {

  Sheep(int x,int y,int health) {
    super(x,y,health);
    ///this.setPrey(Plant);
  }
  
  /**
   * Breeds with another sheep if both health values are over twenty. Counts as a turn for both sheep.
   * @override
   */
  public void socialize() {
    if(this.getHealth() > 20 && this.getTarget().getHealth() > 20) {
      /// move in this if block into a void method in animal for wolf breeding
      this.getTarget().setUpdated(true);
      this.addHealth(-10);
      this.getTarget().addHealth(-10);
      int[] spawnPos=Ecosystem.findEmpty(/*add coords*/);
      Ecosystem.spawnBeing(spawnPos[0],spawnPos[1],new Sheep(spawnPos[0],spawnPos[1],20)); ///TODO
    }
  }
  
  /**
   * Depending on target, either stays still, moves over null, eats a Plant, or breeds with another Sheep.
   */
  public void update() {
    super.update();
    if(this.getTarget() != this) {
      if (this.getTarget() == null) {
        move();
      } else if (this.getTarget() instanceof Plant) {
        eat();
      } else if (this.getTarget() instanceof Sheep) {
        socialize();
      }
    }
    ///System.out.println(this.getHealth());
  }
  
  /// replaced eat and interact with update
}
