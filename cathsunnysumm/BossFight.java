// Author : CATHERINE 

//IMPORTING PROJECTS
import javax.swing.*;
import javax.swing.JButton;
import java.awt.*;
import java.awt.event.*;
import java.awt.Font;
import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Scanner;
import java.io.*;

//AUTHOR: Catherine
//class boss fight- what happens: short game where they have a fight with a "boss" ai, mainly based on chance
public class BossFight extends JFrame implements ActionListener {
  
  //calling in classes
  public GameLauncher game;
  public UI ui;
  
  // JBUTTONS FOR EACH OPTION , show damamge and how much it is blocked by
  JButton swordNormHit = new JButton("NORMAL SWORD HIT (45 DMG)");
  JButton swordRiskyHit = new JButton("(RISKY)CRIT SWORD HIT(100 DMG)");
  JButton swordDefence = new JButton("NORMAL SWORD DEFENCE (BLOCK 35)");
  JButton swordRiskyDefence = new JButton("(RISKY) SWORD DEFENCE (BLOCK 100)");
  
  JButton knifeNormHit = new JButton("NORMAL KNIFE HIT (25 DMG)");
  JButton knifeRiskyHit = new JButton("(RISKY) CRIT KNIFE HIT (90 DMG)");
  JButton knifeDefence = new JButton("NORM. KNIFE DEFENCE (BLOCK 45)");
  JButton knifeRiskyDefence = new JButton("(RISKY)KNIFE DEFENCE (BLOCK 40)");
  
  JButton axeNormHit = new JButton("NORMAL AXE HIT (45 DMG)");
  JButton axeRiskyHit = new JButton("(RISKY) CRIT AXE HIT (80 DMG)");
  JButton axeDefence = new JButton("NORMAL AXE DEFENCE (BLOCK 45)");
  JButton axeRiskyDefence = new JButton("(RISKY) AXE DEFENCE (BLOCK 80)");
  
  JButton lanceNormHit = new JButton("NORMAL LANCE HIT (100 DMG)");
  JButton lanceRiskyHit = new JButton("(RISKY) CRIT LANCE HIT (200 DMG)");
  JButton lanceDefence = new JButton("NORMAL LANCE DEFENCE (BLOCK 10)");
  JButton lanceRiskyDefence = new JButton("(RISKY) LANCE DEFENCE (BLOCK 20)");
  
  // JBUTTONS FOR CYCLING THROUGH WEAPONS
  JButton nextWeapon = new JButton(">");
  JButton lastWeapon = new JButton("<");
  
  // JBUTTONS FOR CHANGING TURNS
  JButton yourTurnButton = new JButton("YOUR TURN");
  JButton bossTurnButton = new JButton("BOSS TURN");
  
  //array for jbuttons
  JButton[][] optionButton = {{swordNormHit, swordRiskyHit, swordDefence, swordRiskyDefence}, {knifeNormHit, knifeRiskyHit, knifeDefence, knifeRiskyDefence}, {axeNormHit, axeRiskyHit, axeDefence, axeRiskyDefence}, {lanceNormHit, lanceRiskyHit, lanceDefence, lanceRiskyDefence}};
  
  // JLABEL FOR WEAPON LABELS
  JLabel swordText = new JLabel("Sword");
  JLabel knifeText = new JLabel("Knife");
  JLabel axeText = new JLabel("Axe");
  JLabel lanceText = new JLabel("Lance");
  
  // BOSSFIGHT INTRO SCREEN TEXT
  JLabel bossFight = new JLabel("BOSSFIGHT");
  JLabel bossFightText1 = new JLabel("To escape this castle, you must defeat the dragon!");
  JLabel bossFightText2 = new JLabel("You have 4 Weapons: Sword, Knife, Axe, Lance. All having unique properties");
  JLabel bossFightText3 = new JLabel("-Sword: good attack but no counter attacks when defense");
  JLabel bossFightText4 = new JLabel("-Knife: good counter attacks but not great attacks");
  JLabel bossFightText5 = new JLabel("-Axe: safe but not great attacks/defenses");
  JLabel bossFightText6 = new JLabel("-Lance: glass cannon- heavy attacks but risky");
  JLabel bossFightText7 = new JLabel("-use '>' & '<' buttons to go through the weapons");
  JLabel bossFightText8 = new JLabel("REMEMBER: to look at the chances! Also if you defend more than the enemy attack, you gain health!");
  JButton bossFightStart = new JButton("Start the Game!");
  
