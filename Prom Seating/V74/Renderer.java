// graphics
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Color;

// gui
import javax.swing.JPanel;
import java.awt.Dimension;

// listeners
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

// util
import java.util.ArrayList;

// file io
import java.io.PrintWriter;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * Renderer.java
 * creates and maintains the center panel of the floor plan
 * @author Bryan Zhang and Glen Wang
 * @version 1.0
 **/
class Renderer extends JPanel implements KeyListener {
    
    private Tool[] tools;
    private Tool currentTool;
    private Tool prevTool;
    private Rectangle selectionBox;
    private boolean controlKeyPressed;
    
    private ArrayList<Table> tables;
    private ArrayList<Rectangle> tableRects;
    private ArrayList<Rectangle> miscRects;
    private ArrayList<Integer> tableTargets;
    private ArrayList<Integer> miscTargets;
    private ArrayList<ArrayList<Rectangle>> prevMiscRects;
    private ArrayList<ArrayList<Rectangle>> prevTableRects;
    
    /**
     * Renderer
     * creates and maintains the center panel of the floor plan
     * @param tableRects ArrayList of rectangles, representing tables
     * @param miscRects ArrayList of rectangles, representing drawn rectangles
     * @param tableTargets ArrayList of drawn tables that have been selected
     * @param miscTargets ArrayList of drawn rectangles that have been selected
     * @param tables ArrayList of tables storing student information
     */
    Renderer(ArrayList<Rectangle> tableRects, ArrayList<Rectangle> miscRects, ArrayList<Integer> tableTargets, ArrayList<Integer> miscTargets, ArrayList<Table> tables) {
        this.tableRects = tableRects;
        this.tableTargets = tableTargets;
        this.miscTargets = miscTargets;
        this.miscRects = miscRects;
        this.tables = tables;
        
        prevMiscRects = new ArrayList<ArrayList<Rectangle>>();
        prevTableRects = new ArrayList<ArrayList<Rectangle>>();
        selectionBox = new Rectangle (0, 0, 0, 0);
        
        // Try to load saved floor plan
        File savedFloorPlan = new File("savedFloorPlan.txt");
        if(savedFloorPlan.exists()) { 
            try{ loadFloorPlan(); } catch (Exception exc) {}
        }
        undoSave();
        
        tools = new Tool[4];
        tools[0] = new Pan(this, tableRects, miscRects);
        tools[1] = new Select(this, tableRects, miscRects, tableTargets, miscTargets, selectionBox);
        tools[2] = new Inspect(this, tableRects, miscRects, tableTargets, miscTargets);
        tools[3] = new Draw(this, miscRects, tableTargets, miscTargets);
        currentTool = tools[1];
        prevTool = null;
        this.addMouseListener(tools[1]);
        this.addKeyListener(this);
        this.setPreferredSize(new Dimension(960, 720));
        
        
    }
    
    /**
     * paintComponent
     * Renders the floor plan.
     * @param g Graphics used to render floor plan
     */
    public void paintComponent(Graphics g) { 
        super.paintComponent(g);
        setDoubleBuffered(true);
        setBackground(new Color(108, 91, 84)); 
        paintTables(g);
        paintTableTargets(g);
        paintMiscRects(g);
        paintMiscTargets(g);
        g.drawRect((int)selectionBox.getX(), (int)selectionBox.getY(), (int)selectionBox.getWidth(), (int)selectionBox.getHeight());
        repaint();
    }
    
    /**
     * paintTableTargets
     * Paints over targeted rectangles to indicate selected rectangles
     * @param g Graphics used to draw rectangles
     */
    private void paintTableTargets(Graphics g) {
        for (Integer target : tableTargets) {
            Rectangle rect = tableRects.get(target); 
            g.setColor(new Color(0, 0, 0, 70));
            g.fillRect((int)rect.getX(), (int)rect.getY(), (int)rect.getWidth() , (int)rect.getHeight());
        }
    }
    
    /**
     * paintMiscTargets
     * Paints over targeted rectangles to indicate selected rectangles
     * @param g Graphics used to draw rectangles
     */
    private void paintMiscTargets(Graphics g) {
        for (Integer target : miscTargets) {
            Rectangle rect = miscRects.get(target); 
            g.setColor(new Color(0, 0, 0, 70));
            g.fillRect((int)rect.getX(), (int)rect.getY(), (int)rect.getWidth() , (int)rect.getHeight());
        }
    }
    /**
     * paintTables
     * Renders all tables
     * @param g Graphics used to draw tables
     */
    private void paintTables(Graphics g) {
        for (int i = 0; i < tableRects.size(); ++i) {
            Rectangle rect = tableRects.get(i);
            g.setColor(new Color(214, 184, 164));
            g.fillRect((int)rect.getX(), (int)rect.getY(), (int)rect.getWidth() , (int)rect.getHeight());
            g.setColor(Color.BLACK);
            g.drawRect((int)rect.getX(), (int)rect.getY(), (int)rect.getWidth() , (int)rect.getHeight());
            g.drawString(Integer.toString(i + 1), (int)rect.getX() + (int)rect.getWidth() / 2, (int)rect.getY() + (int)rect.getHeight() / 2);
        }
    }
    
    /**
     * paintMiscRects
     * Renders all rectangles drawn with Draw tool
     * @param g Graphics used to draw rectangles
     */
    private void paintMiscRects(Graphics g) {
        for (int i = 0; i < miscRects.size(); i++) {
            Rectangle rect = miscRects.get(i);
            g.setColor(new Color(186, 136, 138));
            g.fillRect((int)rect.getX(), (int)rect.getY(), (int)rect.getWidth() , (int)rect.getHeight());
            g.setColor(Color.BLACK);
            g.drawRect((int)rect.getX(), (int)rect.getY(), (int)rect.getWidth() , (int)rect.getHeight());
        }
    }
    
