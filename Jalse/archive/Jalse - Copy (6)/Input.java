import java.awt.event.AWTEventListener;
import java.awt.AWTEvent;
import java.awt.Toolkit;

import java.util.HashSet;
import java.util.Scanner;

// parses global input
// necessary because splitscreen
public class Input {

  private HashSet<Integer> keys; // O(1) hashset.contains
  public HashSet<Integer> getKeys() { return keys; }
  private GeoVector mouse;
  public GeoVector getMouse() { return mouse; };

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
  * Input
  * Adds a global input listener to the default toolkit.
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
      switch (params.next()) {
        case "PRESSED":
          keys.add(forceNextInt(params));
          break;
        case "RELEASED":
          keys.remove(forceNextInt(params));
          break;
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
