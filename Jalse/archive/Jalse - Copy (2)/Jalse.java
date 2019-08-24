import javax.swing.JFrame;
import java.util.ArrayList;

public class Jalse extends JFrame {

  public Jalse() {

    Input input = new Input();

    Player player = new Player(new Vector(200, 200), new Vector(64, 64), 0.5, 0.05);
    ArrayList<Player> players = new ArrayList<Player>();
    players.add(player);

    Engine engine = new Engine(players);

    setTitle("pygame window");
    setResizable(false);
    setSize(1280, 720); // setSize(9999999, Integer.MAX_VALUE);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    getContentPane().add(engine);
    // pack();
    setVisible(true);
  }

  public static void main(String[] args) {
    new Jalse();
  }
}
