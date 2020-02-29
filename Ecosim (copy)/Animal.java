/**
 * Animal.java
 * 
 * @version 1.0
 * @author Sarita Sou
 * @since Dec 9, 2018
 * 
 * A mobile lifeform that seeks prey for nourishment.
 */

abstract class Animal extends Being {
  
  /*
  private Class prey;
  public Class getPrey() {
    return this.prey;
  }
  public void setPrey(Class prey) {
    this.prey=prey;
  }
  */
  
  private int targetX;
  public int getTargetX() {
    return this.targetX;
  }
  private int targetY;
  public int getTargetY() {
    return this.targetY;
  }
  private Being target;
  public Being getTarget() {
    return this.target;
  }
  
  Animal(int x,int y,int health) {
    super(x,y,health);
  }
  
  /**
  * Sets target to a random, adjacent tile.
  */
  public void chooseTarget() {
	System.out.println(this + " current pos: " + this.getX() + " " + this.getY());
    
    boolean[] validTargets = new boolean[4]; /// 0=north, 1=east, 2=south, 3=west
    if (this.getY()>0) {
      validTargets[0] = true;
    } 
    if (this.getX()<Ecosystem.map.length-1) {
      validTargets[1] = true;
    } 
    if (this.getY()<Ecosystem.map[0].length-1) {
      validTargets[2] = true;
    } 
    if (this.getX()>0) {
      validTargets[3] = true;
    }
    
    ///for(boolean b : validTargets) System.out.println(this + " " + b);
    
    int choice = (int)(Math.random()*5);
    ///System.out.println(this + " choice " + choice);
    if ((choice==1) && (validTargets[0])) {
      targetX=this.getX();
      targetY=this.getY()-1;
    } else if ((choice==2) && (validTargets[1])) {
      targetX=this.getX()+1;
      targetY=this.getY();
    } else if ((choice==3) && (validTargets[2])) {
      targetX=this.getX();
      targetY=this.getY()+1;
    } else if ((choice==4) && (validTargets[3])) {
      targetX=this.getX()-1;
      targetY=this.getY();
    }
    System.out.println(this + " target pos: " + this.getX() + " " + this.getY());
    target=Ecosystem.map[targetX][targetY];
    /*
    int numValidTargets = 0;
    for (int i=0;i<validTargets.length;i++) {
	  System.out.println(this + " " + validTargets[i]);
      if (validTargets[i]) {
        numValidTargets++;
      }
    }
    
    int choice = (int)(Math.random()*numValidTargets);
    System.out.println(this + " numValidTargets " + numValidTargets);
    System.out.print(this + " choice " + choice + " ");
    int trueCount = 0;
    for (int i=0;i<validTargets.length;i++) {
      if (validTargets[i]) {
        trueCount++;
      }
      if (trueCount == choice) {
        /// i is equal to the index of a random true value in validTargets
        System.out.println(i);
        if(i == 0) {
          targetX=this.getX();
          targetY=this.getY()+1;
        } else if(i == 1) {
          targetX=this.getX()+1;
          targetY=this.getY();
        } else if(i == 2) {
          targetX=this.getX();
          targetY=this.getY()-1;
        } else if(i == 3) {
          targetX=this.getX()-1;
          targetY=this.getY();
        }
        target = Ecosystem.map[targetX][targetY];
      }
    }
    */
  }
  
  /**
  * Replaces target with this.
  */
  public void move() {
    ///System.out.println(this + " move location " + targetX + " " + targetY);
    Ecosystem.map[targetX][targetY] = Ecosystem.map[this.getX()][this.getY()];
    Ecosystem.map[this.getX()][this.getY()] = null;
    this.setX(targetX);
    this.setY(targetY);
  }
  
  /**
  * Adds target health to this health before replacing target. 
  */
  public void eat() {
    this.addHealth(target.getHealth());
    target.die();
    move();
  }
  
  /// can implement with target.setUpdated(true); and have inheritors call super.socialize() but gotta flex that abstract
  
  /**
  * Determines action when target is another instance of this class.
  */
  abstract public void socialize();
  
  /**
  * Ages and updates target.
  * @override /// dunno if this is how you're supposed to use this
  */
  public void update() {
    super.update();
    this.addHealth(-1);
    chooseTarget();
  }
  
  /// replaced moveCheck and interact with update
}
