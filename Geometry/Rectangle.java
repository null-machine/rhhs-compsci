import java.util.Scanner;
import java.awt.Point;
import java.awt.Graphics;

class Rectangle extends Shape {
	
	public Rectangle(Scanner input) {
		super(input);
		System.out.println("Input the width and height:");
		int width = input.nextInt();
		int height = input.nextInt();
		construct(width, height);
	}
	
	protected Rectangle(Scanner input, Object overloader) {
		super(input);
	}
	
	protected void construct(int width, int height) {
		width /= 2;
		height /= 2;
		vertices = new Point[] {
			new Point(width, height),
			new Point(-width, height),
			new Point(-width, -height),
			new Point(width, -height)
		};
	}
	
	@Override
	public double getArea() {
		return Math.abs(vertices[0].x * vertices[0].y) * 4;
	}
}
