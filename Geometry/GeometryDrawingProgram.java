import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Point;
import java.util.Scanner;
import java.util.ArrayList;

public class GeometryDrawingProgram {
	private static JFrame frame;
	private static ArrayList<Shape> shapes;
	
	public static void main(String[] args) {
		shapes = new ArrayList<Shape>();
		GeometryScreen gs = new GeometryScreen();
		Scanner input = new Scanner(System.in);
		
		// System.out.println("0 - ADD\n1 - REMOVE\n2 - MODIFY\n3 - TRANSLATE\n4 - SCALE\n5 - SAVE\n6 - LOAD\n7 - QUIT\n");
		
		/*
		what the loop should look like
		1) get input
		2) if else jungle to determine what the user wants
		3) apply changes
		4) frame.repaint to update changes
		5) loop
		*/
		
		while (true) {
			shapes.add(new Triangle(input));
			System.out.println(shapes.get(0).getArea());
			frame.repaint();
		}
	}
	
	private static class GeometryScreen {
		
		private static Point shift = new Point(0, 0);
		public void translate(int x, int y) { shift = new Point(shift.x + x, shift.y + y); }
		
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
