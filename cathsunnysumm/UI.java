//AUTHOR: SUNNY

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;



public class UI {
  int riddleAnswersGottenRight=0;
  static String verbose = "";//displaying a small text onto screen to tell player what happened
  private static long lastVerbose = System.currentTimeMillis();//the time interval between two messages
  private static boolean shownTime = true;
  public static long totalTime = 0;
  private static boolean answerNotCorrect = true;
  public static void verbose(String string) {
    verbose = string;
    lastVerbose = System.currentTimeMillis();
  }
  
  public static void render(Screen screen) {
    if(player == null) return;
    for(int i = 0; i < GameLauncher.WIDTH / Tile.SIZE; i++) {
      for(int j = 0; j < GameLauncher.HEIGHT / Tile.SIZE; j++) {
        screen.render(Art.sprites[2][1], i * Tile.SIZE, j * Tile.SIZE);
      }
    }
    for(int i = 0; i < 12; i++) {
      for(int j = 0; j < 13; j++) {
        screen.render(Art.sprites[0][1], (i + 7) * Tile.SIZE, (j + 1) * Tile.SIZE);
      }
    }
    /*SCRAPPED IDEA
     for(int i = 0; i < 5; i++) {
     for(int j = 0; j < 8; j++) {
     screen.render(Art.sprites[14][7], (i + 1) * Tile.SIZE, (j + 1) * Tile.SIZE);
     }
     }*/
    
    for (int i = 0; i < 20; i++){
      screen.render(Art.sprites[3][1], (i) * Tile.SIZE, 0);
    }
    
    
    
    
    
    
    //print verbose messages
    if(System.currentTimeMillis() - lastVerbose < 3000 + verbose.split(" ").length * 250) {//verbose.split(" ").length tells us how many words there are. each word is an extra 250ms to read
      Font.draw(screen, verbose, GameLauncher.WIDTH / 2 - Font.getStringWidth(verbose) / 2, 10);//we will draw the verbose message at the centre of screen
    }
    
  }
  
