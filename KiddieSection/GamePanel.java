/**
 * GamePanel.java
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

class GamePanel extends JPanel {

  BouncingBall[] balls;
  BouncingBall wheatley;
  FrameRate frameRate;
  Clock clock;

  //constructor
  public GamePanel(int ballCount) {
    setPreferredSize(new Dimension(640,720));
    frameRate = new FrameRate();
    wheatley = new BouncingBall();

    balls = new BouncingBall[ballCount];
    for (int i = 0; i < balls.length; i++) {
      balls[i] = new BouncingBall();
    }
    clock = new Clock();
  }


  public void paintComponent(Graphics g) {
    super.paintComponent(g); //required to ensure the panel is correctly redrawn
    setDoubleBuffered(true);
    // setBackground(new Color(254, 255, 229));

    for (int i = 0; i < balls.length; i++) {
      balls[i].update(clock.getElapsedTime());
      balls[i].draw(g);
    }

    clock.update();
    frameRate.update();

    wheatley.update(clock.getElapsedTime());

    wheatley.draw(g);
    frameRate.draw(g, 100, 100);

    //request a repaint
    repaint();
  }
}
