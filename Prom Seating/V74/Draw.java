import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.Rectangle;
import java.awt.Point;

/**
 * Draw.java
 * Draw tool used by renderer.
 * @author Bryan Zhang and Glen Wang
 * @version 1.0
 **/
class Draw extends Tool {
    
    private Renderer renderer;
    private ArrayList<Rectangle> miscRects;
    private ArrayList<Integer> tableTargets;
    private ArrayList<Integer> miscTargets;
    
    /**
     * Draw
     * Draw tool
     * @param renderer Used to access mouse position relative to renderer coordinate plane
     * @param miscRects ArrayList of Rectangles, representing drawn rectangles
     * @param tableTargets ArrayList of Rectangles, representing table rectangles that have been selected
     * @param miscTargets ArrayList of Rectangles, representing drawn rectangles that have been selected
     **/
    Draw (Renderer renderer, ArrayList<Rectangle> miscRects, ArrayList<Integer> tableTargets, ArrayList<Integer> miscTargets) {
        this.renderer = renderer;
        this.miscRects = miscRects;
        this.tableTargets = tableTargets;
        this.miscTargets = miscTargets;
    }
    
    /**
     * update
     * updates rectangle
     */
    public void update () {
        mousePos = renderer.getMousePosition();
        if(mouseLeftDown && (mousePos != null)) { // if left, draw rectangle
            int mouseX = prevMousePos.x;
            int mouseY = prevMousePos.y;
            int dx = mousePos.x - mouseX;
            int dy = mousePos.y - mouseY;
            if (dx < 0) {
                mouseX += dx;
            }
            if (dy < 0) {
                mouseY += dy;
            }
            miscRects.get(miscRects.size() - 1).setRect(mouseX, mouseY, Math.abs(dx), Math.abs(dy));
        }
    }
    
    /**
     * mousePressed
     * updates mouse-related variables and brings panel into focus
     * @param e a MouseEvent for mouse info
     */
    public void mousePressed(MouseEvent e) {
        renderer.requestFocus();
        if(renderer.getMousePosition() != null) {
            prevMousePos = renderer.getMousePosition();
            miscRects.add(new Rectangle(prevMousePos.x, prevMousePos.y, 0, 0));
        }
        tableTargets.clear();
        miscTargets.clear();
        mouseLeftDown = (e.getButton() == MouseEvent.BUTTON1);
    }
    
    /**
     * mouseReleased
     * updates mouse-related variables
     * @param e a MouseEvent for mouse info
     */
    public void mouseReleased(MouseEvent e) {
        renderer.undoSave();
        mouseLeftDown = false;
    }
    
    public void mouseClicked(MouseEvent e) {
    }
    public void mouseEntered(MouseEvent e) {
    }
    public void mouseExited(MouseEvent e) {
    }
    
}
