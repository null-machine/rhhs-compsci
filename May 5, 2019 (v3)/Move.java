//packagecheckers;

/**
 * Move.java
 * @author Glen, Eric, Anthony
 * May 5, 2019
 */

import java.util.ArrayList;
import java.awt.Point;

public class Move implements Comparable<Move> {
	public Point piece;
	public ArrayList<Point> jumpPath; // includes target
	public double value;

	public Move(Point piece) {
		this.piece = piece;
		this.jumpPath = new ArrayList<Point>();
	}

	public Move(Point piece, ArrayList<Point> jumpPath) {
		this.piece = piece;
		this.jumpPath = jumpPath;
	}

	public void addJump(Point jump) {
		this.jumpPath.add(jump);
	}

	public int compareTo(Move other) {
		System.out.println("HOSNTEUHSNOEHUNSTOEHUNSHOESUHOESNHNHUSNOHEU: " + (int)(this.value - other.value));
		return (int)Math.round(this.value - other.value);
	}

	public Point getLastJump() {
		return jumpPath.get(jumpPath.size()-1);
	}

	/**
	 * copy
	 * This method will create a copy of a move.
	 * @return
	 */
	public Move copy() {
		Point newPiece = new Point(piece.x, piece.y);
		ArrayList<Point> newJumpPath = new ArrayList<Point>();
		for (int i = 0; i < jumpPath.size(); i++) {
			int x = jumpPath.get(i).x;
			int y = jumpPath.get(i).y;
			Point jumpPathPoint = new Point(x, y);
			newJumpPath.add(jumpPathPoint);
		}
		return new Move(newPiece, newJumpPath);
	}

	/**
	 * output
	 * This method will output the move to the console.
	 */
	public void output() {
		System.out.print("MOVE: ");
		System.out.print("("+piece.x+","+piece.y+") --> ");
		for (int i = 0; i < jumpPath.size(); i++) {
			Point point = jumpPath.get(i);
			System.out.print("("+point.x+","+point.y+") ");
			if (i < jumpPath.size() - 1) {
				System.out.print("--> ");
			}
		}
		System.out.println("VALUE: " + value);
	}
}
