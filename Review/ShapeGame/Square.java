/**
* 
* 
*/

public class Square extends Shape {
	
	public double width;
	public double height;
	
	Square(double x, double y, double height, double width) {
		super(x, y);
		this.width = width;
		this.height = height;
	}
	
	public double getArea() {
		return width * height;
	}
	
	public void setArea(double area) {
		double side = Math.sqrt(area);
		this.setHeight(side);
		this.setWidth(side);
	}
	
	public double getWidth() {
		return width;
	}
	
	public double getHeight() {
		return height;
	}
	
	public void setHeight(double height) {
		this.height = height;
	}
	
	public void setWidth(double width) {
		this.width = width;
	}
}
