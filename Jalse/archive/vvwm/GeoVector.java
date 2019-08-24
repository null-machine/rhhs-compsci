import java.lang.Math;
import java.util.Random;

//
public class GeoVector {

  public double x;
  public double y;

  public static final GeoVector zero = new GeoVector(0.0, 0.0);
  public static final GeoVector up = new GeoVector(0.0, -1.0);
  public static final GeoVector down = new GeoVector(0.0, 1.0);
  public static final GeoVector left = new GeoVector(-1.0, 0.0);
  public static final GeoVector right = new GeoVector(1.0, 0.0);

  // math

  public GeoVector add(GeoVector vector) {
    return new GeoVector(x + vector.x, y + vector.y);
  }
  public GeoVector add(double scalar) {
    return new GeoVector(x + scalar, y + scalar);
  }

  public GeoVector mult(GeoVector vector) {
    return new GeoVector(x * vector.x, y * vector.y);
  }
  public GeoVector mult(double scalar) {
    return new GeoVector(x * scalar, y * scalar);
  }

  public GeoVector neg() {
    return mult(-1.0);
  }

  public GeoVector sub(GeoVector vector) {
    return add(vector.neg());
  }
  public GeoVector sub(double scalar) {
    return add(-scalar);
  }

  public GeoVector round() {
    return new GeoVector(Math.round(x), Math.round(y));
  }

  // advanced

  public GeoVector normalize() {
    if (x != 0.0 || y != 0.0) {
      double mag = mag();
      return new GeoVector(x / mag, y / mag);
    } else {
      return GeoVector.zero;
    }
  }

  public GeoVector lerp(GeoVector targ, double percent) {
    GeoVector dir = targ.sub(this); // direction
    return this.add(dir.mult(percent));
  }

  // rotates vector about the origin by ang radians
  public GeoVector rotate(double ang) {
    double sin = Math.sin(ang);
    double cos = Math.cos(ang);
    GeoVector rotVector = new GeoVector(x * cos - y * sin, y * cos + x * sin);
    return rotVector;
  }

  // random

  public GeoVector randOnRadius(double radius) {
    Random rnd = new Random();
    double rndAng = rnd.nextDouble() * Math.PI * 2;
    GeoVector rndVector = new GeoVector(radius, 0.0);
    rndVector = rndVector.rotate(rndAng);
    return this.add(rndVector);
  }

  public GeoVector randInRadius(double radius) {
    Random rnd = new Random();
    double rndRadius = rnd.nextDouble() * radius;
    return randOnRadius(rndRadius);
  }


  // scalars

  public double mag() {
    return Math.hypot(x, y);
  }

  public double dist(GeoVector vector) {
    return vector.sub(this).mag();
  }

  // helper

  public void print() {
    System.out.println("(" + Math.round(x) + ", " + Math.round(y) + ")");
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof GeoVector)) {
      return false;
    }
    GeoVector other = (GeoVector)o;
    return x == other.x && y == other.y;
  }

  public GeoVector(double x, double y) {
    this.x = x;
    this.y = y;
  }
}
