import java.awt.Point;
import java.awt.event.MouseListener;

/**
 * Tool.java
 * Interface used by editing tools in the floor plan editor.
 * @author Bryan Zhang and Glen Wang
 * @version 1.0
 **/
abstract class Tool implements MouseListener, Updatable {
    protected boolean mouseLeftDown;
    protected boolean mouseRightDown;
    protected Point mousePos;
    protected Point prevMousePos;
} 
