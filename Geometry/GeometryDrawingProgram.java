import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Point;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

public class GeometryDrawingProgram {
	private static JFrame frame;
	private static ArrayList<Shape> shapes;
	
	public static void main(String[] args) {
		shapes = new ArrayList<Shape>();
		GeometryScreen gs = new GeometryScreen();
		Scanner input = new Scanner(System.in);
		
		int choice;
		while (true) {
			System.out.println("\n0 - Add\n1 - Remove\n2 - Modify\n3 - Translate\n4 - Scale\n5 - Save\n6 - Load\n7 - Quit");
			System.out.println("Input your choice: ");
			choice = input.nextInt();
			if (choice == 0) {
				System.out.println("0 - Parallelogram\n1 - Rectangle\n2 - Square\n3 - Rhombus\n4 - Oval\n5 - Circle\n6 - Triangle");
				System.out.println("Input your choice: ");
				choice = input.nextInt();
				if (choice == 0) shapes.add(new Parallelogram(input));
				else if (choice == 1) shapes.add(new Rectangle(input));
				else if (choice == 2) shapes.add(new Square(input));
				else if (choice == 3) shapes.add(new Rhombus(input));
				else if (choice == 4) shapes.add(new Oval(input));
				else if (choice == 5) shapes.add(new Circle(input));
				else if (choice == 6) shapes.add(new Triangle(input));
			} else if (choice == 1) {
				display();
				System.out.println("Input your choice to delete: ");
				choice = input.nextInt();
				if (choice >= 0 || choice < shapes.size()) shapes.remove(choice);
				else System.out.println("insert witty failure message here");
			} else if (choice == 2) {
				System.out.println("have fun writing a billion getters and setters cause i sure wouldnt");
				System.out.println("(but also remember to recompute centroids after changing vertices)");
			} else if (choice == 3) {
				System.out.println("Input the x and y shift: ");
				int shiftX = input.nextInt();
				int shiftY = input.nextInt();
				gs.shift(shiftX, shiftY);
			} else if (choice == 4) {
				System.out.println("Input the scale factor (double): ");
				gs.scale(input.nextDouble());
			} else if (choice == 5) {
				System.out.println("Input the scale factor (double): ");
			} else if (choice == 6) {
				System.out.println("Input the scale factor (double): ");
			} else if (choice == 7) {
				input.close(); // :glares angrily at petty mark lost on my own summative:
				System.exit(0);
			}
			frame.repaint();
		}
	}
	
	private static void display() {
		Collections.sort(shapes);
		for (int i = 0; i < shapes.size(); i++) {
			System.out.println(i + " - " + shapes.get(i).getClass().getName());
			// should println more info but i personally cannot be bothered
		}
	}
	
	private static class GeometryScreen {
		
		private static Point shift = new Point(0, 0);
		public void shift(int x, int y) { shift = new Point(shift.x + x, shift.y + y); }
		
		private static double scale = 1.0;
		public void scale(double scale) { this.scale *= scale; }
		
		private GeometryScreen() {
			frame = new JFrame("Geometry Drawing Program 1.0");
			frame.getContentPane().add(new GraphicsPanel());
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setSize(520, 540);
			frame.setVisible(true);
		}
		
		private static class GraphicsPanel extends JPanel {
			
			public void paintComponent(Graphics g) {
				setDoubleBuffered(true);
				g.setColor(Color.GRAY);
				g.drawLine(250, 0, 250, 500);
				g.drawLine(0, 250, 500, 250);
				g.setColor(Color.BLACK);
				for (int i = 0; i < shapes.size(); i++) {
					shapes.get(i).draw(g, shift, scale);
				}
			}
		}
	}
}
