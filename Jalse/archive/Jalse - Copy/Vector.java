
// 2d vector
public class Vector {

  public double x;
  public double y;

  public static final Vector zero = new Vector(0.0, 0.0);

  // vector calculations

  public Vector add(Vector vector) {
    return new Vector(x + vector.x, y + vector.y);
  }
  public Vector add(double scalar) {
    return new Vector(x + scalar, y + scalar);
  }

  public Vector mult(Vector vector) {
    return new Vector(x * vector.x, y * vector.y);
  }
  public Vector mult(double scalar) {
    return new Vector(x * scalar, y * scalar);
  }

  public Vector neg() {
    return mult(-1.0);
  }

  public Vector sub(Vector vector) {
    return add(vector.neg());
  }
  public Vector sub(double scalar) {
    return add(-scalar);
  }

  public Vector rotate(double ang) {
    double sin = Math.sin(ang);
    double cos = Math.cos(ang);
    return new Vector(x * cos - y * sin, y * cos + x * sin);
  }

  public Vector normalize() {
    if (x != 0 && y != 0) {
      double mag = mag();
      return new Vector(x / mag, y / mag);
    } else {
      return Vector.zero;
    }
  }

  // scalar calculations

  public double sqrMag() {
    return x * x + y * y;
  }

  public double mag() {
    return Math.hypot(x, y);
  }

  public void print() {
    System.out.println("(" + x + " " + y + ")");
  }

  public Vector(double x, double y) {
    this.x = x;
    this.y = y;
  }
}
