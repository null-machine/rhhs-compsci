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

/**
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
    
    /**
     * FloorPlan
     * Creates the floor plan window.
     */
    FloorPlan() {
        // create jframe
        super("Floor Plan");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1280, 720);
        this.setResizable(false);
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
     * Manages initalization and adds components to the main window.
     * @param An ArrayList of the tables to display. Independant of the save and load functions.
     */
    public void generateFloorPlan(ArrayList<Table> tables) {
        this.tables = tables;
        tableRects = new ArrayList<Rectangle>();
        miscRects = new ArrayList<Rectangle>();
        tableTargets = new ArrayList<Integer>();
        miscTargets = new ArrayList<Integer>();
        
        int index = 0;
        for (int i = 0; i < Math.ceil(Math.sqrt(tables.size())); ++i) {
            for (int j = 0; j < Math.ceil(Math.sqrt(tables.size())); ++j) {
                if(index < tables.size()) {
                    tableRects.add(new Rectangle(j * 100, i * 100, 70, 70));
                    ++index;
                }
            }
        }
        
        editor = new Editor(tableRects, miscRects, tableTargets, tables);
        renderer = new Renderer(tableRects, miscRects, tableTargets, miscTargets, tables);
        
        this.setLayout(new BorderLayout());
        this.add(editor, BorderLayout.WEST);
        this.add(renderer, BorderLayout.CENTER);
        //this.add(inspector, BorderLayout.EAST);
        this.pack(); // absolute prankster
    }
    
    /**
     * displayFloorPlan
     * Makes the main window visible and starts the updater.
     * @param An ArrayList of the tables to display. Independant of the save and load functions.
     */
    public void displayFloorPlan() {
        this.requestFocusInWindow();
        this.setVisible(true);
        // launch updater
        Thread updater = new Thread(new Runnable() { public void run() { update(); } });
        updater.start();
    }
}
