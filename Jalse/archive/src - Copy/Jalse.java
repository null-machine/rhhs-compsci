
/**
* Starts the game by initializing all components.
* @author Glen Wang
*/
public class Jalse {

  // E:\jdk-12.0.1\bin\javac.exe *.java & E:\jdk-12.0.1\bin\java.exe Jalse.java

  /**
  * Initializes all components and constructs the game master.
  * @param args Command-line arguments are irrelevant for this.
  */
  public static void main(String[] args) {
    GameState gameState = new GameState();
    Master master = new Master();
    MainMenu menu = new MainMenu(gameState, master);
    Renderer rendererOne = new Renderer(gameState);
    Renderer rendererTwo = new Renderer(gameState);
    Engine engine = new Engine(gameState, master);
    Results results = new Results(gameState, master);
    master.init(menu, rendererOne, rendererTwo, engine, results);
  }
}
