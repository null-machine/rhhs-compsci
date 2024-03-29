//AUTHOR:SUNNY
import java.applet.Applet;

import java.awt.BorderLayout;

public class GameApplet extends Applet {
  
  private static final long serialVersionUID = 1L;
  private GameLauncher game;
  
  public GameApplet() {
    game = new GameLauncher();
    setLayout(new BorderLayout());
    add(game, BorderLayout.CENTER);
  }
  
  public void start() {
    game.start();
  }
  
  public void stop() {
    game.stop();
  }
}