    /**
     * setTool
     * Sets currentTool to chosen tool
     * @param toolIndex Integer of index of chosen tool
     */
    private void setTool(int toolIndex) {
        currentTool = tools[toolIndex];
        this.removeMouseListener(this.getMouseListeners()[0]);
        this.addMouseListener(currentTool);
    }
    
    /**
     * update
     * Updates the current tool
     **/
    public void update () {
        currentTool.update();
    }
    
    /**
     * undoSave
     * Saves the previous snapshot for undo
     **/
    public void undoSave () {
        ArrayList<Rectangle> oldTableRects = new ArrayList<Rectangle>();
        ArrayList<Rectangle> oldMiscRects = new ArrayList<Rectangle>();
        for (int i = 0; i < tableRects.size(); i++) {
            oldTableRects.add(new Rectangle (tableRects.get(i)));
        }
        for (int i = 0; i < miscRects.size(); i++) {
            oldMiscRects.add(new Rectangle (miscRects.get(i)));
        }
        prevTableRects.add(oldTableRects);
        prevMiscRects.add(oldMiscRects);
    }
    
    public void keyTyped(KeyEvent e) {
    }
    
    /**
     * keyPressed
     * Manages pan tool and save and load shortcuts
     * @param e KeyEvent storing key information
     **/
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_SPACE:
                if (prevTool == null) {
                prevTool = currentTool;
                setTool(0);
            }
                break;
            case KeyEvent.VK_CONTROL:
                controlKeyPressed = true;
                break;
        }
    }
    
    /**
     * keyReleased
     * manages functionality mapped to keys
     * @param e KeyEvent for key info
     */
    public void keyReleased(KeyEvent e) {
        switch(e.getKeyCode()) {
            case KeyEvent.VK_SPACE:
                currentTool = prevTool;
                prevTool = null;
                this.removeMouseListener(this.getMouseListeners()[0]);
                this.addMouseListener(currentTool);
                break;
            case KeyEvent.VK_S:
                if (controlKeyPressed) {
                try{ saveFloorPlan(); } catch (Exception exc) {}
            } else {
                setTool(1);
            }
            break;
            case KeyEvent.VK_A: 
                setTool(2);
                break;
            case KeyEvent.VK_D:
                setTool(3);
                break;
            case KeyEvent.VK_ENTER:
                tableTargets.clear();
                miscTargets.clear();
                break;
            case KeyEvent.VK_L:
                if (controlKeyPressed) {
                try{ loadFloorPlan(); } catch (Exception exc) {}
            }
                break;
            case KeyEvent.VK_Z:
                if (controlKeyPressed && prevTableRects.size() > 1) {
                tableTargets.clear();
                miscTargets.clear();
                // Remove from undo timeline
                prevTableRects.remove(prevTableRects.size() - 1);
                prevMiscRects.remove(prevMiscRects.size() - 1);
                
                // Load undone rectangles
                tableRects.clear();
                miscRects.clear();
                tableRects.addAll(prevTableRects.get(prevTableRects.size() - 1));
                miscRects.addAll(prevMiscRects.get(prevMiscRects.size() - 1));
                
                // Recopy current floorplan so current Rectangles and saved copy pointers don't point to same objects
                prevTableRects.remove(prevTableRects.size() - 1);
                prevMiscRects.remove(prevMiscRects.size() - 1);
                undoSave();
            }
                break;
            case KeyEvent.VK_CONTROL:
                controlKeyPressed = false;
                break;
        }
    }
    
    /**
     * saveFloorPlan
     * Writes currently displayed floor plan to file
     */
    private void saveFloorPlan()  throws IOException {
        File savedFloorPlan = new File ("savedFloorPlan.txt");
        PrintWriter output = new PrintWriter(savedFloorPlan);
        Rectangle currentRect;
        for (int i = 0; i < tableRects.size(); ++i) {
            currentRect = tableRects.get(i);
            output.println(currentRect.x + " " + currentRect.y + " " + currentRect.width + " " + currentRect.height);
        }
        output.println("end of saved tables");
        for (int i = 0; i < miscRects.size(); ++i) {
            currentRect = miscRects.get(i);
            output.println(currentRect.x + " " + currentRect.y + " " + currentRect.width + " " + currentRect.height);
        }
        output.println("end of misc rectangles");
        output.close();
    }
    
    /**
     * saveFloorPlan
     * Loads the most recently saved floor plan
     */
    private void loadFloorPlan() throws IOException {
        File savedFloorPlan = new File("savedFloorPlan.txt");
        Scanner input = new Scanner(savedFloorPlan);
        tableRects.clear();
        miscRects.clear();
        String rect = input.nextLine();
        while(!rect.equals("end of saved tables")) {
            String[] dimens = rect.split(" ");
            tableRects.add(new Rectangle(Integer.parseInt(dimens[0]), Integer.parseInt(dimens[1]), Integer.parseInt(dimens[2]), Integer.parseInt(dimens[3])));
            rect = input.nextLine();
        }
        rect = input.nextLine();
        while(!rect.equals("end of misc rectangles")) {
            String[] dimens = rect.split(" ");
            miscRects.add(new Rectangle(Integer.parseInt(dimens[0]), Integer.parseInt(dimens[1]), Integer.parseInt(dimens[2]), Integer.parseInt(dimens[3])));
            rect = input.nextLine();
        }
        input.close();
    }
}
