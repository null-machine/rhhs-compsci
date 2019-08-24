import javax.swing.JFrame;
import java.util.ArrayList;

class Jalse extends JFrame {

  private double trackLength = 200;
  private double trackWidth = 5;
  private int pivotCount = 12;
  private int wallDamage = 100;

  public Jalse() {

    setTitle("pygame window");
    setResizable(false);
    setSize(1280, 720);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    Input input = new Input();

    Player player = new Player(GeoVector.zero, 1, 0.05);
    ArrayList<Player> players = new ArrayList<Player>();
    players.add(player);

    BezierMap bm = new BezierMap(trackLength, pivotCount);
    GridMap gm = new GridMap(bm, trackWidth);

    Engine engine = new Engine(players, gm.walls, gm.finish, wallDamage);

    getContentPane().add(engine);
    setVisible(true);
    // pack();
  }

  public static void main(String[] args) {
    new Jalse();
  }
}
