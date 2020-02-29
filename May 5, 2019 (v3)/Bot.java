//packagecheckers;

import java.awt.Point;

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
	
	private int turn = 1;

	public void updateState(int[][] board) {
		this.root = new State(board);
	}

	/**
	 * getMove
	 * This method will get a move.
	 * @return
	 */
	public Move getMove() {
		Move bestMove;
		if (turn > 2) { // normal move
			bestMove = getMoveMethod();
		} else { // hard coded start
			bestMove = hardCodedStart();
		}
		root.apply(bestMove);
		return bestMove;
	}
	
	/**
	 * getMoveMethod
	 * This method will get a move using a bot.
	 * @return
	 */
	private Move getMoveMethod() {
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
		return bestMove;
	}
	
	/**
	 * hardCodedStart
	 * This method will get a move according to a predetermined hard-coded start.
	 * @return
	 */
	private Move hardCodedStart() {
		Move bestMove;
		if (turn == 1) { // first turn
			bestMove = new Move(new Point(12, 5));
			bestMove.addJump(new Point(13, 6));
		} else { // second turn
			bestMove = new Move(new Point(12, 8));
			bestMove.addJump(new Point(13, 8));
		}
		turn++;
		if (isMoveValid(bestMove) == false) { // if not valid
			turn = 9;
			bestMove = getMoveMethod();
		}
		return bestMove;
	}
	
	/**
	 * isMoveValid
	 * This method will return true if a move is valid.
	 * @param move
	 * @return
	 */
	private boolean isMoveValid(Move move) {
		Point point = move.getLastJump();
		if (root.board[point.x][point.y] == 0) {
			return true;
		} else {
			return false;
		}
	}
}