    /*bring our the battle screen and overlay
     *@param screen the screen object we are printing to
     * 
     * 
     }*/
  public static void battleScreen(Screen screen) {
    
    if (floor == 8){
      if (shownTime){
        long endTime = System.currentTimeMillis();
        totalTime = (endTime - GameLauncher.startTime)/1000;
        
        //System.out.println("you have played for this many seconds: " + (endTime - GameLauncher.startTime));
        shownTime = false;
      }
      
      for(int i = 0; i < 14; i++) {
        for(int j = 0; j < 10; j++) {
          if(i == 0 || i == 13 || j == 0 || j == 9) {
            screen.render(Art.sprites[0][1], (i + 3) * Tile.SIZE, (j + 2) * Tile.SIZE);
          }
          else {
            screen.render(Art.sprites[2][1], (i + 3) * Tile.SIZE, (j + 2) * Tile.SIZE);
          }
        }
      }
      Font.draw(screen, "Go and fight the boss", 6*Tile.SIZE, 4*Tile.SIZE);
      Font.draw(screen, "In the new window!!!!", 6*Tile.SIZE, 5*Tile.SIZE);
      Font.draw(screen, totalTime + "seconds played in the game!", 6*Tile.SIZE, 7*Tile.SIZE);
      
    }
    
    if(hascombat) {
      for(int i = 0; i < 14; i++) {
        for(int j = 0; j < 8; j++) {
          if(i == 0 || i == 13 || j == 0 || j == 7) {
            screen.render(Art.sprites[0][1], (i + 3) * Tile.SIZE, (j + 3) * Tile.SIZE);
            
          }else {
            screen.render(Art.sprites[2][1], (i + 3) * Tile.SIZE, (j + 3) * Tile.SIZE);
          }
        }
      }
      Font.draw(screen, "Fighting " + combatEntity.name, GameLauncher.WIDTH / 2 - Font.getStringWidth("Fighting " + combatEntity.name) / 2, 105);
      screen.render(Art.sprites[9][19], 450, 140);
      combatEntity.render(screen, 150, 140);
      
      screen.render(Art.pk, 200, 130);
      
      
      
      
      if(won) {
        if(System.currentTimeMillis() - lastWin >= 500) {
          won = false;
          hascombat = false;
          player.canmove = true;
        }
      }
      
    }
    
    //check if there is a npc and has a dialog
    //over here catherine u can put if u want to add riddles, print to the side of the screen
    if(hasdialog) {
      for(int i = 0; i < 14; i++) {
        for(int j = 0; j < 10; j++) {
          if(i == 0 || i == 13 || j == 0 || j == 9) {
            screen.render(Art.sprites[0][1], (i + 3) * Tile.SIZE, (j + 2) * Tile.SIZE);
          }
          else {
            screen.render(Art.sprites[2][1], (i + 3) * Tile.SIZE, (j + 2) * Tile.SIZE);
          }
        }
      }
      Font.draw(screen, npc.title, GameLauncher.WIDTH / 2 - Font.getStringWidth(npc.title) / 2, 75);
      for(int i = 0; i < npc.text.length; i++) {
        Font.draw(screen, npc.text[i], 145, 110 + i * 20);
      }
      
      Scanner input = new Scanner(System.in);
      String response = "";
      
      if (npc.type == 4){
        while (answerNotCorrect){
          
          
          System.out.println("///////RIDDLE//////\nWhat 8 letter word can have a letter taken away and it still makes a word.\nTake another letter away and it still makes a word.\nKeep on doing that until you have one letter left.\nWhat is the word? Or, type 'giveup' to skip!");
          response = input.nextLine();
          if (response.equalsIgnoreCase("starting") || response.equalsIgnoreCase("giveup")){
            answerNotCorrect = false;
            System.out.println("The answer was:# Starting");
          }
        }
      } else if (npc.type == 5){
        while (answerNotCorrect){
          
          
          System.out.println("///////RIDDLE//////\nWhat has a head, a tail, is brown, and has no legs? Or, type 'giveup' to skip!");
          response = input.nextLine();
          if (response.equalsIgnoreCase("penny") || response.equalsIgnoreCase("giveup")){
            answerNotCorrect = false;
            System.out.println("The answer was:# Penny");
          }
        }
      } else if (npc.type == 6){
        while (answerNotCorrect){
          
          
          System.out.println("///////RIDDLE//////\nDavid's father has three sons : Snap, Crackle and ?: or type 'giveup' to proceed");
          response = input.nextLine();
          if (response.equalsIgnoreCase("david") || response.equalsIgnoreCase("giveup")){
            answerNotCorrect = false;
            System.out.println("The answer was:# David");
          }
        }
      } else if (npc.type == 7){
        while (answerNotCorrect){
          
          
          System.out.println("///////RIDDLE//////\nWhat room do ghosts avoid?||| or type 'giveup' to proceed");
          response = input.nextLine();
          if (response.equalsIgnoreCase("living room") || response.equalsIgnoreCase("giveup")){
            answerNotCorrect = false;
            System.out.println("The answer was:# living room");
          }
        }
      }else if (npc.type == 8){
        while (answerNotCorrect){
          
          
          System.out.println("///////RIDDLE//////\nWhen does Christmas come before Thanksgiving??||| or type 'giveup' to proceed");
          response = input.nextLine();
          if (response.equalsIgnoreCase("dictionary") || response.equalsIgnoreCase("giveup")){
            answerNotCorrect = false;
            System.out.println("The answer was:# Dictionary");
          }
        }
      }else if (npc.type == 9){
        while (answerNotCorrect){
          
          
          System.out.println("///////RIDDLE//////\nRe-arrange the letters, O O U S W T D N E J R, to spell just one word.\nWhat is it???||| or type 'giveup' to proceed");
          response = input.nextLine();
          if (response.equalsIgnoreCase("just one word") || response.equalsIgnoreCase("giveup")){
            answerNotCorrect = false;
            System.out.println("The answer was:# just one word");
          }
        }
      }else if (npc.type == 10){
        while (answerNotCorrect){
          
          
          System.out.println("///////RIDDLE//////\nMy life can be measured in hours, I serve by being devoured. Thin, I am quick. Fat, I am slow. Wind is my foe. What am I??||| or type 'giveup' to proceed");
          response = input.nextLine();
          if (response.equalsIgnoreCase("candle") || response.equalsIgnoreCase("giveup")){
            answerNotCorrect = false;
            System.out.println("The answer was:# candle");
          }
        }
      }else if (npc.type == 11){
        while (answerNotCorrect){
          
          
          System.out.println("///////RIDDLE//////\nI am not alive, but I grow; I don't have lungs, but I need air; I don't have a mouth, but water kills me. What am I??||| or type 'giveup' to proceed");
          response = input.nextLine();
          if (response.equalsIgnoreCase("fire") || response.equalsIgnoreCase("giveup")){
            answerNotCorrect = false;
            System.out.println("The answer was:# fire");
          }
        }
      }else if (npc.type == 12){
        while (answerNotCorrect){
          
          
          System.out.println("///////RIDDLE//////\nWhat is more useful when it is broken??||| or type 'giveup' to proceed");
          response = input.nextLine();
          if (response.equalsIgnoreCase("egg") || response.equalsIgnoreCase("giveup")){
            answerNotCorrect = false;
            System.out.println("The answer was:# egg");
          }
        }
      }else if (npc.type == 13){
        while (answerNotCorrect){
          
          
          System.out.println("///////RIDDLE//////\nWhat has six faces, But does not wear makeup. It also has twenty-one eyes, But cannot see?||| or type 'giveup' to proceed");
          response = input.nextLine();
          if (response.equalsIgnoreCase("die") || response.equalsIgnoreCase("giveup")){
            answerNotCorrect = false;
            System.out.println("The answer was:# die");
          }
        }
      }else if (npc.type == 14){
        while (answerNotCorrect){
          
          
          System.out.println("///////RIDDLE//////\n They have not flesh, nor feathers, nor scales, nor bone. Yet they have fingers and thumbs of their own. What are they?||| or type 'giveup' to proceed");
          response = input.nextLine();
          if (response.equalsIgnoreCase("glove") || response.equalsIgnoreCase("giveup")){
            answerNotCorrect = false;
            System.out.println("The answer was:# glove");
          }
        }
      }else if (npc.type == 15){
        while (answerNotCorrect){
          
          System.out.println("///////RIDDLE//////\nThis is as light as a feather, yet no man can hold it for long. What am I??||| or type 'giveup' to proceed");
          response = input.nextLine();
          if (response.equalsIgnoreCase("breath") || response.equalsIgnoreCase("giveup")){
            answerNotCorrect = false;
            System.out.println("The answer was:# breath");
          }
        }
      }else if (npc.type == 16){
        while (answerNotCorrect){
          
          
          System.out.println("///////RIDDLE//////\nBefore Mount Everest was discovered, what was the highest mountain on Earth?||| or type 'giveup' to proceed");
          response = input.nextLine();
          if (response.equalsIgnoreCase("mount everest") || response.equalsIgnoreCase("giveup")){
            answerNotCorrect = false;
            System.out.println("The answer was:# mount everest");
          }
        }
      }else if (npc.type == 17){
        while (answerNotCorrect){
          
          
          System.out.println("///////RIDDLE//////\nPoor people have it. Rich people need it. If you eat it you starve. what is it?||| or type 'giveup' to proceed");
          response = input.nextLine();
          if (response.equalsIgnoreCase("nothing") || response.equalsIgnoreCase("giveup")){
            answerNotCorrect = false;
            System.out.println("The answer was:# nothing");
          }
        }
      }else if (npc.type == 18){
        while (answerNotCorrect){
          
          
          System.out.println("///////RIDDLE//////\nHow do you spell COW in thirteen letters?||| or type 'giveup' to proceed");
          response = input.nextLine();
          if (response.equalsIgnoreCase("see o double you") || response.equalsIgnoreCase("giveup")){
            answerNotCorrect = false;
            System.out.println("The answer was:# SEE O DOUBLE YOU");
          }
        }
      }else if (npc.type == 19){
        while (answerNotCorrect){
          
          
          System.out.println("What goes up but never comes down?||| or type 'giveup' to proceed");
          response = input.nextLine();
          if (response.equalsIgnoreCase("age") || response.equalsIgnoreCase("giveup")){
            answerNotCorrect = false;
            System.out.println("The answer was:# age");
          }
        }
      }else if (npc.type == 20){
        while (answerNotCorrect){
          
          
          System.out.println("What runs around the whole yard without moving?||| or type 'giveup' to proceed");
          response = input.nextLine();
          if (response.equalsIgnoreCase("fence") || response.equalsIgnoreCase("giveup")){
            answerNotCorrect = false;
            System.out.println("The answer was:# fence");
          }
        }
      }else if (npc.type == 21){
        while (answerNotCorrect){
          
          
          System.out.println("What goes up when the rain comes down?||| or type 'giveup' to proceed");
          response = input.nextLine();
          if (response.equalsIgnoreCase("umbrella") || response.equalsIgnoreCase("giveup")){
            answerNotCorrect = false;
            System.out.println("The answer was:# umbrella");
          }
        }
      }
      
      
      Font.draw(screen, "-Space-", GameLauncher.WIDTH / 2 - Font.getStringWidth("-Space-") / 2, 360);
    }
  }
  
