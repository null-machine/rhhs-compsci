
/**
* Starts the game by initializing all components.
* @author Glen Wang
*/
public class Jalse {

  // E:\jdk-12.0.1\bin\javac.exe *.java & E:\jdk-12.0.1\bin\java.exe Jalse.java

  /**
  * Constructs the game master.
  * @param argameState Command-line arguments are irrelevant for this program.
  */
  public static void main(String[] argameState) {
    GameState gameState = new GameState();
    Master engine = new Master();
    MainMenu menu = new MainMenu(gameState, engine);
    Renderer rendererOne = new Renderer(gameState);
    Renderer rendererTwo = new Renderer(gameState);
    Engine pe = new Engine(gameState, engine);
    Results results = new Results(gameState, engine);
    engine.init(menu, rendererOne, rendererTwo, pe, results);
  }
}
