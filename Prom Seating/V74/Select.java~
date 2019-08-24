import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.Rectangle;
import java.awt.Point;

/**
 * Select.java
 * Selection tool used by renderer.
 * @author Bryan Zhang and Glen Wang
 * @version 1.0
 **/
class Select extends Tool {
    
    private Renderer renderer;
    private ArrayList<Rectangle> tableRects;
    private ArrayList<Rectangle> miscRects;
    private ArrayList<Integer> tableTargets;
    private ArrayList<Integer> miscTargets;
    private Rectangle selectionBox;
    
    /**
     * Select
     * Pan tool
     * @param renderer - Used to access mouse position relative to renderer coordinate plane
     * @param tableRects - ArrayList of rectangles, representing tables
     * @param miscRects - ArrayList of rectangles, representing drawn rectangles
     * @param tableTargets - ArrayList of rectangles that have been selected
     * @param miscTargets - ArrayList of rectangles that have been selected
     * @param selectionBox - Rectangle representing the selection box
     **/
    Select (Renderer renderer, ArrayList<Rectangle> tableRects, ArrayList<Rectangle> miscRects, ArrayList<Integer> tableTargets, ArrayList<Integer> miscTargets, Rectangle selectionBox) {
        this.renderer = renderer;
        this.tableRects = tableRects;
        this.miscRects = miscRects;
        this.tableTargets = tableTargets;
        this.miscTargets = miscTargets;
        this.selectionBox = selectionBox;
    }
    
    /**
     * update
     * updates selection box size
     */
    public void update () {
        mousePos = renderer.getMousePosition();
        if(mouseLeftDown && (mousePos != null)) { // if left, draw selection box
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
            selectionBox.setRect(mouseX, mouseY, Math.abs(dx), Math.abs(dy));
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
        tableTargets.clear();
        miscTargets.clear();
        mouseLeftDown = (e.getButton() == MouseEvent.BUTTON1);
    }
    
    /**
     * mouseReleased
     * updates mouse-related variables
     * @param a MouseEvent for mouse info
     */
    public void mouseReleased(MouseEvent e) {
        for (Rectangle rect: tableRects) {
            if (selectionBox.intersects(rect)) {
                tableTargets.add(tableRects.indexOf(rect));
            }
        }
        for (Rectangle rect: miscRects) {
            if (selectionBox.intersects(rect)) {
                miscTargets.add(miscRects.indexOf(rect));
            }
        }
        selectionBox.setRect(0, 0, 0, 0);
        mouseLeftDown = false;
    }
    
    public void mouseClicked(MouseEvent e) {
    }
    public void mouseEntered(MouseEvent e) {
    }
    public void mouseExited(MouseEvent e) {
    }
    
}
