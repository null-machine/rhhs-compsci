import javax.swing.JFrame;

import java.util.List;

import java.util.ArrayList;

public class Jalse extends JFrame {
  // old town road

  private double trackLength = 200;
  private double trackWidth = 5;
  private int pivotCount = 12;
  private int poolSize = 300;

  public void startMenu(SelectorMenu menu) {
    getContentPane().add(menu);
    setVisible(true);
  }

  public void startEngine(List<Racer> racers) {
    ParticlePool pp = new ParticlePool(poolSize);
    BezierMap bm = new BezierMap(trackLength, pivotCount);
    GridMap gm = new GridMap(bm, trackWidth);
    Engine engine = new Engine(racers, gm);

    getContentPane().add(engine);
    revalidate();
    // pack();
  }

  public Jalse() {

    setTitle("Hey, you. You're finally awake. You were trying to cross the border, right? Walked right into that Imperial ambush, same as us, and that thief over there.");
    setSize(1280, 720);
    setExtendedState(JFrame.MAXIMIZED_BOTH);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // Racer racer = new Racer(new Arrows(new Input()), new Control());
    // ArrayList<Racer> racers = new ArrayList<Racer>();
    // racers.add(racer);
    // startEngine(racers);
  }
}