  // JLABEL FOR WEAPON LABELS
  JLabel bossFightEnd = new JLabel("END OF BOSS FIGHT");
  JLabel bossFightResultLose = new JLabel("Sadly you did not win");
  JLabel bossFightResultWin = new JLabel("Congrats you won!! Your score and time is recorded.");
  JButton bossFightRestart = new JButton("Replay the Game");
  JButton bossFightClose = new JButton("Exit the game");
  
  // BOSS FIGHT TEXTS
  JLabel[] bossFightTexts = {bossFightText1, bossFightText2, bossFightText3, bossFightText3, bossFightText4, bossFightText5, bossFightText6, bossFightText7, bossFightText8};
  
  // ALL JPANELS
  JPanel welcomeScreen = new JPanel();
  JPanel endingScreen = new JPanel();
  JPanel swordPane = new JPanel();
  JPanel knifePane = new JPanel();
  JPanel axePane = new JPanel();
  JPanel lancePane = new JPanel();
  JPanel[] panels = {swordPane, knifePane, axePane, lancePane};
  
  JLabel[] weaponText = {swordText, knifeText, axeText, lanceText};
  
  String[] whosTurn = {"Boss", "Player"};
  String[][][] attackScenerios = {{{" attacked ", " blocked ", " boss have hp change of "}, {" critted ", " blocked ", " suffers hp lose of "}}, {{" failed to attack ", " didn't need to block.", " defence change by - "}, {" failed to block "}}};
  String []lines= new String[10];;
  // SOME MISC VARIABLES
  char playerAnswer = '@';
  
  int[][][] dimension = {{{0, 300, 250, 25}, {0, 400, 250, 25}, {350, 300, 275, 25}, {350, 400, 275, 25}}, {{0, 300, 250, 25}, {0, 400, 250, 25}, {350, 300, 275, 25}, {350, 400, 275, 25}}, {{0, 300, 250, 25}, {0, 400, 250, 25}, {350, 300, 275, 25}, {350, 400, 275, 25}}, {{0, 300, 250, 25}, {0, 400, 250, 25}, {350, 300, 275, 25}, {350, 400, 275, 25}}};
  
  int currentPane = 0;
  int attackOrDefence = 0;
  int turn = 1;
  
  int bossHP = 750;
  int bossAttack = 50;
  int enemyAttack = 0;
  int bossSpecial = 10;
  int defence = 10;
  int defenceSpecial = 100;
  
  int playerHP = 750;
  int playerAttack = 0;
  int playerDefence = 10;
  
  int debuffDefence = 0;
  
  //JLABELS
  JLabel player = new JLabel("PLAYER");
  JLabel playerHealthText = new JLabel("Player Health: " + Integer.toString(playerHP));
  
  JLabel chances = new JLabel("CHANCE");
  JLabel normalChances = new JLabel("Normal Attacks/Defence. 80% Norm Sucess, 20% Failure ");
  JLabel riskyChances = new JLabel("Risky Attacks/Defence. 40% Crit 60% Failure");
  JLabel yourRoll = new JLabel("Your Roll:" + " -");
  
  JLabel versus = new JLabel("VS");
  JLabel turnText = new JLabel("TURN: PLAYER");
  JLabel turnWhatHappenedText = new JLabel("[start of the game]");
  
  JLabel boss = new JLabel("BOSS");
  JLabel bossHealthText = new JLabel("Boss Health: " + Integer.toString(bossHP));
  JLabel bossAttackText = new JLabel("Boss Basic Attack: " + Integer.toString(bossAttack));
  JLabel bossCritAttackText = new JLabel("Boss Crit Attack: " + Integer.toString(bossSpecial));
  JLabel bossDefenceText = new JLabel("Boss Basic Defence: " + Integer.toString(defence));
  
  JFrame window = new JFrame("Position");
  
  int[][][] weaponDamage = {{{45, 100, 0, 0}, {25, 90, 10, 40}, {45, 80, 10, 20}, {100, 200, 1, 1}}, {{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}}};
  int[][][] weaponDamageBlocked = {{{0, 0, 35, 100}, {0, 0, 45, 50}, {10, 40, 40, 80}, {0, 0, 10, 20}}, {{-10, -70, -30, -50}, {-25, -40, -10, -20}, {-12, -50, -20, -35}, {-10, -20, -10, -20}}};
  int[][] weaponChances = {{80, 40}, {80, 50}, {90, 60}, {60, 30}};
  
