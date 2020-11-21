import java.awt.Point;
import java.awt.Graphics;

class Oval extends Shape {
	private int width;
	private int height;

	Oval(Point centroid, int width, int height) {
		super(centroid);

		this.width = width;
		this.height = height;
	}

	@Override
	public double getArea() {
		return Math.PI * width / 2 * height / 2;
	}

	@Override
	public double getPerimeter() {
		double h;

		if (width > height) {
			h = (Math.pow(width / 2 - height / 2, 2)) / (Math.pow(width / 2 + height / 2, 2));
		} else {
			h = (Math.pow(height / 2 - width / 2, 2)) / (Math.pow(height / 2 + width / 2, 2));
		}

		return Math.PI * (width / 2 + height / 2) * (1 + (3 * h) / (10 + Math.sqrt(4 - 3 * h)));
	}

	@Override
	public String display() {
		String info = "";
		info += super.display();
		info += "\nWidth: " + width;
		info += "\nHeight: " + height;

		return info;
	}

	@Override
	public void scale(double scale) {
		width = (int) (scale * width);
		height = (int) (scale * height);
	}

	@Override
	public void draw(Graphics g) {
		g.drawOval(((int) (getCentroid().x - width / 2)), ((int) (getCentroid().y - height / 2)), width, height);
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
}
