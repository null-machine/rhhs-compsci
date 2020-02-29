import java.util.ArrayList;
import java.util.Point;

import java.util.Collections; // just for disjoint

public class Link {
  public Cell a;
  public Cell b;
  public Point mapPos;

  public void merge() {
    if (Collections.disjoint(a.linkedCells, b.linkedCells)) {
      a.linkedCells.addAll(b.linkedCells);
      b.linkedCells = a.linkedCells;
      mapPos = null;
    }
  }
}
