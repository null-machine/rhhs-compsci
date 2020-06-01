import java.awt.Point;

class Parallelogram extends Shape {
	private double width;
	private double height;

	Parallelogram(Point centroid, int x1, int y1, int x2, int y2) {
		super(centroid);

		setVertices(new Point[] { new Point(x1, y1), new Point(x2, y2), new Point(-x1, -y1), new Point(-x2, -y2) });

		width = getDistance(x1, y1, -x2, -y2);
		height = getDistance(x1, y1, x2, y2);
	}

	@Override
	public double getArea() {
		int sum = 0;
		for (int i = 0; i < 3; i++) {
			sum += getVertices()[i].x * (getVertices()[(i + 1) % 3].y - getVertices()[(i + 2) % 3].y);
		}
		return Math.abs(sum);
	}

	@Override
	public double getPerimeter() {
		return 2 * (width + height);
	}

	@Override
	public String display() {
		String info = "";
		info += super.display();
		info += "\nSidelength 1: " + width;
		info += "\nSidelength 2: " + height;
		
		return info;
	}

	@Override
	public void scale(double scale) {
		for (int i = 0; (getVertices() != null) && (i < getVertices().length); i++) {
			setVertex(new Point(((int) (scale * getVertices()[i].x)), ((int) (scale * getVertices()[i].y))), i);
		}

		width = getDistance(getVertices()[0].x, getVertices()[0].y, -getVertices()[1].x, -getVertices()[1].y);
		height = getDistance(getVertices()[0].x, getVertices()[0].y, getVertices()[1].x, getVertices()[1].y);
	}

	@Override
	public void setVertices(Point[] vertices) {
		super.setVertices(vertices);

		width = getDistance(getVertices()[0].x, getVertices()[0].y, -getVertices()[1].x, -getVertices()[1].y);
		height = getDistance(getVertices()[0].x, getVertices()[0].y, getVertices()[1].x, getVertices()[1].y);
	}

	@Override
	public void setVertex(Point vertex, int i) {
		super.setVertex(vertex, i);
		super.setVertex(new Point(-vertex.x, -vertex.y), i + 2);

		width = getDistance(getVertices()[0].x, getVertices()[0].y, -getVertices()[1].x, -getVertices()[1].y);
		height = getDistance(getVertices()[0].x, getVertices()[0].y, getVertices()[1].x, getVertices()[1].y);
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;

		super.setVertices(new Point[] { new Point((int) (width / 2), (int) (height / 2)),
				new Point((int) (width / 2), (int) (-height / 2)), new Point((int) (-width / 2), (int) (-height / 2)),
				new Point((int) (-width / 2), (int) (height / 2)) });
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;

		super.setVertices(new Point[] { new Point((int) (width / 2), (int) (height / 2)),
			new Point((int) (width / 2), (int) (-height / 2)), new Point((int) (-width / 2), (int) (-height / 2)),
			new Point((int) (-width / 2), (int) (height / 2)) });
	}
}
