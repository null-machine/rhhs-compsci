import javax.swing.JFrame;
import java.util.ArrayList;

public class Jalse extends JFrame {

  private double trackLength = 200;
  private double trackWidth = 5;
  private int pivotCount = 12;

  public Jalse() {

    setTitle("pygame window");
    setSize(1280, 720);
    setExtendedState(JFrame.MAXIMIZED_BOTH);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    Input input = new Input();

    ParticlePool pp = new ParticlePool(100);
    Racer racer = new Racer(new Arrows(input), new Speed());
    ArrayList<Racer> racers = new ArrayList<Racer>();
    racers.add(racer);

    BezierMap bm = new BezierMap(trackLength, pivotCount);
    GridMap gm = new GridMap(bm, trackWidth);
    Engine engine = new Engine(racers, gm);

    getContentPane().add(engine);
    // revalidate();
    setVisible(true);
    // pack();
  }

  public static void main(String[] args) {
    new Jalse();
  }
}
