/**
 * Animal.java
 */

abstract class Animal extends Organism {

  private int targetX;
  public int getTargetX() {
    return this.targetX;
  }
  private int targetY;
  public int getTargetY() {
    return this.targetY;
  }
  private Organism target;
  public Organism getTarget() {
    return this.target;
  }

  Animal(int x,int y,int health) {
    super(x,y,health);
  }

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

    int choice = (int)(Math.random()*5);
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
  }

  public void eat() {
    this.addHealth(target.getHealth());
    target.die();
    move();
  }

  abstract public void socialize();

  public void check() {
    super.check();
    this.addHealth(-1);
    chooseTarget();
  }

  public void move() {
    Ecosystem.map[targetX][targetY] = Ecosystem.map[this.getX()][this.getY()];
    Ecosystem.map[this.getX()][this.getY()] = null;
    this.setX(targetX);
    this.setY(targetY);
  }

}
