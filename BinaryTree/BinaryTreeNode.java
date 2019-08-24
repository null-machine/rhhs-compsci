class BinaryTreeNode<E> {
	
	private E item;
	private BinaryTreeNode<E> left;
	private BinaryTreeNode<E> right;

	public BinaryTreeNode(E item) {
		this.item = item;
	}

	public BinaryTreeNode getLeft() {
		return this.left;
	}

	public BinaryTreeNode getRight() {
		return this.right;
	}
	
	public void setLeft(BinaryTreeNode left) {
		this.left = left;
	}
	
	public void setRight(BinaryTreeNode right) {
		this.right = right;
	}
	
	public E getItem() {
		return this.item;
	}
	
	public boolean isLeaf() {
		return (this.left == null && this.right == null) ? true : false;
	}
}