  int scenrio = 0;
  int chance = 0;
  
  int option = 0;
  int debuff = 25;
  
  // BOSSFIGHT METHOD (to be able to call in from other classes)
  public BossFight() {
    
    //SETTING UP WINDOW/FRAME
    System.out.print("Welcome To BossFight!!");
    window.setTitle("Boss Fight");
    window.setSize(640, 480);
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    // SETTING UP WELCOME SCREEN
    welcomeScreen.setLayout(null); //
    bossFight.setFont(new Font("Serif", Font.PLAIN, 30));
    bossFight.setBounds(200, 0, 200, 30);
    welcomeScreen.add(bossFight);
    bossFightStart.setBounds(200, 250, 150, 30);
    bossFightStart.addActionListener(this);
    welcomeScreen.add(bossFightStart);
    
    // SETTING UP ENDING SCREEN
    bossFightEnd.setFont(new Font("Serif", Font.PLAIN, 30));
    bossFightEnd.setBounds(200, 0, 200, 30);
    
    endingScreen.setLayout(null);
    endingScreen.add(bossFight);
    
    bossFightResultLose.setFont(new Font("Serif", Font.PLAIN, 15));
    bossFightResultLose.setBounds(150, 40, 200, 20);
    bossFightResultLose.setVisible(false);
    
    bossFightResultWin.setFont(new Font("Serif", Font.PLAIN, 15));
    bossFightResultWin.setBounds(150, 40, 400, 20);
    bossFightResultWin.setVisible(false);
    
    endingScreen.add(bossFightResultLose);
    endingScreen.add(bossFightResultWin);
    
    bossFightClose.setBounds(150, 150, 150, 30);
    bossFightClose.addActionListener(this);
    
    bossFightRestart.setBounds(300, 150, 150, 30);
    bossFightRestart.addActionListener(this);
    bossFightRestart.setVisible(false);
    
    endingScreen.add(bossFightClose);
    endingScreen.add(bossFightRestart);
    
    // SETTING UP ALL THE BOSSFIGHT TEXTS
    for (int i = 0; i < 9; i++) {
      bossFightTexts[i].setFont(new Font("Serif", Font.PLAIN, 13));
      bossFightTexts[i].setBounds(0, (40 + (20 * i)), 640, 15);
      welcomeScreen.add(bossFightTexts[i]);
    }// end of bossfight text for loop
    
    // SETTING UP WEAPON PANELS
    for (int pane = 0; pane < 4; pane++) {
      panels[pane].setLayout(null); //
      weaponText[pane].setFont(new Font("Serif", Font.PLAIN, 30));
      weaponText[pane].setBounds(260, 315, 100, 100);
      panels[pane].add(weaponText[pane]);
      
      for (int option = 0; option < 4; option++) {    
        panels[pane].add(optionButton[pane][option]); //
        optionButton[pane][option].setBounds(dimension[pane][option][0], dimension[pane][option][1], dimension[pane][option][2], dimension[pane][option][3]);
        optionButton[pane][option].addActionListener(this);
      }// end of option for loop
      
      optionButton[pane][2].setEnabled(false);
      optionButton[pane][3].setEnabled(false);
      panels[pane].setVisible(false);
    }// end of forloop, pane
    
    //SETTING MORE GUI THINGS UP!
    nextWeapon.setBounds(350, 350, 41, 30);
    lastWeapon.setBounds(200, 350, 41, 30);
    
    nextWeapon.addActionListener(this);
    lastWeapon.addActionListener(this);
    
    yourTurnButton.setBounds(205, 200, 165, 15);
    yourTurnButton.addActionListener(this);
    yourTurnButton.setVisible(false);
    
    bossTurnButton.setBounds(205, 200, 165, 15);
    bossTurnButton.addActionListener(this);
    bossTurnButton.setVisible(false);
    
    player.setFont(new Font("Serif", Font.PLAIN, 30));
    player.setBounds(10, 0, 250, 30);
    playerHealthText.setFont(new Font("Serif", Font.PLAIN, 15));
    playerHealthText.setBounds(10, 30, 250, 15);
    
    chances.setFont(new Font("Serif", Font.PLAIN, 30));
    chances.setBounds(10, 50, 250, 30);
    
    normalChances.setFont(new Font("Serif", Font.PLAIN, 15));
    normalChances.setBounds(10, 75, 400, 15);
    
    riskyChances.setFont(new Font("Serif", Font.PLAIN, 15));
    riskyChances.setBounds(10, 90, 400, 15);
    
    yourRoll.setFont(new Font("Serif", Font.PLAIN, 15));
    yourRoll.setBounds(10, 105, 400, 15);
    
    ///////////////////////
    versus.setFont(new Font("Serif", Font.PLAIN, 30));
    versus.setBounds(280, 0, 250, 30);
    
    //////////////////////
    boss.setFont(new Font("Serif", Font.PLAIN, 30));
    boss.setBounds(450, 0, 250, 30);
    
    bossHealthText.setFont(new Font("Serif", Font.PLAIN, 15));
    bossHealthText.setBounds(450, 25, 250, 17);
    
    bossAttackText.setFont(new Font("Serif", Font.PLAIN, 15));
    bossAttackText.setBounds(450, 45, 250, 17);
    
    bossCritAttackText.setFont(new Font("Serif", Font.PLAIN, 15));
    bossCritAttackText.setBounds(450, 65, 250, 17);
    
    bossDefenceText.setFont(new Font("Serif", Font.PLAIN, 15));
    bossDefenceText.setBounds(450, 85, 250, 17);
    //////////////////////////////////////
    
    turnText.setFont(new Font("Serif", Font.PLAIN, 30));
    turnText.setBounds(200, 180, 250, 100);
    turnWhatHappenedText.setFont(new Font("Serif", Font.PLAIN, 13));
    turnWhatHappenedText.setBounds(200, 230, 250, 100);
    
    window.add(welcomeScreen);
    
    window.setVisible(true);
    
  }// end of BossFight();
  
