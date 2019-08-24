
// graphics
import javax.imageio.*;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Point;
import java.awt.Color;

// gui
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
import java.awt.Dimension;

// listeners
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

// util
import java.util.ArrayList;

/**
 * Editor.java
 * a toolkit for floor plan manipulation 
 * @author Bryan Zhang and Glen Wang
 * @version 1.0
 **/
class Editor extends JPanel implements ActionListener {
    
    
    
    private JTextField search;
    private Renderer renderer; // ref for search function
    private FloorPlan floorPlan;
    
    /**
     * Editor
     * creates and maintains the left panel of the floor plan
     * @param a Renderer for search()
     */
    Editor(FloorPlan floorPlan) {
		this.floorPlan = floorPlan;
        System.out.println("editor initialized!");
        this.renderer = renderer;
        this.setPreferredSize(new Dimension(320, 720));
        
        search = new JTextField();
        search.addActionListener(this);
        search.setPreferredSize(new Dimension(200, 20));
        this.add(search);
        
        // add other components
    }
    
    public void paintComponent(Graphics g) { 
        super.paintComponent(g);
        setDoubleBuffered(true);
        setBackground(new Color(255, 235, 209)); 
        repaint();
    }
    
    /**
     * actionPerformed
     * pans the searched rectangle into the center of the screen
     * (it's the search() function but in disguise)
     * @param an ActionEvent that nobody really understands
     */
    public void actionPerformed(ActionEvent e) {
        int input = Integer.parseInt(search.getText()); // add clamping later for out of bounds
        int x = (int)floorPlan.rects.get(input).getX();
        int y = (int)floorPlan.rects.get(input).getY();
        for(Rectangle rect : floorPlan.rects) {
			rect.translate(320 - x - 35, 360 - y - 35);
		}
    }
}

