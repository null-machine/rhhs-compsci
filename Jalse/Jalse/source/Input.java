import java.awt.event.AWTEventListener;
import java.awt.AWTEvent;
import java.awt.Toolkit;

import java.util.HashSet;
import java.util.Scanner;

/**
* A class that records user input, regardless of the focus subsystem.
* In this case, global input is favoured over listeners because
* the game is splitscreen, and it works well with the Controller class.
* @author Glen Wang
* @version 1.0
* @date June 11, 2019
*/
public class Input {

  private HashSet<Integer> keys; // O(1) hashset.contains

  /**
  * Gets the keys currently pressed down.
  * @return The keys currently pressed down.
  */
  public HashSet<Integer> getKeys() {
    return keys;
  }

  private GeoVector mouse;

  /**
  * Gets the last click location.
  * @return The last click location.
  */
  public GeoVector getMouse() {
    return mouse;
  };

  private Scanner getParser(String input) {
    Scanner params = new Scanner(input);
    params.useDelimiter("\\p{Punct}+");
    params.next();
    return params;
  }

  private int forceNextInt(Scanner scanner) {
    for (; scanner.hasNext(); scanner.next()) {
      if (scanner.hasNextInt()) {
        return scanner.nextInt();
      }
    }
    return -1;
  }

  /**
  * Constructs and attaches a global input listener to the default toolkit.
  */
  public Input() {
    keys = new HashSet<Integer>();
    mouse = new GeoVector(-1.0, -1.0);

    AWTEventListener keysUpdater = new KeysUpdater();
    Toolkit.getDefaultToolkit().addAWTEventListener(keysUpdater, AWTEvent.KEY_EVENT_MASK);

    AWTEventListener mouseUpdater = new MouseUpdater();
    long mouseMask = AWTEvent.MOUSE_EVENT_MASK + AWTEvent.MOUSE_MOTION_EVENT_MASK;
    Toolkit.getDefaultToolkit().addAWTEventListener(mouseUpdater, mouseMask);
  }

  private class KeysUpdater implements AWTEventListener {
    @Override
    public void eventDispatched(AWTEvent e) {
      Scanner params = getParser(e.paramString());
      String param = params.next();
      if (param.equals("PRESSED")) {
        keys.add(forceNextInt(params));
      } else if (param.equals("RELEASED")) {
        keys.remove(forceNextInt(params));
      }
    }
  }

  private class MouseUpdater implements AWTEventListener {
    @Override
    public void eventDispatched(AWTEvent e) {
      Scanner params = getParser(e.paramString());
      if (params.next().equals("RELEASED")) {
        mouse.x = forceNextInt(params);
        mouse.y = forceNextInt(params);
      }
    }
  }
}
