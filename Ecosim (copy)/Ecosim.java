/**
 * Ecosim.java
 * 
 * @version 1.0
 * @author Sarita Sou
 * @since Dec 9, 2018 /// check the date format
 * 
 * Takes input to initialize, update and display the ecosystem.
 */

import java.util.Scanner;

class Ecosim {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    
    //change to Input later
    System.out.println("~ Plants ~");
    System.out.println("What is the initial amount of plants? Recommended value: #");
    int plantAmount = 200;
    System.out.println("What is the nutritional value of the plants? Recommended value: #");
    ///plantHealth = input.nextInt();
    int plantHealth = 10;
    System.out.println("How many plants spawn each turn? Recommended value: #");
    int plantSpawnRate = 20;
    
    System.out.println("\n~ Sheep ~");
    System.out.println("What is the initial amount of sheep? Recommended value: #");
    int sheepAmount = 1;
    System.out.println("What is the starting health of the sheep? Recommended value: #");
    int sheepHealth = 100;
    
    System.out.println("\n~ Wolves ~");
    System.out.println("What is the initial amount of wolves? Recommended value: #");
    int wolfAmount = 200;
    System.out.println("What is the starting health of the wolves? Recommended value: #");
    int wolfHealth = 500;
    
    /// renamed to avoid confusion with the og system in System.out.println()
    Ecosystem ecosystem = new Ecosystem(plantAmount,plantHealth,plantSpawnRate,sheepAmount,sheepHealth,wolfAmount,wolfHealth);
    
    //Set up Grid Panel
    DisplayGrid grid = new DisplayGrid(ecosystem.map);
    
    
    do { /// changed to do-while to get rid of an extra refresh
      
      //Display the grid on a Panel
      grid.refresh();
      
      /// update map
      ecosystem.readyBeings();
      ecosystem.updateBeings();
      ecosystem.spawnPlants();
      
      //Small delay
      /// changed the delay
      try{ Thread.sleep(200); }catch(Exception e) {};
      
    } while (ecosystem.getExtinct() == null);
    
    System.out.println(ecosystem.getExtinct()+" goes extinct. The world ends.");
  }
}