  static Player player;
  static Entity combatEntity;
  
  /*update player object for ui
     *@param player the player object we are updating
     * 
     }*/
  public static void track(Player player) {
    UI.player = player;
  }
  
  static int floor = 0;
  
  
  /*update ui variables and floor
     *@param floor which floor/room we area in
     *@param input the input handler object we are using to take in user input
     }*/
  public static void tick(int floor, InputHandler input) {
    UI.floor = floor;
    
    if(hascombat && System.currentTimeMillis() - lastHit > hitint) {
      if(won){
        return;
      }
      
      if (turn == 0){
        int ch = (int) (Math.random() * 15);
        boolean c = (ch == 0) ? true : false;
        int dmg = 0;
        if(c) {
          dmg = player.getProperty("stat.attack") * 2 - combatEntity.defense;
        } else {
          dmg = player.getProperty("stat.attack") - combatEntity.defense;
        }
        if(combatEntity.hp - dmg < 0) {
          dmg = combatEntity.hp;
        }
        combatEntity.hp -= dmg;
        hit = 1;
        lastHit += hitint;
        turn = 1;
        
      } else if (turn == 1){
        //case 1:
        int edmg = combatEntity.attack - player.getProperty("stat.defense");
        if(player.getProperty("stat.hp") - edmg < 0) {
          edmg = player.getProperty("stat.hp");
        }
        if(edmg < 0) edmg = 0;
        player.putProperty("stat.hp", player.getProperty("stat.hp") - edmg);
        hit = 0;
        lastHit += hitint;
        turn = 0;
        
        
      }
      if(combatEntity.hp <= 0) {
//    hascombat = false;
        hit = -1;
        turn = 0;
        lastWin = System.currentTimeMillis();
        won = true;
        combatEntity.remove();
      }
    }
    
    
    
    if(hasdialog) {
      
      if(input.confirm.down) {
        hasdialog = false;
        player.canmove = true;
        if(npc.disappear) {
          npc.remove();
        }
      }
    }
    
  }
  
  
  
  
  static short hit = -1;
  static boolean hascombat = false;
  static boolean won = false;
  static long lastHit, lastWin;
  static short hitint = 250, turn = 0;
  
   /*initiate combat 
     *@param player the player object that is fighting
     *@param monster the enemy the player is fighting
     }*/
  public static void combat(Player player, Entity monster) {
    if(Combat.calculateDamage(player, monster) < 0) {
      verbose(monster.name + " is too strong!");
      return;
    }
    hascombat = true;
    turn = 0;
    lastHit = System.currentTimeMillis();
    combatEntity = monster;
    player.canmove = false;
  }
  
  
  
  static boolean hasdialog = false;
  static NPC npc;
  
  
  /*initiate interacting with NPC and player
     *@param npc the npc entity object
     *@param p the player object
     }*/
  public static void npc(NPC npc, Player p) {
    UI.npc = npc;
    UI.player = p;
    hasdialog = true;
    p.canmove = false;
    answerNotCorrect = true;
    
    
  }
  
  static Level level;
  
  static List<Entity> entities;
  
  
}
