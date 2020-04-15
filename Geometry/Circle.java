import java.util.Scanner;
import java.awt.Point;
import java.awt.Graphics;

class Circle extends Oval {
	
	public Circle(Scanner input) {
		super(input, null);
		System.out.print("Input the radius: ");
		width = input.nextInt();
		height = width;
	}
}
