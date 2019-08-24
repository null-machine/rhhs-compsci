import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.Rectangle;
import java.awt.Point;

/**
 * Inspect.java
 * Inspection and movement tool used by renderer.
 * @author Bryan Zhang and Glen Wang
 * @version 1.0
 **/
class Inspect extends Tool {
    
    private Renderer renderer;
    private ArrayList<Rectangle> tableRects;
    private ArrayList<Rectangle> miscRects;
    private ArrayList<Integer> tableTargets;
    private ArrayList<Integer> miscTargets;
    private Rectangle selectionBox;
    private boolean clicked;
    
	/**
     * Editor
     * creates and maintains the left panel of the floor plan
     * @param tableRects - ArrayList of rectangles, representing tables
     * @param miscRects - ArrayList of rectangles, representing drawn rectangles
     * @param tableTargets - ArrayList of rectangles that have been selected
     * @param miscTargets - ArrayList of rectangles that have been selected
     */
    Inspect (Renderer renderer, ArrayList<Rectangle> tableRects, ArrayList<Rectangle> miscRects, ArrayList<Integer> tableTargets, ArrayList<Integer> miscTargets) {
        this.renderer = renderer;
        this.tableRects = tableRects;
        this.miscRects = miscRects;
        this.tableTargets = tableTargets;
        this.miscTargets = miscTargets;
    }
    
    /**
     * update
     * updates selection box size
     */
    public void update () {
        mousePos = renderer.getMousePosition();
        if(mouseLeftDown && (mousePos != null)) { // if left, pan
            moveTargeted(mousePos.x - prevMousePos.x, mousePos.y - prevMousePos.y);
            prevMousePos = mousePos;
        }
    }
    
    /**
     * moveTargeted
     * Keeps targeted rectangles stationary while moving everything else
     * @param two integers describing the visual movement of the selected rectangles
     */
    public void moveTargeted(int dx, int dy) {
        for(Integer target : tableTargets) {
            Rectangle rect = tableRects.get(target);
            rect.translate(dx, dy);
        }
        for(Integer target : miscTargets) {
            Rectangle rect = miscRects.get(target);
            rect.translate(dx, dy);
        }
    }
    
    /**
     * mousePressed
     * updates mouse-related variables and brings panel into focus
     * @param a MouseEvent for mouse info
     */
    public void mousePressed(MouseEvent e) {
        renderer.requestFocus();
        if(renderer.getMousePosition() != null) {
            prevMousePos = renderer.getMousePosition();
        }
        mouseLeftDown = (e.getButton() == MouseEvent.BUTTON1);
    }
    
    /**
     * mouseReleased
     * updates mouse-related variables
     * @param a MouseEvent for mouse info
     */
    public void mouseReleased(MouseEvent e) {
        if (!clicked) {
            renderer.undoSave();
        }
        clicked = false;
        mouseLeftDown = false;
    }
    
    public void mouseClicked(MouseEvent e) {
        clicked = true;
        tableTargets.clear();
        miscTargets.clear();
        int i = 0;
        while (i < tableRects.size() && tableTargets.size() == 0) {
            if (tableRects.get(i).contains(renderer.getMousePosition())) {
                tableTargets.add(i);
            }
            i++;
        }
        i = 0;
        while (i < miscRects.size() && miscTargets.size() == 0) {
            if (miscRects.get(i).contains(renderer.getMousePosition())) {
                miscTargets.add(i);
            }
            i++;
        }
    }
    public void mouseEntered(MouseEvent e) {
    }
    public void mouseExited(MouseEvent e) {
    }
    
}
