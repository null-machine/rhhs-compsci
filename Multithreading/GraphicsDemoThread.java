//This Sample code shows:
//inner classes to demonstrate action listener
//inner class graphics panel
//jbutton usage


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class GraphicsDemoThread {

    JFrame frame;
    MyPanel panel;
    JButton buttonA;

    private int y_change = 5;
    private int y = 150;
    private int y2_change = 5;
    private int y2 = 150;

    public static void main(String[] args) {
        new GraphicsDemoThread().go();
    }

    private void go() {
        frame = new JFrame("GraphicsDemo");
        panel = new MyPanel();

        JButton buttonA = new JButton("MOVE IT!");

        buttonA.addActionListener(new ButtonAListener());

        frame.getContentPane().add(BorderLayout.CENTER, panel);
        frame.getContentPane().add(BorderLayout.SOUTH, buttonA);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setSize(500, 500);

        animate();
    }

       class MyPanel extends JPanel {
        public void paintComponent(Graphics g) {
          g.setColor(Color.BLACK);
          g.fillRect(250, y, 10, 10);
          g.setColor(Color.RED);
          g.fillRect(150, y2, 10, 10);
        }
    }


    class ButtonAListener implements ActionListener {
      public void actionPerformed(ActionEvent event) {
        y_change=y_change*-1;
        y=y+y_change;
        frame.repaint();
      }

    }
    private void animate() {
      Thread ai = new Thread(new AnimationThread());
      ai.start();
        while(true){
          if (y>100 && y<400)
                y= y + y_change;

            try{
                Thread.sleep(15);
            } catch (Exception exc){}
            frame.repaint();
        }
    }

    class AnimationThread implements Runnable {
      public void run() {


        while(true){
          if (y2<100)
            y2_change = 5;
          if(y2>400)
            y2_change = -5;

            y2= y2 + y2_change;

          try{
              Thread.sleep(15);
            } catch (Exception exc){}
            frame.repaint();
        }
      }
    }
}
