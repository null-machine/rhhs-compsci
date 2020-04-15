import java.util.Scanner;
import java.awt.Point;
import java.awt.Graphics;

class Oval extends Shape {
	
	protected int width;
	protected int height;
	
	public Oval(Scanner input) {
		super(input);
		System.out.print("Input the width and height: ");
		width = input.nextInt() / 2;
		height = input.nextInt() / 2;
		System.out.println();
	}
	
	protected Oval(Scanner input, Object overloader) {
		super(input);
	}
	
	@Override
	public void draw(Graphics g, Point shift, double scale) {
		int scaledcentroidX = (int)(scale * (centroid.x + shift.x - width / 2 - 250) + 250);
		int scaledcentroidY = (int)(scale * (centroid.y + shift.y - height / 2 - 250) + 250);
		int scaledWidth = (int)(scale * width);
		int scaledHeight = (int)(scale * height);
		g.drawOval(scaledcentroidX, scaledcentroidY, scaledWidth, scaledHeight);
	}
	
	@Override
	public double getArea() {
		return Math.PI * width * height;
	}
}
