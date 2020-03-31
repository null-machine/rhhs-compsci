//package checkers;

/**
 * Node.java
 * @author Glen Wang
 * May 5, 2019
 */

import java.util.ArrayList;

public class Node implements Comparable<Node> {
	public Move move;
	public double value;
	// public int frequency;
	public ArrayList<Node> children;

	public Node(Move move, double value) {
		this.move = move;
		this.value = value;
		this.children = new ArrayList<Node>();
	}

	public int compareTo(Node other) {
		return (int)Math.ceil(this.value - other.value);
	}
}
