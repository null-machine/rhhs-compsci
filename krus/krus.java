import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Kruskal extends Maze {

  private List<List<Tree>> sets;
  private Stack<Edge> edges;

  public Kruskal() {
    super();
    initialize();
  }
  public Kruskal(int w, int h) {
    super(w,h);
    initialize();
  }

  private void initialize() {

    // Initialize the sets to the same dimension as the maze.
    // We use Tree objects to represent the sets to be joined.
    sets = new ArrayList<List<Tree>>();
    for (int y = 0; y < h; ++y) {
      List<Tree> tmp = new ArrayList<Tree>();
      for (int x = 0; x < w ;  ++x) {
        tmp.add(new Tree());
      }
      sets.add(tmp);
    }

    // Build the collection of edges and randomize.
    // Edges are "north" and "west" sides of cell,
    // if index is greater than 0.
    edges = new Stack<Edge>();
    for (int y = 0; y < h; ++y) {
      for (int x = 0; x < w ;  ++x) {
        if (y > 0) 	{ edges.add(new Edge(x,y,Maze.N)); }
        if (x > 0) 	{ edges.add(new Edge(x,y,Maze.W)); }
      }
    }
    shuffle(edges);

    if (!_animate) {
      carvePassages();
    }
  }

  /*******************************************************************
  * Method only need to be overridden if we are animating.
  *
  * If we are drawing the maze statically, defer to the superclass.
  ******************************************************************/
  public void draw() {
    // Clear the screen
    System.out.print((char)27 + "[2J");

    if (!_animate) {
      // Move to the upper left and defer to superclass.
      System.out.print((char)27 + "[H");
      super.draw();
    } else {
      // Carve the passages and animate as we go
      carvePassages();
    }
  }

  /*************************************************
  * Implement Kruskal's algorithm.
  *
  * (1) Randomly select an edge.
  * (2) If the sets are not already connected, then
  * (3) Connect the sets, and
  * (4) Knock down the wall between the sets.
  * (5) Repeat at Step 1.
  *************************************************/
  private void carvePassages() {
    while (edges.size() > 0) {
      // Select the next edge, and decide which direction we are going in.
      Edge tmp = edges.pop();
      int x = tmp.getX();
      int y = tmp.getY();
      int direction = tmp.getDirection();
      int dx = x + Maze.DX(direction), dy = y + Maze.DY(direction);

      // Pluck out the corresponding sets
      Tree set1 = (sets.get(y)).get(x);
      Tree set2 = (sets.get(dy)).get(dx);

      if (!set1.connected(set2)) {
        // If we are animating, display the maze and pause
        if (_animate) {
          display();
          try {
            Thread.sleep((long)(_delay * 1000));
          } catch (Exception ex) {
            ex.printStackTrace();
          }
        }

        // Connect the two sets and "knock down" the wall between them.
        set1.connect(set2);
        _grid[y][x] |= direction;
        _grid[dy][dx] |= Maze.OPPOSITE(direction);
      }
    }
  }

  private void shuffle(List<Edge> edges) {
    for (int i = 0; i < edges.size(); ++i) {
      int index = random.nextInt(edges.size());
      Edge tmp1 = edges.get(i);
      Edge tmp2 = edges.get(index);
      edges.set(i, tmp2);
      edges.set(index, tmp1);
    }
  }
}

class Tree {

  private Tree parent = null;

  public Tree root() {
    return parent != null ? parent.root() : this;
  }

  public boolean connected(Tree tree) {
    return this.root() == tree.root();
  }

  public void connect(Tree tree) {
    tree.root().setParent(this);
  }

  public void setParent(Tree parent) {
    this.parent = parent;
  }
}

class Edge {
  private int x;
  public int getX() { return x; }
  private int y;
  public int getY() { return y; }
  private int direction;
  public int getDirection() { return direction; }

  public Edge(int x, int y, int direction) {
    this.x = x;
    this.y = y;
    this.direction = direction;
  }
}
