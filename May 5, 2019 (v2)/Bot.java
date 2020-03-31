//package checkers;

/**
 * Bot.java
 * @author Glen
 * May 5, 2019
 */

public class Bot {
	// current board information
	private State root;

	private final double MOVE_KEEP_PERCENT = 0.5;
	private final double CENTROID_BIAS = 1.0;

	public void updateState(int[][] board) {
		this.root = new State(board);
	}

	public Move getMove() {
		root.analyze(); // move analyze method to bot to customize centroid bias
		for (Move move : root.moves) {
			root.addChild(move);
		}
		Move bestMove = root.moves.get(0);
		double bestScore = Double.NEGATIVE_INFINITY;
		for (State child : root.children) {
			child.analyze(); // reminder to take top x% moves, rounded up
			System.out.println("CHILD SCORE: " + child.score);
			if (child.score > bestScore) {
				bestScore = child.score;
				bestMove = child.previousMove;
			}
		}
		root.apply(bestMove);
		return bestMove;
	}
}
