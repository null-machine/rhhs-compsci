/**
 * NatureBattelRoyal.java
 * Version 1.0
 * Author: @Jordan A.
 * 04/17 -
 * The sequel to BattelRoyal!
 */

import java.util.Scanner;
import java.awt.Image;
import javax.imageio.*;
import javax.swing.*;
import java.awt.*;

class NatureBattelRoyal{

  static int freeSpaces;  // total amount of null spaces available on the board
  static int totalSpaces; // total area of the board (done by size * size)
  static int sheepCount;  // initial sheep count
  static int wolfCount;   // initial wolf count
  static int grassCount;  // initial grass count
  static int sheepHP;     // initial sheep HP
  static int wolfHP;      // initial wolf HP
  static int grassRate;   // initial grass rate. higher numbers makes spawning harder

  public static void main(String[] args){

    Scanner input = new Scanner(System.in);

    Image sheep = Toolkit.getDefaultToolkit().getImage("sheep.png");
    Image wolf = Toolkit.getDefaultToolkit().getImage("wolf.png");

    // take inputs for map
    System.out.println("What size do you want the map to be? ");
    int mapSize = input.nextInt();

    // set amounts for total spaces and free spaces
    totalSpaces = mapSize * mapSize; // totalSpaces will be used to reset
    // freeSpaces later on
    freeSpaces = totalSpaces; // initially set as the maximum spaces available

    // take inputs for entities
    int tempAmount; // temporary amount to determine whether or not
    // the value has went past the maximum entities available

    // sheep amount input
    System.out.print("How many sheep do you want? ");
    System.out.println("(0 - " + freeSpaces + ")");
    tempAmount = input.nextInt();
    entityMaxCheck(input, tempAmount, sheepCount);

    //sheep HP input
    System.out.print("What do you want the initial sheep HP to be? ");
    System.out.println("(10 - 50)");
    tempAmount = input.nextInt();
    hpCap(input, tempAmount, sheepHP);

    // wolf amount input
    System.out.print("How many wolves do you want? ");
    System.out.println("(0 - " + freeSpaces + ")");
    tempAmount = input.nextInt();
    entityMaxCheck(input, tempAmount, wolfCount);

    //wolf HP input
    System.out.print("What do you want the initial wolf HP to be? ");
    System.out.println("(10 - 50)");
    tempAmount = input.nextInt();
    hpCap(input, tempAmount, wolfHP);

    hpCap(input, input.nextInt(), wolfHP);

    // grass amount input
    System.out.print("How much grass do you want? ");
    System.out.println("(0 - " + freeSpaces + ")");
    tempAmount = input.nextInt();
    entityMaxCheck(input, tempAmount, grassCount);

    //grass rate input
    System.out.println("What do you want the chances of grass growing to be? ");
    System.out.println("(1 / x)");
    System.out.println("Note: Higher numbers make grass growth harder. ");
    tempAmount = input.nextInt();
    grassRateCheck(input, tempAmount);

    input.close();

    // reset freeSpaces amount
    freeSpaces = totalSpaces;

    Entity[][] map = new Entity[mapSize][mapSize];
    Ecosystem ecosystem = new Ecosystem(map, mapSize, sheepCount, sheepHP, wolfCount, wolfHP, grassCount, grassRate);

    DisplayGrid grid = new DisplayGrid(map);

    // spawn the initial sheep, wolves, and grass tiles
    sheepCheck(map, mapSize);
    wolfCheck(map, mapSize);
    initialGrass(map, mapSize);
    dirtFill(map, mapSize);

    for (int i = 0; i < mapSize; i++){
      for (int j = 0; j < mapSize; j++){
        if (map[i][j] instanceof Dirt){
          System.out.print("D ");
        } else if (map[i][j] instanceof Wolf){
          System.out.print("W ");
        } else if (map[i][j] instanceof Sheep){
          System.out.print("S ");
        } else if (map[i][j] instanceof Grass){
          System.out.print("G ");
        } else {
          System.out.print("? ");
        }

      }
      System.out.print("\n");
    }



    // put the display stuff here

  } // end of maine



