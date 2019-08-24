/**
* 
* 
*/

public class Enemy extends Square implements Moveable {
	
	public void moveRight() {
		this.setX(this.x + 12);
	}
	public void moveLeft() {
		this.setX(this.x - 12);
	}
	public void moveUp() {
		this.setY(this.y + 12);
	}
	public void moveDown() {
		this.setY(this.y - 12);
	}
	
	Enemy(double x, double y, double height, double width) {
		super(x, y, height, width);
	}
}
