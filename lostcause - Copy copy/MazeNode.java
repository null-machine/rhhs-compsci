import java.util.ArrayList;
import java.util.Random;

public class MazeNode {

  public MazeNode north, east, south, west; // make getters and setters
  public String openLinks = "";
  private ArrayList<MazeNode> links; // kruskal's

  private Random rnd = new Random();

  public void addLink(String link) {
    openLinks += link;
  }

  public void remLink(String link) {
    openLinks = openLinks.replace(link, "");
  }

  

  public void linkPath() {

    if (openLinks.length() < 1) {
      return;
    }

    int choiceIndex = rnd.nextInt(openLinks.length());
    String choice = "" + openLinks.charAt(choiceIndex);
    remLink(choice);

    switch (choice) {
      case "N":
        // north is now linked to this node, so its south link isnt open anymore
        north.remLink("S");
        north.linkPath();
        break;
      case "E":
        east.remLink("W");
        east.linkPath();
        break;
      case "S":
        south.remLink("N");
        south.linkPath();
        break;
      case "W":
        west.remLink("E");
        west.linkPath();
        break;
    }
  }
}
