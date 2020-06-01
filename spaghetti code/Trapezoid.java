import java.awt.Point;

class Trapezoid extends Shape {
	
	private double width2;
	
	Trapezoid(Point anchorOne, Point anchorTwo, Point dir, int width) {
		super(new Point(0, 0));
		// cant normalize, point stores ints
		Point[] vertices = new Point[] {
			anchorOne,
			anchorTwo,
			new Point(anchorOne.x + dir.x, anchorOne.y + dir.y),
			new Point(anchorTwo.x + dir.x * width, anchorTwo.y + dir.y * width)
		};
		setVertices(vertices);
		
		Point centroid = new Point(0, 0);
		for (int i = 0; i < 4; i++) {
			centroid = new Point(centroid.x + vertices[i].x, centroid.y + vertices[i].y);
		}
		centroid = new Point(centroid.x / 4, centroid.y / 4);
		for (int i = 0; i < 4; i++) {
			vertices[i] = new Point(vertices[i].x - centroid.x, vertices[i].y - centroid.y);
			centroid = new Point(centroid.x + vertices[i].x, centroid.y + vertices[i].y);
		}
	}
	
	@Override
	public double getArea() {
		System.out.println("i need a hazmat suit to work on this");
		return 420;
	}
	
	@Override
	public double getPerimeter() {
		return 69;
	}
}
