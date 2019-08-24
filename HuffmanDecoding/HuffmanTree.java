/**
 * HuffmanTree.java
 * An extremely specialized tree for decoding .MZIP files.
 * Uses "auxin" nodes to construct the tree from strings.
 * Uses "spider" nodes as iterators for decoding based on binary instructions.
 * @author Glen Wang
 * March 22, 2019
 */
public class HuffmanTree <E extends Comparable<E>> {
	
	private Node<E> root;
	private Node<E> auxin;
	private Node<E> spider;
	
	/**
     * HuffmanTree
     * Creates a root node and sets auxin and spider to the root node.
     */
	HuffmanTree() {
		root = new Node(null, null);
		auxin = root;
		spider = root;
	}
	
	/**
     * add
     * Attempts to add an item to the left or right of the auxin node, with left taking priority.
     * If the item is null, it sets the auxin node to the new node.
     * @param The item to be added.
     */
	public void add(E item) {
		if(auxin.left == null) {
			addLeft(item);
		} else if(auxin.right == null) {
			addRight(item);
		}
	}
	
	private void addLeft(E item) {
		//System.out.println("\tadded left: " + item);
		auxin.left = new Node(item, auxin);
		if(item == null) {
			auxin = auxin.left;
			//System.out.println("\tmoved auxin down");
		}
	}
	
	private void addRight(E item) {
		//System.out.println("\tadded right: " + item);
		auxin.right = new Node(item, auxin);
		if(item == null) {
			auxin = auxin.right;
			//System.out.println("\tmoved auxin down");
		}
	}
	
	/**
     * moveUp
     * Sets the auxin node to its root node. 
     */
	public void moveUp() {
		//System.out.println("\tmoved auxin up");
		auxin = auxin.branch;
		//if(auxin.equals(root)) //System.out.println("\ttree matured");
	}
	
	/**
     * spyRoot
     * Moves the spider to the root node.
     */
	public void spyRoot() {
		//System.out.println("\tspider reset");
		spider = root;
	}
	
	/**
     * spyLeft
     * Moves the spider to its left node.
     * @return true if the node contains a value (is a leaf), and false otherwise.
     */
	public boolean spyLeft() {
		//System.out.println("\tspidering left");
		spider = spider.left;
		return spider.getItem() == null ? false : true;
	}
	
	/**
     * spyRight
     * Moves the spider to its right node.
     * @return true if the node contains a value (is a leaf), and false otherwise.
     */
	public boolean spyRight() {
		//System.out.println("\tspidering right");
		spider = spider.right;
		return spider.getItem() == null ? false : true;
	}
	
	/**
     * spyItem
     * @return The item on the spider node.
     */
	public E spyItem() {
		return spider.getItem();
	}
	
	private class Node<E> {
	
		private E item;
		private Node<E> branch;
		private Node<E> left;
		private Node<E> right;

		private Node(E item, Node<E> branch) {
			this.item = item;
			this.branch = branch;
		}
		
		private E getItem() {
			return this.item;
		}
	}
}
