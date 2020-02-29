/**
 * GamePanel.java
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*; 

class GamePanel extends JPanel {
  
  BouncingBall[] ball;
  BouncingBall wheatley;
  FrameRate frameRate;
  Clock clock;
  
  //constructor
  public GamePanel() { 
    setPreferredSize(new Dimension(1024,768));
    frameRate = new FrameRate();
//    
//    ball = new BouncingBall[ballCount];
//    wheatley = new BouncingBall();
//    for (int i = 0; i < ballCount; i++){
//      ball[i] = new BouncingBall();
//    }
//    
    clock = new Clock();
  }
  
  
  public void paintComponent(Graphics g, int ballCount) { 
    super.paintComponent(g); //required to ensure the panel is correctly redrawn
    
    repaint();
    
    //update the content
    clock.update();
    frameRate.update();
    for (int i = 0; i < ballCount; i++){
      ball[i].update(clock.getElapsedTime());//you can 'pause' the game by forcing elapsed time to zero
    }
    
    wheatley.update(clock.getElapsedTime());
    
    //draw the screen
    for (int i = 0; i < ballCount; i++){
      ball[i].draw(g);
    }
    
    wheatley.draw(g);
    frameRate.draw(g,100,100);
    
    //request a repaint
    repaint();
  }
  
  
}