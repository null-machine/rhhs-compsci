import java.util.Scanner;
import java.awt.Point;
import java.awt.Graphics;

class Parallelogram extends Shape {
	
	public Parallelogram(Scanner input) {
		super(input);
		System.out.println("Input the x and y offsets of a corner: ");
		int oneX = input.nextInt();
		int oneY = input.nextInt();
		System.out.println("Input the x and y offsets of another corner: ");
		int twoX = input.nextInt();
		int twoY = input.nextInt();
		construct(oneX, oneY, twoX, twoY);
	}
	
	protected Parallelogram(Scanner input, Object overloader) {
		super(input);
	}
	
	protected void construct(int oneX, int oneY, int twoX, int twoY) {
		vertices = new Point[4];
		vertices[0] = new Point(oneX, oneY);
		vertices[1] = new Point(twoX, twoY);
		vertices[2] = new Point(-oneX, -oneY);
		vertices[3] = new Point(-twoX, -twoY);
	}
	
	@Override
	public double getArea() {
		int sum = 0;
		for (int i = 0; i < 3; i++) {
			sum += vertices[i].x * (vertices[(i + 1) % 3].y - vertices[(i + 2) % 3].y);
		}
		return Math.abs(sum); // diff from triangle
	}
}
