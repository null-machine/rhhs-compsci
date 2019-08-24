import java.awt.event.AWTEventListener;
import java.awt.AWTEvent;
import java.awt.Toolkit;
import java.util.HashSet;

// tracks global input
public class Input {

  public static HashSet<Integer> keys; // using hashset because will be calling .contains a lot

  // parses keycodes
  private int nextInt(String str, int index) {
    char c = str.charAt(index);
    String intStr = "";
    while (Character.isDigit(c)) {
      c = str.charAt(index);
      intStr += c;
      ++index;
    }
    intStr = intStr.substring(0, intStr.length() - 1);
    return Integer.parseInt(intStr);
  }

  public Input() {
    keys = new HashSet<Integer>();

    long inputMask = AWTEvent.KEY_EVENT_MASK; // AWTEvent.MOUSE_EVENT_MASK + AWTEvent.MOUSE_MOTION_EVENT_MASK
    AWTEventListener inputListener = new InputListener();

    Toolkit.getDefaultToolkit().addAWTEventListener(inputListener, inputMask);
  }

  private class InputListener implements AWTEventListener {
    public void eventDispatched(AWTEvent e) {
      String params = e.paramString();
      // 4 and 20 denote token indices in the params
      if (params.charAt(4) == 'P') { // pressed
        keys.add(nextInt(params, 20)); // add the keycode
      } else if (params.charAt(4) == 'R') { // released
        keys.remove(nextInt(params, 21)); // remove the keycode
      }
    }
  }
}
