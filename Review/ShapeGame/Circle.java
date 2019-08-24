/**
* 
* 
*/

public class Circle extends Shape {
	
	public double radius;
	public double getRadius() { return radius; }
	public void setRadius(double radius) { this.radius = radius; }
	
	Circle(double x, double y, double radius) {
		super(x, y);
		this.radius = radius;
	}
	
	public double getArea() {
		return Math.PI * radius * radius;
	}
}
