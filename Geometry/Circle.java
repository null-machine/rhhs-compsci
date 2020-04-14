import java.util.Scanner;
import java.awt.Point;
import java.awt.Graphics;

class Circle extends Shape {
	
	public int radius;
	
	public Circle(Scanner input) {
		super(input);
		System.out.println("Input the radius:");
		radius = input.nextInt();
	}
	
	@Override
	public void draw(Graphics g, Point shift, double scale) {
		int scaledcentroidX = (int)(scale * (centroid.x + shift.x - radius / 2 - 250) + 250);
		int scaledcentroidY = (int)(scale * (centroid.y + shift.y - radius / 2 - 250) + 250);
		int scaledRadius = (int)(scale * radius);
		g.drawOval(scaledcentroidX, scaledcentroidY, scaledRadius, scaledRadius);
	}
	
	@Override
	public double getArea() {
		return Math.PI * radius * radius;
	}
}
