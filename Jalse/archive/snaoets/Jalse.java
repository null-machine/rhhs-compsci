import javax.swing.JFrame;
import java.util.ArrayList;

public class Jalse extends JFrame {

  private double trackLength = 200;
  private double trackWidth = 5;
  private int pivotCount = 12;

  public Jalse() {

    setTitle("pygame window");
    // setResizable(false);
    setSize(1280, 720);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    ParticlePool pp = new ParticlePool(100);
    Racer racer = new Racer(new ArrowKeys(), new Balance());
    ArrayList<Racer> racers = new ArrayList<Racer>();
    racers.add(racer);

    BezierMap bm = new BezierMap(trackLength, pivotCount);
    GridMap gm = new GridMap(bm, trackWidth);
    Engine engine = new Engine(racers, gm);

    getContentPane().add(engine);
    setVisible(true);
    // pack();
  }

  public static void main(String[] args) {
    new Jalse();
  }
}
