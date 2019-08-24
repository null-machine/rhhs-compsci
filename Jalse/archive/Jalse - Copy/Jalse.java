import javax.swing.JFrame;
import java.util.ArrayList;

public class Jalse extends JFrame {

  public Jalse() {

    // Vector v = Vector.zero;
    Input input = new Input();
    Player player = new Player(new Vector(200, 200), new Vector(64, 64), 0.5, 0.05);
    ArrayList<Entity> entities = new ArrayList<Entity>();
    entities.add(player);
    Engine engine = new Engine(entities);

    setTitle("pygame window");
    setResizable(false);
    setSize(1280, 720); // setSize(9999999, Integer.MAX_VALUE);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    Renderer renderer = new Renderer(entities);
    getContentPane().add(renderer);
    // pack();
    setVisible(true);
  }

  public static void main(String[] args) {
    new Jalse();
  }
}
