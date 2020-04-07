import java.lang.Math;
import java.awt.Point;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

// not rhhs standards but that sure aint my problem

class Circle {
	
	// should be in: public class Main
	// but repl only has one file so  w e l p
	public static void main(String[] args) {
		
		// System.out.println(circle.getCenter());
		// System.out.println(circle.getRadius());
		// System.out.println(circle.getArea());
		// System.out.println(circle.getCircumference());
		// System.out.println(circle.toString());
		
		new Frame();
	}
	
	private Point center;
	private double radius;
	
	Circle(Point center, double radius) {
		this.center = center;
		this.radius = radius;
	}
	
	public double getRadius() {
		return radius;
	}
	
	public double getArea() {
		return Math.PI * Math.pow(radius,2);
	}
	
	public double getCircumference() {
		return 2 * Math.PI * radius;
	}
	
	public String toString() {
		return "Circle[center=(" + center.getX() + "," + center.getY() + "), radius=" + radius + "]";
	}
	
	public void draw(Graphics g) {
		g.drawOval((int)center.getX(), (int)center.getY(), (int)radius, (int)radius);
	}
	
	public boolean contains(Point p) {
		return (radius > Math.sqrt(Math.pow((p.x - center.x), 2) + Math.pow((p.y - center.y), 2)));
	}
}

class Frame extends JFrame {
	
	public Frame() {
		super("fuck gui");
		setSize(500, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Point center = new Point(200, 300);
		Circle circle = new Circle(center, 100);
		setContentPane(new Renderer(circle));
		
		revalidate();
		setVisible(true);
	}
}

class Renderer extends JPanel {
	
	private Circle circle;
	
	public Renderer(Circle circle) {
		this.circle = circle;
	}
	
	public void paintComponent(Graphics g) {
		circle.draw(g);
	}
}
