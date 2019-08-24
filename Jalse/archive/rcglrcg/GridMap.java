import java.util.HashSet;
import java.awt.Color;

public class GridMap {

  private double trackWidth;
  private final double wallWidth = 3;
  private final double precision = 200; // tododododo get rid of precision and use quick sort ish
  // if the distance between halfnode and other nodes is greater than wallwidth, recurse

  private final int wallSize = 32;

  public HashSet<Wall> walls;
  public Wall finish;

  private BezierMap bm;
  // container.revalidate();

  private void addWall(HashSet<Wall> walls, GeoVector pos, boolean brush) {
    GeoVector wallPos = pos.round().mult(wallSize);
    Wall wall = new Wall(wallPos, wallSize);
    if (brush) {
      walls.add(wall);
    } else {
      walls.remove(wall);
    }
  }

  // adds all walls inside radius to walls
  private void brushPos(HashSet<Wall> walls, GeoVector pos, double width, boolean brush) {
    GeoVector[] quadrants = new GeoVector[4];
    Wall tTile;
    double yCap;
    for (int x = 0; x < width; x++) {
      yCap = Math.sqrt(width * width - x * x);
      for (int y = 0; y < yCap; y++) {
        quadrants[0] = new GeoVector(x, y);
        quadrants[1] = new GeoVector(-x, y);
        quadrants[2] = new GeoVector(x, -y);
        quadrants[3] = new GeoVector(-x, -y);
        for (GeoVector wall : quadrants) {
          addWall(walls, pos.add(wall), brush);
        }
      }
    }
  }

  private void brushBezier(HashSet<Wall> walls, Bezier bez, double width, boolean brush) {
    double inc = 1.0 / precision;
    for (int i = 0; i <= precision; ++i) {
      brushPos(walls, bez.eval(i * inc), width, brush);
    }
    finish = new Wall(bez.eval(1).mult(wallSize), wallSize * trackWidth);
    finish.color = Color.WHITE;
  }

  public GridMap(BezierMap bm, double trackWidth) {

    this.bm = bm;
    this.trackWidth = trackWidth;

    walls = new HashSet<Wall>();

    brushBezier(walls, bm.track, trackWidth + wallWidth, true);
    brushBezier(walls, bm.track, trackWidth, false);
  }
}
