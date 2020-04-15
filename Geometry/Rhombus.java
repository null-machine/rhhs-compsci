import java.util.Scanner;
import java.awt.Point;
import java.awt.Graphics;

class Rhombus extends Parallelogram {
	
	public Rhombus(Scanner input) {
		super(input, null);
		System.out.println("Input the x and y offsets of a corner: ");
		int oneX = input.nextInt();
		int oneY = input.nextInt();
		System.out.println("\nInput the scaling of the other corner (multiplicative): ");
		double scale = input.nextDouble();
		
		int twoX = (int)(oneY * scale);
		int twoY = (int)(-oneX * scale);
		construct(oneX, oneY, twoX, twoY);
	}
}
