import javax.swing.JFrame;

import java.util.List;

import java.util.ArrayList;

public class Jalse extends JFrame {

  private double trackLength = 100; // short 100, medium 100, long 150, xtra long 200
  private double trackWidth = 5; // easy 6, imposs 3
  private int pivotCount = 6; // irrelevant?
  private int poolSize = 300; // 100 per player is safe

  private SelectorMenu menu;

  public void startMenu(SelectorMenu menu) {
    this.menu = menu;
    getContentPane().add(menu);
    setVisible(true);
  }

  public void startEngine(List<Racer> racers) {
    ParticlePool pp = new ParticlePool(poolSize);
    BezierMap bm = new BezierMap(trackWidth, trackLength, pivotCount);
    GridMap gm = new GridMap(bm, trackWidth);
    Engine engine = new Engine(racers, gm);

    getContentPane().remove(menu);
    menu = null;
    getContentPane().add(engine);
    revalidate();
  }

  public Jalse() {
    setTitle("Hey, you. You're finally awake. You were trying to cross the border, right? Walked right into that Imperial ambush, same as us, and that thief over there.");
    setSize(1280, 720);
    setExtendedState(JFrame.MAXIMIZED_BOTH);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }
}
