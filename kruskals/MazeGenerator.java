import java.util.ArrayList;
import java.util.Random;
import java.util.Point;

public class MazeGenerator {

  public char[][] map;

  private Cell[][] cells;
  private ArrayList<Wall> walls;
  private Point size = new Point(5, 7); // size for cell map, not char map
  // coordinates are cartesian; x refers to column, y to row

  private Random rnd = new Random();

  private int mapCell(int coord) {
    return coord * 2 + 1;
  }

  private void genCellGrid() {
    for (int y = 0; y < size.y; y++) {
      for (int x = 0; x < size.x; x++) {
        cells[x][y] = new Cell(mapCell(x), mapCell(y));
        if (x > 0) {
          walls.add(new Wall(cells[x][y], cells[x - 1][y], x * 2, y * 2));
        }
        if (y > 0) {
          walls.add(new Wall(cells[x][y], cells[x][y - 1], x * 2, y * 2));
        }
      }
    }
  }

  private void genNodeGrid() {

    nodeGrid = new MazeNode[size][size];
    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        nodeGrid[i][j] = new MazeNode();
      }
    }

    // vert links
    for (int row = 1; row < size; row++) {
      for (int col = 0; col < size; col++) {
        nodeGrid[row][col].north = nodeGrid[row - 1][col];
        nodeGrid[row][col].addLink("N");
        nodeGrid[row - 1][col].south = nodeGrid[row][col];
        nodeGrid[row - 1][col].addLink("S");
      }
    }

    // horz links
    for (int col = 1; col < size; col++) {
      for (int row = 0; row < size; row++) {
        nodeGrid[row][col].west = nodeGrid[row][col - 1];
        nodeGrid[row][col].addLink("W");
        nodeGrid[row][col - 1].east = nodeGrid[row][col];
        nodeGrid[row][col - 1].addLink("E");
      }
    }
  }

  private void genPaths() {
    for (int row = 0; row < size; row++) {
      for (int col = 0; col < size; col++) {
        if (nodeGrid[row][col].openLinks.length() > 2) {
          nodeGrid[row][col].linkPath();
        }
      }
    }
  }

  private void genCharMap() {
    int mapSize = size * 2 + 1;
    map = new char[mapSize][mapSize];

    // central walls
    for (int row = 1; row < mapSize - 1; row++) {
      for (int col = 1; col < mapSize - 1; col++) {
        map[row][col] = 'W';
      }
    }

    // paths
    for (int row = 0; row < size; row++) {
      for (int col = 0; col < size; col++) {
        String links = nodeGrid[row][col].openLinks;
        int mapRow = row * 2 + 1;
        int mapCol = col * 2 + 1;
        map[mapRow][mapCol] = ' ';
        if (!links.contains("N")) {
          map[mapRow - 1][mapCol] = ' ';
        }
        if (!links.contains("E")) {
          map[mapRow][mapCol + 1] = ' ';
        }
        if (!links.contains("S")) {
          map[mapRow + 1][mapCol] = ' ';
        }
        if (!links.contains("W")) {
          map[mapRow][mapCol - 1] = ' ';
        }
      }
    }

    // borders (overwrites paths)
    for (int row = 0; row < mapSize; row++) {
      map[row][0] = 'W';
      map[row][mapSize - 1] = 'W';
    }
    for (int col = 0; col < mapSize; col++) {
      map[0][col] = 'W';
      map[mapSize - 1][col] = 'W';
    }

    // printing
    for (int i = 0; i < mapSize; i++) {
      for (int j = 0; j < mapSize; j++) {
        System.out.print(map[i][j] + " ");
      }
      System.out.println();
    }
  }

  public MazeGenerator() {
    unlinkedNodes = new ArrayList<MazeNode>();
    genNodeGrid();
    genPaths();
    genCharMap();
  }

  public static void main(String[] args) {
    new MazeGenerator();
  }
}