  /**
   * hpCap() checks whether or not the initial hp value is within the set range
   * @param input is used for rewriting the amount variable
   * @param amount used for determining whether or not the input from the user is valid
   * @param finalEntity used to determine which entity HP value is being edited
   */

  public static void hpCap(Scanner input, int amount, int finalEntity){
    if (amount > 50 || amount < 10){
      System.out.println("Invalid value. Please try again. ");
      amount = input.nextInt();
      hpCap(input, amount, finalEntity);
    } else {
      finalEntity = amount;
    }
  } // end of hpCap

  /**
   * grassRateCheck() checks whether or not the set grass rate is valid
   * @param input is used for rewriting the rate variable
   * @param rate used for determining whether or not the input from the user is valid
   */

  public static void grassRateCheck(Scanner input, int rate){
    if (rate <= 0){
      System.out.println("Invalid value. Please try again. ");
      rate = input.nextInt();
      grassRateCheck(input, rate);
    } else {
      grassRate = rate;
    }
  } // end of grassRateCheck

  /**
   * entityMaxCheck() determines whether or not the input for initial spawning
   * of entities goes past the maximum entity tiles for the map
   * @param input is used for rewriting the amount variable
   * @param amount used for determining whether or not the input from the user is valid
   * @param finalEntity used to determine which entity amount value is being edited
   */

  public static void entityMaxCheck(Scanner input, int amount, int finalEntity){
    if (amount > freeSpaces || amount < 0){
      System.out.println("Invalid value. Please try again. ");
      amount = input.nextInt();
      entityMaxCheck(input, amount, finalEntity);
    } else {
      finalEntity = amount;
      freeSpaces -= amount;
    }
  } // end of entityMaxCheck

  /**
   * sheepCheck() determines where the sheep will initially spawn
   * @param map used as the main Entity array
   * @param size used as the maximum size of the array
   */

  public static void sheepCheck(Entity[][] map, int size){
    int entitiesLeft = sheepCount;
    while (entitiesLeft != 0){
      int randY = (int)(Math.random() * size);
      int randX = (int)(Math.random() * size);
      if (map[randY][randX] == null){
        map[randY][randX] = new Sheep(sheepHP, randY, randX);
        entitiesLeft--;
        freeSpaces--;
      }
    }
  } // end of sheepCheck

  /**
   * wolfCheck() determines where the wolf / wolves will initially spawn
   * @param map used as the main Entity array
   * @param size used as the maximum size of the array
   */

  public static void wolfCheck(Entity[][] map, int size){
    int entitiesLeft = wolfCount;
    while (entitiesLeft != 0){
      int randY = (int)(Math.random() * size);
      int randX = (int)(Math.random() * size);
      if (map[randY][randX] == null){
        map[randY][randX] = new Wolf(wolfHP, randY, randX);
        entitiesLeft--;
        freeSpaces--;
      }
    }
  } // end of wolfCheck

  /**
   * initialGrass() spawns the grass for the first time
   * @param map used as the main Entity array
   * @param size used as the maximum size of the array
   */

  public static void initialGrass(Entity[][] map, int size){
    int entitiesLeft = grassCount;
    while (entitiesLeft != 0){
      int randY = (int)(Math.random() * size);
      int randX = (int)(Math.random() * size);
      if (map[randY][randX] == null){
        map[randY][randX] = new Grass(map, 1, randY, randX);
        entitiesLeft--;
        freeSpaces--;
      }
    }
  }

  /**
   * dirtFill() fills the remaining null spaces with Dirt tiles
   * @param map used as the main Entity array
   * @param size used as the maximum size of the array
   */

  public static void dirtFill(Entity[][] map, int size){
    for (int i = 0; i < size; i++){
      for (int j = 0; j < size; j++){
        if (map[i][j] == null){
          map[i][j] = new Dirt();
        }
      }
    }
  }



} // end of NatureBattelRoyal