  /*  Sees if the HP is lower than 0 
   *  @author: Catherine
   *  @param playerHP is the current hp of the player
   *  @param bossHP is the current hp of the boss
   *  @return whether or not the game is done/ boss or player HP lower than 0
   */  
  public static boolean seeIfDone(int playerHP, int bossHP) {
    if (playerHP <= 0 || bossHP <= 0) {
      return true;
    }// end of checking if
    return false;
  }
  
  
  public void actionPerformed(ActionEvent event) {
    String command = event.getActionCommand();
    
    //START OF THE CHECKING STATEMENTS
    if (command.equals("Start the Game!")) {
      welcomeScreen.setVisible(false);
      bossFightStart.setVisible(false);
      
      currentPane = 0;
      
      panels[currentPane].setVisible(true);
      window.add(addStats(panels[currentPane]));
      window.add(panels[currentPane]);
    }// end of setting up the start of the game
    if (seeIfDone(playerHP, bossHP) == false) { // if hp is above 0
      if (command.equals(">")) {  // NEXT WEAPON 
        panels[currentPane].setVisible(false);
        
        if (currentPane != 3) {
          currentPane = currentPane + 1;
        } else {
          currentPane = 0;
        }// end of if statements, change current pane
        
        panels[currentPane].setVisible(true);
        window.add(addStats(panels[currentPane]));
        window.setVisible(true);
      } else if (command.equals("<")) {//LAST WEAPON
        panels[currentPane].setVisible(false);
        
        if (currentPane != 0) {
          currentPane = currentPane - 1;
        } else {
          currentPane = 3;
        }// end of if statements, change current pane
        
        panels[currentPane].setVisible(true);
        window.add(addStats(panels[currentPane]));
        window.setVisible(true);
        
      } else {// if they pressed the weapon options
        
        // DIFFERENT OPTIONS - 0- normal, 1- risky
        option = 0;
        
        if (command.indexOf("NORMAL") != -1) {
          chance = chanceRoll(false);
        } else if (command.indexOf("RISKY") != -1) {
          chance = chanceRoll(true);
          option = 1;
        }// end of if statements
        
        // SET OPTIONS, in relation to block
        if (command.indexOf("BLOCK") != -1) {
          attackOrDefence = 2;// defence
          option += 2; // normal block option = 2, risky block = 3
        } else if (command.indexOf("DMG") != -1) {
          attackOrDefence = 1;//attack
        }// end of the if statements
        
        //IF TURN IS PLAYER TURN
        if (turn % 2 == 1) {
          
          //SET PLAYER DEFENCE & ATTACK
          
          playerAttack = weaponDamage[chance][currentPane][option];
          //CALCULATING BOSS HP
          int HPloss = playerAttack - defence;
          bossHP = bossHP + defence - Math.abs(HPloss);
          
          //SET STATUS TEXT
          turnWhatHappenedText.setBounds(25, 230, 600, 100);
          
          //IF IN ATTACK
          if (attackOrDefence == 1) {
            debuffDefence = weaponDamageBlocked[chance][currentPane][option];//lowering defence
            //IF UNSUCESSFUL IN ATTACK & WAS CRIT
            if (chance == 1 && option == 1) {
              option = 0;
              turnWhatHappenedText.setText("You" + attackScenerios[chance][option][0] + playerAttack + " DMG . The Boss" + attackScenerios[chance][option][1] + ". You" + attackScenerios[chance][option][2] + debuffDefence);
            } else {//IF SUCESSFUL.
              turnWhatHappenedText.setText("You" + attackScenerios[chance][option][0] + playerAttack + " DMG . The Boss" + attackScenerios[chance][option][1] + defence + ". The Boss" + attackScenerios[chance][option][2] + HPloss);
            }// end of if statements
            
          } else if (attackOrDefence == 2){ 
            //if unsucessful defence
            playerDefence = weaponDamageBlocked[chance][currentPane][option];
            playerDefence-=Math.abs(debuffDefence);
            
            if (chance == 1) {
              option = 1;
              turnWhatHappenedText.setText("You" + attackScenerios[chance][option][0] + ". Your defence for the next turn is " + playerDefence);
            } else {
              // end of sucessful defence
              turnWhatHappenedText.setText("You sucessfully defended. your defence for the next turn is " + playerDefence + ". You also can counter the boss, gain " + weaponDamage[0][currentPane][option] + " DMG");
              playerAttack = playerAttack + weaponDamage[0][currentPane][option];
              bossHP -= weaponDamage[0][currentPane][option];
            }// end of defence registering
            
            window.add(addStats(panels[currentPane]));// adding stats pane to windows
          }// end of if statement- register attack/defence
          
          if (command.indexOf("BLOCK") != -1) {
            bossTurnButton.setVisible(true);
            turn++;
          }// end of if statement for next turn
        } else {// start of boss turn
          if (command.indexOf("BOSS") != -1) {
            bossTurnButton.setVisible(false);
            yourTurnButton.setVisible(true);
            
            enemyAttack = enemyAttack();
            playerHP = playerHP + (playerDefence - enemyAttack);
            turnWhatHappenedText.setText("Boss " + attackScenerios[0][0][0] + enemyAttack + "DMG. You" + attackScenerios[0][0][1] + (playerDefence) + ". You" + attackScenerios[0][0][2] + (playerDefence - enemyAttack));
          }// end of if statement
          
          window.add(addStats(panels[currentPane]));
          
          //start of the player turn
          if (command.equals("YOUR TURN")) {
            turn++;
            yourTurnButton.setVisible(false);
          }// end of buttong press if statement
          
          debuffDefence = 0;
          
        }// end of turn if statements
        
        window.add(addStats(panels[currentPane]));
        
        for (int pane = 0; pane < 4; pane++) {
          for (int option = 0; option < 4; option++) {
            if (command.indexOf("BLOCK") == -1 && command.indexOf("BOSS") == -1) {
              if ((option < 2 && attackOrDefence == 1) || (option > 1 && attackOrDefence == 2)) {
                optionButton[pane][option].setEnabled(false);
              } else {
                optionButton[pane][option].setEnabled(true);
              }// end of enabling if statements
            } else {
              optionButton[pane][option].setEnabled(false);
            }// end of enabling if statements
          }// end of forloop
        }// end of forloop
      }// end of for loop 
    } else {// start of end game screen (lower than 0 hp)
      panels[currentPane].setVisible(false);
      window.add(endingScreen);
      
      char sucess = 'N';
      if (playerHP <= 0) {// boss won
        bossFightResultLose.setVisible(true);
        bossFightRestart.setVisible(true);
      } else {// boss defeated
        bossFightResultWin.setVisible(true);
        sucess = 'Y';
      }// set up the win/loss screen
      
      
      if (command.equals("Replay the Game")) { // end of replaying the game
        window.add(welcomeScreen);
        playerHP = 750;
        bossHP = 750;
        
        //SET UP WELCOM SCREEN
        panels[currentPane].setVisible(false);
        bossFightStart.setVisible(true);
        endingScreen.setVisible(false);
        welcomeScreen.setVisible(true);
        bossFightResultLose.setVisible(false);
        bossFightResultWin.setVisible(false);
        
      } else if (command.equals("Exit the game")) {// if exiting the game
        
        Scanner input = new Scanner(System.in);
        
        System.out.println("\nDo you want to save your score?"); 
        
        do {
          System.out.print("Y/N:");
          playerAnswer = input.next().charAt(0);
        } while (playerAnswer != 'N' && playerAnswer != 'n' && playerAnswer != 'Y' && playerAnswer != 'y');// end of error checking
        
        File myFile = new File("pastPlayers.txt");
        
        String print = game.playerName + "|" + ui.totalTime + "ms|" + sucess + "|";
        if (playerAnswer == 'Y' || playerAnswer == 'y') {// if they want to print 
          
          try {
            Scanner readFile = new Scanner(myFile);
            for(int i=0; i<10; i++){
              
              lines[i]=readFile.nextLine();
            }// end of reading file
            
            PrintWriter printOut = new PrintWriter(myFile);
            printOut.println(print);
            for(int i=0; i<9; i++){
              printOut.println(lines[i]);
            }// end of the printing on the text file
            
            readFile.close();
            printOut.close();
          } catch (IOException e) {
          }// end of trying tot read
          
        }// end of the reading/printing files
        
        window.dispose();
      }// end of endscreen button functions
      
    }// end of game/ endscreen
  }// end of the action listener
  
