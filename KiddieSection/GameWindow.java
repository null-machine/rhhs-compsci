/**
 * GameWindow
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class GameWindow extends JFrame{
  public GameWindow(int ballCount){

    super("pygame window");
    // setSize(1280, 720);
    // setResizable(false);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    setLayout(new BorderLayout());
    add(new GamePanel(ballCount), BorderLayout.CENTER);
    pack();

    requestFocusInWindow();
    setVisible(true);

    System.out.println("gw init");
  }
}
