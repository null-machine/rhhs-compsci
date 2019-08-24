import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.GridLayout;

/**
* A game master that manages components by updating and displaying them.
* Runs the single shared clock thread used by all components.
* @author Glen Wang
* @version 1.0
* @date June 11, 2019
*/
public class Master extends JFrame {

  private MainMenu menu;
  private Renderer rendererOne;
  private Renderer rendererTwo;
  private Engine engine;
  private Results results;

  private enum Phase { MENU, GAME, RESULTS }
  private Phase phase;

  private void loadSingle(JPanel panel, String title) {
    setTitle(title);
    getContentPane().removeAll();
    getContentPane().add(panel);
    revalidate();
  }

  /**
	* Switches to the main menu.
	*/
  public void loadMenu() {
    loadSingle(menu, "Main Menu");
    phase = Phase.MENU;
  }

  /**
	* Switches to the gameplay.
  * @param racerOne The focus for the left renderer panel.
  * @param racerTwo The focus for the right renderer panel.
	*/
  public void loadRenderer(Racer racerOne, Racer racerTwo) {
    setTitle("Game");
    getContentPane().removeAll();
    rendererOne.setFocus(racerOne);
    rendererTwo.setFocus(racerTwo);
    add(rendererOne);
    add(rendererTwo);
    revalidate();

    phase = Phase.GAME;
  }

  /**
	* Switches to the results screen.
	*/
  public void loadResults() {
    loadSingle(results, "Results");
    phase = Phase.RESULTS;
  }

  /**
	* Constructs and displays the game master.
  * Called seperately from constructor to avoid linkage errors.
  * @param menu The main menu.
  * @param rendererOne Player one's renderer.
  * @param rendererTwo Player two's renderer.
  * @param engine The physics engine.
  * @param results The results screen.
	*/
  public void init(MainMenu menu, Renderer rendererOne, Renderer rendererTwo, Engine engine, Results results) {
    setSize(1280, 720);
    setExtendedState(JFrame.MAXIMIZED_BOTH);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    GridLayout gl = new GridLayout(1, 2);
    getContentPane().setLayout(gl);
    setVisible(true);

    this.menu = menu;
    this.rendererOne = rendererOne;
    this.rendererTwo = rendererTwo;
    this.engine = engine;
    this.results = results;

    loadMenu();

    Thread focusUpdater = new Thread(new FocusUpdater());
    focusUpdater.start();
  }

  private class FocusUpdater implements Runnable {
    private long oldTime = System.currentTimeMillis();
    private long deltaTime;
    private final long targDeltaTime = 10;

    @Override
    public void run() {
      while (this != null) {
        deltaTime = System.currentTimeMillis() - oldTime;
        oldTime = System.currentTimeMillis();
        switch (phase) {
          case MENU:
            menu.update(deltaTime);
            break;
          case GAME:
            rendererOne.update(deltaTime);
            rendererTwo.update(deltaTime);
            engine.update(deltaTime);
            break;
          case RESULTS:
            results.update(deltaTime);
            break;
        }
        try {
          Thread.sleep(targDeltaTime);
        } catch (Exception exc) {
          exc.printStackTrace();
        }
      }
    }
  }
}
