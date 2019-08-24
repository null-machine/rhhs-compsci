/* FloorPlan.java
 * Generates and displays a floor plan from tables
 * @Author Glen Wang and Bryan Zhang
 * 12/02/19
 */

// graphics
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Color;

// gui
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;

// util
import java.util.ArrayList;

class FloorPlan extends JFrame {
    
    public ArrayList<Table> tables;
    public ArrayList<Rectangle> rects;
    public ArrayList<Rectangle> targets;
    
    private Editor editor;
    private Renderer renderer;
    private Inspector inspector;
    
    FloorPlan() {
        
        // create jframe
        super("Floor Plan");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1280, 720);
        this.setResizable(false);
        
        // launch updater
        Thread updater = new Thread(new Runnable() { public void run() { update(); } });
        updater.start();
    }
    
    private void update() {
		while(true) {
			try{ Thread.sleep(20); } catch (Exception exc) {}
		}
	}
    
	public void generateFloorPlan(ArrayList<Table> tables) {
		this.tables = tables;
		rects = new ArrayList<Rectangle>();
		targets = new ArrayList<Rectangle>();
		
		editor = new Editor(this);
		renderer = new Renderer(this);
		inspector = new Inspector(this);
		
		this.setLayout(new BorderLayout());
        this.add(editor, BorderLayout.WEST);
        this.add(renderer, BorderLayout.CENTER);
        this.add(inspector, BorderLayout.EAST);
        this.pack(); // absolute prankster
	}
    
    public void displayFloorPlan() {
		this.requestFocusInWindow();
        this.setVisible(true);
	}
}
