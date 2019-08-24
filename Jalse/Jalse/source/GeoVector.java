import java.lang.Math;
import java.util.Random;

/**
* A geometric, two-dimensional, Euclidean vector.
* It supports basic vector arithmetic and methods
* for video games and procedural generation.
* @author Glen Wang
* @version 1.0
* @date June 11, 2019
*/
public class GeoVector {

  // all collections have public properties
  public double x;
  public double y;

  public static final GeoVector zero = new GeoVector(0.0, 0.0);
  public static final GeoVector up = new GeoVector(0.0, -1.0);
  public static final GeoVector down = new GeoVector(0.0, 1.0);
  public static final GeoVector left = new GeoVector(-1.0, 0.0);
  public static final GeoVector right = new GeoVector(1.0, 0.0);

  /**
  * Clones this, with the same x and y values.
  * @return A deep copy of this.
  */
  public GeoVector copy() {
    return new GeoVector(x, y);
  }

  /**
  * Multiplies the x and y values of this with a scalar.
  * @param scalar The scalar to multiply this with.
  * @return A vector with multiplied x and y values.
  */
  public GeoVector mult(double scalar) {
    return new GeoVector(x * scalar, y * scalar);
  }

  /**
  * Multiplies the x and y values of this with negative one.
  * @return This vector with polarity-switched x and y values.
  */
  public GeoVector neg() {
    return mult(-1.0);
  }

  /**
  * Performs an entrywise sum of this and another vector.
  * @param vector The vector to add to this.
  * @return A vector with summed x and y values.
  */
  public GeoVector add(GeoVector vector) {
    return new GeoVector(x + vector.x, y + vector.y);
  }

  /**
  * Performs an entrywise subtraction of this and another vector.
  * @param vector The vector to subtract from this.
  * @return A vector with subtracted x and y values.
  */
  public GeoVector sub(GeoVector vector) {
    return add(vector.neg());
  }

  /**
  * Rounds the components of this vector to integer values.
  * @return A vector with rounded x and y values.
  */
  public GeoVector round() {
    return new GeoVector(Math.round(x), Math.round(y));
  }

  /**
  * Normalizes this vector.
  * @return A vector with a magnitude of one that
  * preserves the original direction.
  */
  public GeoVector normalize() {
    if (x != 0.0 || y != 0.0) {
      double mag = mag();
      return new GeoVector(x / mag, y / mag);
    } else {
      return GeoVector.zero;
    }
  }

  /**
  * Linearly interpolates between this and a target vector.
  * @param targ The target vector.
  * @param percent The fraction from this to the target.
  * @return A vector pointing to the lerped location.
  */
  public GeoVector lerp(GeoVector targ, double percent) {
    GeoVector dir = targ.sub(this);
    return this.add(dir.mult(percent));
  }

  /**
  * Rotates this vector clockwise about the origin by an angle.
  * @param ang The angle in radians.
  * @return The rotated vector.
  */
  public GeoVector rotate(double ang) {
    double sin = Math.sin(ang);
    double cos = Math.cos(ang);
    GeoVector rotVector = new GeoVector(
    x * cos - y * sin,
    y * cos + x * sin
    );
    return rotVector;
  }

  /**
  * Gets a random point on a circle centered on this vector.
  * @param radius The circle's radius.
  * @return The randomly selected vector.
  */
  public GeoVector randOnRadius(double radius) {
    Random rnd = new Random();
    double rndAng = rnd.nextDouble() * Math.PI * 2;
    GeoVector rndVector = new GeoVector(radius, 0.0);
    rndVector = rndVector.rotate(rndAng);
    return this.add(rndVector);
  }

  /**
  * Gets a random point within a circle centered on this vector.
  * @param radius The circle's radius.
  * @return The randomly selected vector.
  */
  public GeoVector randInRadius(double radius) {
    Random rnd = new Random();
    double rndRadius = rnd.nextDouble() * radius;
    return randOnRadius(rndRadius);
  }

  /**
  * Gets the magnitude of this vector.
  * @return The magnitude of this.
  */
  public double mag() {
    return Math.hypot(x, y);
  }

  /**
  * Gets the distance between this and another vector.
  * @param vector The other vector.
  * @return The distance between this and another vector.
  */
  public double dist(GeoVector vector) {
    return vector.sub(this).mag();
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof GeoVector)) {
      return false;
    }
    GeoVector other = (GeoVector)o;
    return x == other.x && y == other.y;
  }

  /**
  * Constructs a new vector with x and y values.
  * @param x The x value.
  * @param y The y value.
  */
  public GeoVector(double x, double y) {
    this.x = x;
    this.y = y;
  }
}
