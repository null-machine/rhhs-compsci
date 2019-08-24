// graphics
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Color;

// gui
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Window;

// util
import java.util.ArrayList;

/**
 * FloorPlan.java
 * Initializes and activates the components of the floor plan editor.
 * @author Bryan Zhang and Glen Wang
 * @version 1.0
 **/
class FloorPlan extends JFrame {
    
    private ArrayList<Table> tables;
    private ArrayList<Rectangle> tableRects;
    private ArrayList<Rectangle> miscRects;
    private ArrayList<Integer> tableTargets;
    private ArrayList<Integer> miscTargets;
    
    private Editor editor;
    private Renderer renderer;
    
    private Thread updater;
    
    /**
     * FloorPlan
     * Creates the floor plan window.
     */
    FloorPlan() {
        // create jframe
        super("Floor Plan");
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent event) {
					updater.stop();
					frame.dispose();
				}
			}
		);
        this.setSize(1280, 720);
        this.setResizable(false);
        
        tableRects = new ArrayList<Rectangle>();
        miscRects = new ArrayList<Rectangle>();
        tableTargets = new ArrayList<Integer>();
        miscTargets = new ArrayList<Integer>();
    }
    
    /**
     * update
     * Calls the update method in all components.
     */
    private void update() {
        while(true) {
            try{ Thread.sleep(20); } catch (Exception exc) {}
            renderer.update();
            editor.update(tableTargets);
        }
    }
    
    /**
     * generateFloorPlan
     * Initializes data for the floor plan components.
     * @param tables An ArrayList of the tables to display. Independant of the save and load functions.
     */
    public void generateFloorPlan(ArrayList<Table> tables) {
        this.tables = tables;
        int index = 0;
        for (int i = 0; i < Math.ceil(Math.sqrt(tables.size())); ++i) {
            for (int j = 0; j < Math.ceil(Math.sqrt(tables.size())); ++j) {
                if(index < tables.size()) {
                    tableRects.add(new Rectangle(j * 100, i * 100, 70, 70));
                    ++index;
                }
            }
        }
    }
    
    /**
     * displayFloorPlan
     * Makes the main window visible and starts the updater.
     */
    public void displayFloorPlan() {
        editor = new Editor(tableRects, miscRects, tableTargets, tables);
        renderer = new Renderer(tableRects, miscRects, tableTargets, miscTargets, tables);
        tableTargets.clear();
        miscTargets.clear();
        
        this.setLayout(new BorderLayout());
        this.add(editor, BorderLayout.WEST);
        this.add(renderer, BorderLayout.CENTER);
        this.pack();
        
        this.requestFocusInWindow();
        this.setVisible(true);
        // launch updater
        updater = new Thread(new Runnable() { public void run() { update(); } });
        updater.start();
    }
}
