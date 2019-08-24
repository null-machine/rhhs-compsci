/**
 * DisplayGrid.java
 */

// Graphics Imports
import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;

class DisplayGrid {

  private JFrame frame;
  private int maxX, maxY, GridToScreenRatio;
  private Organism[][] world;

  DisplayGrid(Organism[][] w) {
    this.world = w;

    maxX = Toolkit.getDefaultToolkit().getScreenSize().width;
    maxY = Toolkit.getDefaultToolkit().getScreenSize().height;

    GridToScreenRatio = maxY / (world.length+1)/2;

    System.out.println("Map size: "+world.length+" by "+world[0].length + "\nScreen size: "+ maxX +"x"+maxY+ " Ratio: " + GridToScreenRatio);

    this.frame = new JFrame("Map of World");

    GridAreaPanel worldPanel = new GridAreaPanel();

    frame.getContentPane().add(BorderLayout.CENTER, worldPanel);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
    frame.setVisible(true);
  }


  public void refresh() {
    frame.repaint();
  }

  class GridAreaPanel extends JPanel {
    public void paintComponent(Graphics g) {
      super.repaint();
      Image plant = Toolkit.getDefaultToolkit().getImage("plant.png"); // find your own
      Image sheep = Toolkit.getDefaultToolkit().getImage("sheep.png");
      Image wolf = Toolkit.getDefaultToolkit().getImage("wolf.png");
      setDoubleBuffered(true);
      g.setColor(Color.BLACK);

      for(int i = 0; i<world[0].length;i=i+1)
      {
        for(int j = 0; j<world.length;j=j+1)
        {
          g.fillRect(j*GridToScreenRatio, i*GridToScreenRatio, GridToScreenRatio, GridToScreenRatio);
          g.setColor(Color.BLACK);
          g.drawRect(j*GridToScreenRatio, i*GridToScreenRatio, GridToScreenRatio, GridToScreenRatio);
          if (world[i][j] instanceof Wolf)
            g.drawImage(wolf,j*GridToScreenRatio,i*GridToScreenRatio,GridToScreenRatio,GridToScreenRatio,this);
          else if (world[i][j] instanceof Sheep)
            g.drawImage(sheep,j*GridToScreenRatio,i*GridToScreenRatio,GridToScreenRatio,GridToScreenRatio,this);
          else if (world[i][j] instanceof Plant)
            g.drawImage(plant,j*GridToScreenRatio,i*GridToScreenRatio,GridToScreenRatio,GridToScreenRatio,this);
          else
            g.setColor(Color.BLACK);
        }
      }
    }
  }//end of GridAreaPanel

} //end of DisplayGrid