  /* Rolls whether or not the move is sucessful
   * @param risky determines if the player action is risky or not
   * return 0= sucess, 1= unsucessful
   */
  public int chanceRoll(boolean risky) {
    
    // call in the diff chances for each weapon
    int riskyRoll = weaponChances[currentPane][1];
    int safeRoll = weaponChances[currentPane][0];
    
    int roll = (int) (Math.random() * 100) + 1;//random no. from 1-100
    if (risky == true) {
      if (roll <= riskyRoll) {
        yourRoll.setText("Your roll: Sucess");
        return 0;
      }// end of sucessful roll- risky
      yourRoll.setText("Your roll: Fail");
      return 1;
    } else {
      if (roll <= safeRoll) {
        yourRoll.setText("Your roll: Sucess");
        return 0;
      }// end of sucessful roll-safe
      yourRoll.setText("Your roll: Fail");
      return 1;
    }// end of risky if statements
  }// end of chance roll method
  
  /*  Updates and adds stats to each weapon 
   *  @author: Catherine
   * @param: panel- the current panel on what to add
   *  @return the jpanel of stats
   */ 
  public JPanel addStats(JPanel panel) {
    bossHealthText.setText("Boss Health:" + Integer.toString(bossHP));
    playerHealthText.setText("Your Health:" + Integer.toString(playerHP));
    
    normalChances.setText("Normal Attacks/Defence. " + weaponChances[currentPane][0] + "% Norm Sucess," + (100 - weaponChances[currentPane][0]) + "% Failure ");
    riskyChances.setText("Risky Attacks/Defence. " + weaponChances[currentPane][1] + "% Crit Sucess," + (100 - weaponChances[currentPane][1]) + "%Failure");
    
    turnText.setText("TURN: " + whosTurn[(turn % 2)]);
    
    panel.add(yourTurnButton);
    panel.add(turnText);
    panel.add(bossTurnButton);
    panel.add(turnWhatHappenedText);
    
    panel.add(chances);
    panel.add(nextWeapon);
    panel.add(lastWeapon);
    
    panel.add(versus);
    panel.add(player);
    panel.add(playerHealthText);
    panel.add(chances);
    panel.add(normalChances);
    panel.add(riskyChances);
    panel.add(yourRoll);
    
    panel.add(boss);
    panel.add(bossHealthText);
    panel.add(bossAttackText);
    panel.add(bossCritAttackText);
    panel.add(bossDefenceText);
    return panel;
  }
  
  /*  Generates enemy attack
   *  @author: Catherine
   *  @return the damage the enemy attacks by
   */ 
  public static int enemyAttack() {
    
    //randomly generates boss attack
    int random = ((int) (Math.random() * 6) + 1);
    if (random == 5) {
      return 100;
    }// end of if statements
    //returns the attack
    return 50;
    
  }//end of enemyAttack();
}//end of boss fight class
