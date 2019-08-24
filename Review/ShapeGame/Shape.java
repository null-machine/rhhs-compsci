/**
* 
* 
*/

import java.awt.Rectangle;

public abstract class Shape {
	
	public double x;
	public double getX() { return x; }
	public void setX(double x) { this.x = x; }
	
	public double y;
	public double getY() { return y; }
	public void setY(double y) { this.y = y; }
	
	public Rectangle boundingBox;
	public Rectangle getBoundingBox() { return boundingBox; }
	
	Shape(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public double getArea() {
		return boundingBox.width * boundingBox.height;
	}
}
