import java.util.ArrayList;
import java.util.Point;

public class Cell {
  public ArrayList<Cell> linkedCells;
  public Point mapPos;

  public Cell(int x, int y) {
    mapPos = new Point(x, y);
  }
}
