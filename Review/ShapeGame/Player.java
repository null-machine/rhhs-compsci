/**
* 
* 
*/

public class Player extends Circle implements Moveable, Scalable {
	
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
	
	public void grow() {
		this.setRadius(this.radius + 12);
	}
	
	public void shrink() {
		this.setRadius(this.radius - 12);
	}
	
	Player(double x, double y, double radius) {
		super(x, y, radius);
	}
}
