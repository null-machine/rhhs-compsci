import java.util.Scanner;
import java.awt.Point;
import java.awt.Graphics;

class Oval extends Shape {
	
	protected int width;
	protected int height;
	
	public Oval(Scanner input) {
		super(input);
		System.out.println("Input the width and height: ");
		width = input.nextInt() / 2;
		height = input.nextInt() / 2;
	}
	
	protected Oval(Scanner input, Object overloader) {
		super(input);
	}
	
	@Override
	public void scale(double scale) {
		centroid = new Point((int)(scale * (centroid.x - 250) + 250), (int)(scale * (centroid.y - 250) + 250));
		width = (int)(scale * width);
		height = (int)(scale * height);
	}
	
	@Override
	public void draw(Graphics g) {
		int scaledcentroidX = (int)(centroid.x - width / 2);
		int scaledcentroidY = (int)(centroid.y - height / 2);
		g.drawOval(scaledcentroidX, scaledcentroidY, width, height);
	}
	
	@Override
	public double getArea() {
		return Math.PI * width * height;
	}
}
