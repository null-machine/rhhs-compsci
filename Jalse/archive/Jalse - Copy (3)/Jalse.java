
/**
* Starts the game by initializing all components.
* @author Glen Wang
*/
public class Jalse {

  // E:\jdk-12.0.1\bin\javac.exe *.java & E:\jdk-12.0.1\bin\java.exe Jalse.java


  public static void main(String[] args) {
    GameState gs = new GameState();
    Master engine = new Master();
    MainMenu menu = new MainMenu(gs, engine);
    Renderer rendererOne = new Renderer(gs);
    Renderer rendererTwo = new Renderer(gs);
    Engine pe = new Engine(gs, engine);
    Results results = new Results(gs, engine);
    engine.init(menu, rendererOne, rendererTwo, pe, results);
  }
}
