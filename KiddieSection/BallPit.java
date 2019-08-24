/**
 * BallPit.java
 * Version 1.0
 * @ Author: Jordan A.
 * @ 5/9/2019
 * The java equivalent of that ball pit at the kiddie section at the mall
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;

class BallPit{
  public static void main(String[] args){
    Scanner input = new Scanner(System.in);

    System.out.println("How many balls are in the ball pit?");
    int ballCount = input.nextInt();

    GameWindow game = new GameWindow(ballCount);
  }
}
