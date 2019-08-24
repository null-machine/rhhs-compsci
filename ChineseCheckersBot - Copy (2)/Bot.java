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
      stateTree.clear();
      stateTree.add(new Node(null, 0.0, 0));
      expand(0, state);

      // tell server
      state.apply(selectMove());
    }
  }

  // get current state
  // expand current state into tree
  // find best move from tree by continously expanding more nodes
  // basically repeated node expansion followed by estimations
  // uses rng to compensate for chinese checker's high branching factor

  private Move selectMove() {
    State simState = state.clone();
    ArrayList<Integer> visited = new ArrayList<Integer>();
    simState.getMoves(possibleMoves);

    // select node to expand
    int i = 0;
    while (stateTree.get(i).childCount != 0) { // keep selecting until i is index of best branch
      i = selectChild(i);
      visited.add(i);
      simState.apply(stateTree.get(i).move);
    }
    Node selection = stateTree.get(i);

    // expand node
    simState.getMoves(possibleMoves);
    double bestScore = Double.NEGATIVE_INFINITY;

    simState.apply(stateTree.get(selectChild(selection)).move);
    double value = simulate(state); // simulate = doPlayout

    // propagation
    for (int i : visited) {
      stateTree.get(i).value += value;
    }
    return stateTree.get(selectChild(0)).move;
  }

  private int selectChild(int node) {
    int start = stateTree.get(node).childStart;
		int end = start + stateTree.get(node).childCount;
		double bestConfidence = -Double.MAX_VALUE;
    int bestChild = -1;
    double confidence;
		for (int i = start; i < end; i++) { // for each child
      confidence = confidence(i, node);
			if (confidence > bestConfidence) {
				bestConfidence = confidence;
				bestChild = i;
			}
		}
		return bestChild;
  }

  // adds all next possible moves to stateTree
  private void expand(int node, State state) {
    state.getMoves(possibleMoves);
    Node n = stateTree.get(node);
    n.childStart = stateTree.size();
    n.childCount = possibleMoves.size();
    for (Move move : possibleMoves) {
      stateTree.add(new Node(move, 0, 0));
    }
  }

  // play the game greedily and see total travel distance
  // ignores other players
  // time intensive
  // return win loss bool?
  private double simulate(State state) {
    State simState = new State(state); // clone state
    ArrayList<Move> moves = new ArrayList<Move>();
    Move move;
    double totalScore = 0;
    for (int i = 0; i < depth; ++i) {
      simState.getMoves(moves);
      move = moves.get(0);
      totalScore += move.getScore();
    }
    return totalScore;
  }

  // upper confidence bound
  // confidence = average value per child move + curiosity * root(ln(total simulations of parent) / number of child moves)
  private double confidence(int nodeIndex, int parentIndex) {
    Node current = stateTree.get(nodeIndex);
    Node parent = stateTree.get(parentIndex);
    return current.value / current.frequency + curiosity * Math.sqrt(Math.log(parent.frequency) / current.frequency);
  }
}
