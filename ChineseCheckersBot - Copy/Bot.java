/* BoardState.java
 * @author Glen Wang
 * 04/20/19
 */

import java.util.ArrayList;
import java.awt.Point;

class Bot {

  // current board information
  private State state;

  // used an arraylist instead of a node tree because i didnt think
  // i'd be doing a tree when i first started
	ArrayList<Node> stateTree = new ArrayList<Node>();

  ArrayList<Move> possibleMoves = new ArrayList<Move>();
  ArrayList<Point> visited = new ArrayList<Point>();
  ArrayList<Move> childMoves = new ArrayList<Move>();

  // depth trades time for better moves
	private int depth = 13;
  // curiosity trades reliability for better moves
	private double curiosity = 17;

  public Bot() {
    System.out.println("JOINROOM android hell");
    System.out.println("CHOOSENAME admin");

    while (true) {
      // wait for turn
      // update board

      // play move
      stateTree.getMoves(possibleMoves);
      stateTree.clear();
      stateTree.add(new Node(null, 0.0, 0));
      expand(0, state);
      Move move = selectMove();
      
      // tell server
      state.apply(move);
    }
  }

  // get current state
  // expand current state into tree
  // find best move from tree by continously expanding more nodes
  // basically repeated node expansion followed by estimations
  // uses rng to compensate for chinese checker's high branching factor

  private Move selectMove() {
    State rootState = new State(state);
    visited.clear();
    rootState.getMoves(possibleMoves);

    // select node to expand
    int i = 0;
    while (stateTree.get(i).childCount != 0) {
      visited.add(i);
      i = selectChild(i);
      rootState.apply(stateTree.get(i).move);
    }

    // expand node
    state.getMoves(possibleMoves);
    double bestValue = 0.0;
    childMoves.clear();
    for (int j = stateTree.get(j).root; j < stateTree.get(j).childCount; ++j) {
      childMoves.add(stateTree.get(j).move);
    }
    state.apply(stateTree.get(selectChild(i)).move);
    value = simulate(state); // simulate = doPlayout

    // propagation
    for (int i : visited) {
      updateValue(p, value);
    }
    return stateTree.get(selectChild(0)).move;
  }

  private void expand(int node, State state) {
    state.getMoves(possibleMoves);
    n = stateTree.get(node);
  }

  private int simulate(State state) {
    Move move = null;
    int dist = 0;
    State simState = new State(state);
    while (dist < DEPTH) {
      move = greedyMove(simState);
      simState.apply(move);
      ++dist;
    }
    return simState.goalDist(move.target);
  }

  // upper confidence bound
  // confidence = average value per child move + curiosity * root(ln(total simulations of parent) / number of child moves)
  private double confidence(int node, int parent) {
    Node current = stateTree.get(node);
    Node parent = stateTree.get(parent);
    return current.value / current.frequency + curiosity * Math.sqrt(Math.log(parent.frequency) / current.frequency);
  }
}
