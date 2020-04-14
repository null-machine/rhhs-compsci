import java.util.Scanner;
import java.awt.Point;
import java.awt.Graphics;

class Square extends Rectangle {
	// only benefit of extending rect insteada parallelo is the faster getArea()
	
	public Square(Scanner input) {
		super(input, null); // skips rectangle prompts
		System.out.println("Input the width:");
		int width = input.nextInt() / 2;
		construct(width, width, width, -width);
	}
}
