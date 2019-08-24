
// graphics
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Color;

// gui
import javax.swing.JPanel;
import java.awt.Dimension;

// util
import java.util.ArrayList;

/**
 * Renderer.java
 * creates and maintains the center panel of the floor plan
 * @author Bryan Zhang and Glen Wang
 * @version 1.0
 **/
class Renderer extends JPanel {
    
    private FloorPlan floorPlan; 
    
    /**
     * Renderer
     * creates and maintains the center panel of the floor plan
     */
    Renderer(FloorPlan floorPlan) {
		this.floorPlan = floorPlan;
        System.out.println("renderer initialized!");
        this.setPreferredSize(new Dimension(640, 720));
        
        for (int i = -1000; i < 1000; i += 100) {
            for (int j = -1000; j < 1000; j += 100) {
                floorPlan.rects.add(new Rectangle(j, i, 70, 70)); // hard code
            }
        }
    }
    
    public void paintComponent(Graphics g) { 
        super.paintComponent(g);
        setDoubleBuffered(true);
        setBackground(new Color(254, 255, 229)); 
        paintTables(g);
        repaint();
    }
    
    /**
     * paintTables
     * translates all the rects by shiftX and shiftY
     * @param two ints representing x shift and y shift
     */
    private void paintTables(Graphics g) {
        for (int i = 0; i < floorPlan.rects.size(); ++i) { // foreach
            g.drawRect((int)floorPlan.rects.get(i).getX(), (int)floorPlan.rects.get(i).getY(), 70 ,70);
            g.drawString(Integer.toString(i), (int)floorPlan.rects.get(i).getX() + 35, (int)floorPlan.rects.get(i).getY() + 35);
        }
        //System.out.println("tables painted");
    }
}
