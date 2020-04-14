import java.util.Scanner;
import java.awt.Point;
import java.awt.Graphics;

class Parallelogram extends Shape {
	
	public Parallelogram(Scanner input) {
		super(input);
		System.out.println("Input the x and y offsets of a corner:");
		Point vertexOne = new Point(input.nextInt(), input.nextInt());
		System.out.println("Input the x offset of the other corner:");
		Point vertexTwo = new Point(input.nextInt(), vertexOne.y);
		
	}
	
	protected Parallelogram(Scanner input, Object overloader) {
		super(input);
	}
	
	protected void construct(int width, int height) {
		area = width * height;
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
		return area;
	}
}
