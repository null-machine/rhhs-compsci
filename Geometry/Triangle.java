import java.util.Scanner;
import java.awt.Point;
import java.awt.Graphics;

class Triangle extends Shape {
	
	public Triangle(Scanner input) {
		super(); // skips centroid prompt
		vertices = new Point[3];
		int xSum = 0;
		int ySum = 0;
		for (int i = 0; i < vertices.length; i++) {
			System.out.println("Input the x and y coordinates of vertex " + (i + 1) + ":");
			int x = input.nextInt();
			int y = input.nextInt();
			xSum += x;
			ySum += y;
			vertices[i] = new Point(x, y);
		}
		centroid = new Point(xSum / 3, ySum / 3);
		
	}
	
	@Override
	public double getArea() { // :woogles fingers: magic! or vectors or smth idk
		int sum = 0;
		for (int i = 0; i < 3; i++) {
			sum += vertices[i].x * (vertices[(i + 1) % 3].y - vertices[(i + 2) % 3].y);
		}
		return Math.abs(sum) / 2;
	}
}
