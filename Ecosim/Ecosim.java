/**
 * Ecosim.java
 */

import java.util.Scanner;

class Ecosim {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);

    int sheepAmount = 1;
    int sheepHealth = 100;
    int wolfAmount = 200;
    int wolfHealth = 500;
    int plantAmount = 200;
    int plantHealth = 10;
    int plantSpawnRate = 20;

    Ecosystem ecosystem = new Ecosystem(plantAmount,plantHealth,plantSpawnRate,sheepAmount,sheepHealth,wolfAmount,wolfHealth);

    DisplayGrid grid = new DisplayGrid(ecosystem.map);

    grid.refresh();
    while (ecosystem.getExtinct() == null) {
      ecosystem.readyOrganisms();
      ecosystem.checkOrganisms();
      ecosystem.spawnPlants();
      try{ Thread.sleep(200); }catch(Exception e) {};
    }
  }
}
