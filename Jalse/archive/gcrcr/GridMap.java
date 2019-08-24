import java.util.HashSet;
import java.awt.Color;

/**
 * ColliderMap
 * A physically simulated AbstractMap.
 * @author Glen Wang
 **/
public class GridMap {

  private double trackWidth;

  private final double wallWidth = 3;
  private final int wallSize = 32;

  public HashSet<Wall> walls;
  public Wall finish;

  public BezierMap bm;
  public double getTrackLength() {
    return bm.track.end.dist(bm.track.start);
  }

  // reason for your tears
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

  private void brushPath(HashSet<Wall> walls, PathNode start, double width, boolean brush) {
    brushPos(walls, start.pos, width, brush);
    if (start.next != null) {
      brushPath(walls, start.next, width, brush);
    }
  }

  private void scalePath(PathNode start) {
    start.pos = start.pos.mult(wallSize);

    if (start.next != null) {
      scalePath(start.next);
    }
  }

  public GridMap(BezierMap bm, double trackWidth) {

    this.bm = bm;
    this.trackWidth = trackWidth;

    walls = new HashSet<Wall>();

    brushPath(walls, bm.start, trackWidth + wallWidth, true);
    brushPath(walls, bm.start, trackWidth, false);
    scalePath(bm.start);

    finish = new Wall(bm.track.eval(1.0).mult(wallSize), wallSize * trackWidth);
    finish.color = Color.WHITE;
  }
}
