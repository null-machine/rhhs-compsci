/**
 * BouncingBall.java
 * Version 1.0
 * @ Author: Jordan A.
 * @ 05/09/2019
 * This is a class for a bouncing ball
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*; 

class BouncingBall{
  
  double xCoord;
  double yCoord;
  double xSpeed;
  double ySpeed;
  int red;
  int green;
  int blue;
  Color color = new Color(red, green, blue);
  
  BouncingBall(){
    this.xCoord = (int)(Math.random()*800)+1;
    this.yCoord = (int)(Math.random()*600)+1;
    this.xSpeed = (int)(Math.random()*10);
    this.ySpeed = (int)(Math.random()*10);
  }
  
  void update(double elapsedTime){
    if (this.xCoord < 0 || this.xCoord > 775){
      this.xSpeed = 0 - xSpeed;
    } if (this.yCoord < 0 || this.yCoord > 575){
      this.ySpeed = 0 - ySpeed;
    }
    this.xCoord = xCoord + xSpeed * elapsedTime * 100;
    this.yCoord = yCoord + ySpeed * elapsedTime * 100;
  }
  
  void draw(Graphics g){
    this.red = (int)(Math.random()*256);
    this.green = (int)(Math.random()*256);
    this.blue = (int)(Math.random()*256);
    
    g.setColor(Color.BLACK);
    g.fillOval((int)xCoord, (int)yCoord, 25, 25);
  }
  
}