/**
 * GameWindow
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*; 

class GameWindow extends JFrame{
  public GameWindow(){
    setTitle("pygame window");
    setResizable(true);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    getContentPane().add(new GamePanel());
    pack();
    setVisible(true);
  }
}