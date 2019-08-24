import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.Rectangle;
import java.awt.Point;

/**
 * Pan.java
 * Panning tool used by renderer.
 * @author Bryan Zhang and Glen Wang
 * @version 1.0
 **/
class  Pan extends Tool {
    
    private Renderer renderer;
    private ArrayList<Rectangle> tableRects;
    private ArrayList<Rectangle> miscRects;
    
    /**
     * Pan
     * Creates a pan tool
     * @param renderer - Used to access mouse position relative to renderer coordinate plane
     * @param tableRects - ArrayList of rectangles which are moved when panning
     **/
    Pan (Renderer renderer, ArrayList<Rectangle> tableRects, ArrayList<Rectangle> miscRects) {
        this.renderer = renderer;
        this.tableRects = tableRects;
        this.miscRects = miscRects;
    }

    /**
     * moveAll
     * translates all the rects by dx and dy
     * @param two ints representing dx and dy
     */
    private void moveAll(int dx, int dy) {
        for (Rectangle rect : tableRects) {
            rect.translate(dx, dy);
        }
        for (Rectangle rect: miscRects) {
            rect.translate(dx, dy);
        }
        //System.out.println("panned");
    }
    
    /**
     * update
     * moves tables by translating them each frame
     */
    public void update () {
        mousePos = renderer.getMousePosition();
        if(mouseLeftDown && (mousePos != null)) { // if left, pan
            moveAll(mousePos.x - prevMousePos.x, mousePos.y - prevMousePos.y);
            prevMousePos = mousePos;
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
        mouseLeftDown = false;
    }
    
    public void mouseClicked(MouseEvent e) {
    }
    public void mouseEntered(MouseEvent e) {
    }
    public void mouseExited(MouseEvent e) {
    }
    
}
