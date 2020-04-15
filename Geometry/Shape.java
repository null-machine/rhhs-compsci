import java.util.Scanner;
import java.awt.Point;
import java.awt.Graphics;
import java.awt.Polygon;
import java.io.Serializable;

abstract class Shape implements Comparable<Shape>, Serializable {
	// serializable is so good i bet mangat doesnt want you to know about it
	
	// give these setters for modifying
	protected Point centroid; // make a custom point class that works with doubles
	protected Point[] vertices; // if you care abt preserving data over scales (i do not)
	// protected double rotation;
	
	protected Shape(Scanner input) {
		System.out.println("- Constructing " + this.getClass().getName() + " -");
		System.out.println("Input the x and y coordinates of the centroid: ");
		centroid = new Point(input.nextInt(), input.nextInt());
	}
	
	// overload constructor to allow triangle to skip centroid prompt
	// kinda messy but mayb you can think o smth better
	protected Shape() {}
		
		// public abstract void modify(Scanner input);
		// have fun lmao
		// modify should be similar to the constructor
		// all the prompts are contained in the shape class instead of GDP
		
		public abstract double getArea();
		// public abstract double getPerimeter(); // L
		
		public void scale(double scale) {
			centroid = new Point((int)(scale * (centroid.x - 250) + 250), (int)(scale * (centroid.y - 250) + 250));
			for (int i = 0; vertices != null && i < vertices.length; i++) {
				vertices[i] = new Point((int)(scale * vertices[i].x), (int)(scale * vertices[i].y));
			}
		}
		
		public void shift(int shiftX, int shiftY) {
			centroid = new Point(centroid.x + shiftX, centroid.y + shiftY);
			for (int i = 0; vertices != null && i < vertices.length; i++) {
				vertices[i] = new Point(vertices[i].x + shiftX, vertices[i].y + shiftY);
			}
		}
		
		public void draw(Graphics g, Point shift, double scale) {
			int count = vertices.length;
			int[] xPoints = new int[count];
			int[] yPoints = new int[count];
			for (int i = 0; i < count; i++) {
				// xPoints[i] = (int)(scale * (vertices[i].x + centroid.x + shift.x - 250) + 250);
				// yPoints[i] = (int)(scale * (vertices[i].y + centroid.y + shift.y - 250) + 250);
				xPoints[i] = (int)(vertices[i].x + centroid.x);
				yPoints[i] = (int)(vertices[i].y + centroid.y);
			}
			g.drawPolygon(new Polygon(xPoints, yPoints, count));
		}
		
		public int compareTo(Shape other) { // collections.sort cheese
			return (int)(getArea() - other.getArea());
		}
	}
