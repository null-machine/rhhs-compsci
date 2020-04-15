import java.util.Scanner;
import java.awt.Point;
import java.awt.Graphics;

class Rectangle extends Parallelogram {
	
	public Rectangle(Scanner input) {
		super(input, null);
		System.out.println("Input the width and height: ");
		int width = input.nextInt() / 2;
		int height = input.nextInt() / 2;
		construct(width, height, width, -height);
	}
	
	protected Rectangle(Scanner input, Object overloader) {
		super(input, overloader);
	}
	
	@Override
	public double getArea() { // faster than parallelo's but parallelo's works too
		return Math.abs(vertices[0].x * vertices[0].y) * 4;
	}
}
