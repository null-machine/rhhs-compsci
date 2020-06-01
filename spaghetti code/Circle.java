import java.awt.Point;

class Circle extends Oval {
	Circle(Point centroid, int radius) {
		super(centroid, radius*2, radius*2);
	}

	@Override
	public String display() {
		String info = "";
		info += getClass().getName();
		info += "\nCentroid: " + getCentroid().x + ", " + getCentroid().y;
		info += "\nArea: " + getArea();
		info += "\nPerimeter: " + getPerimeter();
		info += "\nRadius: " + getWidth()/2;

		return info;
	}

	@Override
	public double getPerimeter() {
		return Math.PI*getWidth();
	}
}
