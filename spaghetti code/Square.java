import java.awt.Point;

class Square extends Rectangle {
	Square(Point centroid, int width) {
		super(centroid, width, width);
	}

	@Override
	public String display() {
		String info = "";
		info += getClass().getName();
		info += "\nCentroid: " + getCentroid().x + ", " + getCentroid().y;
		info += "\nArea: " + getArea();
		info += "\nPerimeter: " + getPerimeter();
		info += "\nWidth: " + getWidth();
		for (int i=0; i<getVertices().length; i++) {
			info += "\nVertex " + (i+1) + ": " + (getVertices()[i].x + getCentroid().x) + ", " + (getVertices()[i].y + getCentroid().y);
		}
		
		return info;
	}

	@Override
	public void setWidth(double width) {
		super.setWidth(width);
		super.setHeight(width);
	}
}
