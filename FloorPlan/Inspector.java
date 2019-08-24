// graphics
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Color;

// gui
import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.awt.Dimension;

// util
import java.util.ArrayList;

/**
 * Inspector.java
 * displays info of selected tables
 * @author Bryan Zhang and Glen Wang
 * @version 1.012/02/19
 */
class Inspector extends JPanel {
        
        private JTextArea info;
        private Renderer renderer;
        private void setRenderer(Renderer renderer) { this.renderer = renderer; }
        
        private FloorPlan floorPlan;
        
        Inspector(FloorPlan floorPlan) {
			this.floorPlan = floorPlan;
            System.out.println("inspector initialized!");
            this.setPreferredSize(new Dimension(320, 720));
            
            info = new JTextArea("infooooooo");
            info.setEditable(false);
            info.setPreferredSize(new Dimension(200, 600));
            this.add(info);
        }
        
        public void paintComponent(Graphics g) { 
            super.paintComponent(g);
            setDoubleBuffered(true);
            setBackground(new Color(255, 235, 209)); 
            repaint();
        }
        
        private void updateInfo() { // todo: update from targets setter 
			String displayInfo = "";
			ArrayList<Rectangle> targets = floorPlan.targets;
			for(Rectangle target : targets) {
				displayInfo += target.x + "\t" + target.y + "\n";
			}
			info.setText(displayInfo);
			//System.out.println("info updated: " + displayInfo);
		}
        // boo
    }
