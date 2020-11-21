import java.awt.Point;

class Rectangle extends Parallelogram {
	Rectangle(Point centroid, int width, int height) {
		super(centroid, width/2, height/2, width/2, -height/2);
	}

	@Override
	public double getArea() {
		return getWidth()*getHeight();
	}

	@Override
	public String display() {
		String info = "";
		info += getClass().getName();
		info += "\nCentroid: " + getCentroid().x + ", " + getCentroid().y;
		info += "\nArea: " + getArea();
		info += "\nPerimeter: " + getPerimeter();
		info += "\nWidth: " + getWidth();
		info += "\nHeight: " + getHeight();
		for (int i=0; i<getVertices().length; i++) {
			info += "\nVertex " + (i+1) + ": " + (getVertices()[i].x + getCentroid().x) + ", " + (getVertices()[i].y + getCentroid().y);
		}

		return info;
	}
}
