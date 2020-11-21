import java.awt.Point;
import java.awt.Graphics;
import java.awt.Polygon;
import java.io.Serializable;

abstract class Shape implements Comparable<Shape>, Serializable {
	private Point centroid;
	private Point[] vertices;

	Shape(Point centroid) {
		this.centroid = centroid;
	}

	abstract public double getArea();

	abstract public double getPerimeter();

	public String display() {
		String info = "";
		info += getClass().getName();
		info += "\nCentroid: " + centroid.x + ", " + centroid.y;
		info += "\nArea: " + getArea();
		info += "\nPerimeter: " + getPerimeter();
		for (int i = 0; i < vertices.length; i++) {
			info += "\nVertex " + (i + 1) + ": " + (vertices[i].x + centroid.x) + ", "
					+ (vertices[i].y + centroid.y);
		}

		return info;
	};

	public void translate(int translateX, int translateY) {
		centroid = new Point(centroid.x + translateX, centroid.y + translateY);
	}

	public void scale(double scale) {
		for (int i = 0; (vertices != null) && (i < vertices.length); i++) {
			vertices[i] = new Point(((int) (scale * vertices[i].x)), ((int) (scale * vertices[i].y)));
		}
	}

	public void draw(Graphics g) {
		int count = vertices.length;
		int[] xPoints = new int[count];
		int[] yPoints = new int[count];
		for (int i = 0; i < count; i++) {
			xPoints[i] = (int) (vertices[i].x + centroid.x);
			yPoints[i] = (int) (vertices[i].y + centroid.y);
		}
		g.drawPolygon(new Polygon(xPoints, yPoints, count));
	}

	public int compareTo(Shape other) {
		return (int) (getArea() - other.getArea());
	}

	public double getDistance(int x1, int y1, int x2, int y2) {
		return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
	}

	public Point getCentroid() {
		return centroid;
	}

	public void setCentroid(Point centroid) {
		this.centroid = centroid;
	}

	public Point[] getVertices() {
		return vertices;
	}

	public void setVertices(Point[] vertices) {
		this.vertices = vertices;
	}

	public void setVertex(Point vertex, int i) {
		vertices[i] = vertex;
	}
}
