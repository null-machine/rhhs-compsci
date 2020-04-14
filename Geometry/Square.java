import java.util.Scanner;
import java.awt.Point;
import java.awt.Graphics;

class Square extends Rectangle {
	
	public Square(Scanner input) {
		super(input, null); // skips rectangle prompts
		System.out.println("Input the width:");
		int width = input.nextInt();
		construct(width, width);
	}
}
