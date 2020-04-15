import java.util.Scanner;
import java.awt.Point;
import java.awt.Graphics;
import java.awt.Polygon;
import java.io.Serializable;

abstract class Shape implements Comparable<Shape>, Serializable {
	
	// give these setters for modifying
	protected Point centroid;
	protected Point[] vertices;
	// protected double rotation; // for sadists
	
	protected Shape(Scanner input) {
		System.out.println("- Constructing " + this.getClass().getName() + " -");
		System.out.println("Input the x and y coordinates of the centroid: ");
		centroid = new Point(input.nextInt(), input.nextInt());
	}
	
	// overload constructor to skip centroid prompt
	protected Shape() {}
	
	public void draw(Graphics g, Point shift, double scale) {
		int count = vertices.length;
		int[] xPoints = new int[count];
		int[] yPoints = new int[count];
		for (int i = 0; i < count; i++) {
			xPoints[i] = (int)(scale * (vertices[i].x + centroid.x + shift.x - 250) + 250);
			yPoints[i] = (int)(scale * (vertices[i].y + centroid.y + shift.y - 250) + 250);
		}
		g.drawPolygon(new Polygon(xPoints, yPoints, count));
	}
	
	public abstract double getArea();
	// public abstract double getPeri(); have fun
	
	public int compareTo(Shape other) {
		return (int)(getArea() - other.getArea());
	}
	
	
}
