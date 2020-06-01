import java.awt.Point;

class Triangle extends Shape {
	public Triangle(int[][] vertices) {
		super(new Point((vertices[0][0] + vertices[1][0] + vertices[2][0]) / 3, (vertices[0][1] + vertices[1][1] + vertices[2][1]) / 3));
		
		setVertices(new Point[3]);
		for (int i = 0; i < 3; i++) {
			setVertex(new Point(vertices[i][0], vertices[i][1]), i);
			setVertex(new Point(getVertices()[i].x - getCentroid().x, getVertices()[i].y - getCentroid().y), i);
		}
	}

	@Override
	public double getArea() {
		int sum = 0;
		for (int i = 0; i < 3; i++) {
			sum += getVertices()[i].x * (getVertices()[(i + 1) % 3].y - getVertices()[(i + 2) % 3].y);
		}
		return Math.abs(sum) / 2;
	}

	@Override
	public double getPerimeter() {
		double perimeter = 0;

		perimeter += getDistance(getVertices()[0].x, getVertices()[0].y, getVertices()[1].x, getVertices()[1].y);
		perimeter += getDistance(getVertices()[0].x, getVertices()[0].y, getVertices()[2].x, getVertices()[2].y);
		perimeter += getDistance(getVertices()[1].x, getVertices()[1].y, getVertices()[2].x, getVertices()[2].y);

		return perimeter;
	}
}
