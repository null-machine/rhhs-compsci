import java.util.HashSet;
import java.awt.Color;

/**
 * A physically simulated AbstractMap.
 * @author Glen Wang
 * @version 1.0
 * @date June 11, 2019
 */
public class WallMap {

  private final int WALL_SIZE = 32; // size of each individual wall
  private final Color WALL_COLOR = new Color(248, 248, 0); // color of walls
  private final Color FINISH_COLOR = new Color(248, 96, 0);

  private HashSet<Wall> walls;

  /**
  * Gets the walls created by this.
  * @return A hashset of walls.
  */
  public HashSet<Wall> getWalls() {
    return walls;
  }

  private Wall finish;

  /**
  * Gets the finish wall.
  * @return The finish wall at the end of the tracks.
  */
  public Wall getFinish() {
    return finish;
  }

  private void addWall(HashSet<Wall> walls, GeoVector pos, boolean brush) {
    GeoVector wallPos = pos.round().mult(WALL_SIZE);
    Wall wall = new Wall(wallPos, WALL_SIZE, WALL_COLOR);
    if (brush) {
      walls.add(wall);
    } else {
      walls.remove(wall);
    }
  }

  // adds all walls inside radius (width) to set
  private void brushPos(HashSet<Wall> walls, GeoVector pos, double width, boolean brush) {
    GeoVector[] quadrants = new GeoVector[4]; // four quadrants of a circle
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

  private void brushPath(HashSet<Wall> walls, PathNode start, double wallWidth, boolean brush) {
    brushPos(walls, start.pos, wallWidth, brush);
    if (start.next != null) {
      brushPath(walls, start.next, wallWidth, brush);
    }
  }

  // scales path nodes to match walls
  private void scalePath(PathNode start) {
    start.pos = start.pos.mult(WALL_SIZE);
    if (start.next != null) {
      scalePath(start.next);
    }
  }

  /**
  * Constructs a wall map from an abstract map.
  * @param abstractMap The map this is based on.
  * @param wallWidth The width of the track walls.
  */
  public WallMap(AbstractMap abstractMap, double wallWidth) {
    walls = new HashSet<Wall>();

    double trackWidth = abstractMap.getWidth();
    PathNode start = abstractMap.getStart();

    brushPath(walls, start, trackWidth + wallWidth, true);
    brushPath(walls, start, trackWidth, false);
    scalePath(start);

    finish = new Wall(abstractMap.getTrack().eval(1.0).mult(WALL_SIZE), WALL_SIZE * trackWidth, FINISH_COLOR);
  }
}
