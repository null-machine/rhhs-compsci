import java.awt.Point;

class Rhombus extends Parallelogram {
	Rhombus(Point centroid, int x1, int y1, double scale) {
		super(centroid, x1, y1, ((int)(y1 * scale)), ((int)(-x1 * scale)));
	}
}
