/* BoardState.java
 * @author Glen Wang
 * 04/20/19
 */

import java.util.ArrayList;
import java.awt.Point;
import java.util.Collections;

class Bot {

  // current board information
  private State state;
  private ArrayList<Move> possibleMoves = new ArrayList<Move>();
  private Node root;

  private int moveCap;

  public Bot() {
    System.out.println("JOINROOM androidhell");
    System.out.println("CHOOSENAME admin");

    // hard coded start
    // MOVE (7, 11) (7, 13)

    while (true) {
      // wait for turn
      // update state
      state.getMoves(possibleMoves);
      root = new Node(null, 0.0);

      for (Move move : possibleMoves) {
        root.children.add(new Node(move, state.getDist(move.target) - state.getDist(move.piece)));
      }

      Collections.sort(root.children, Collections.reverseOrder());

      state.apply(root.children.get(0).move);

      State simState = new State(state);
    }
  }
}
