
public class Launcher {

  public static void main(String[] args) {
    Jalse jalse = new Jalse();
    SelectorMenu menu = new SelectorMenu(jalse);
    jalse.startMenu(menu);
  }
}
