

class BinarySortTree <E extends Comparable<E>> {
	
	private BinaryTreeNode<E> root;
	private int size = 0;
	
	private BinaryTreeNode<E> addRec(BinaryTreeNode<E> temp, E item) {
		if (temp == null) {
			return new BinaryTreeNode(item);
		}
		
		if (item.compareTo(temp.getItem()) < 0) {
			temp.setLeft(addRec(temp.getLeft(), item));
		} else {
			temp.setRight(addRec(temp.getRight(), item));
		}
		
		return temp;
	}
	
	public void add(E item) {
		root = addRec(root, item);
		++size;
		System.out.println("adding " + item);
	}
	
	private boolean containsRec(BinaryTreeNode<E> temp, E item) {
		if (temp == null) {
			return false;
		} 
		if (item == temp.getItem()) {
			return true;
		}
		if (item.compareTo(temp.getItem()) < 0) {
			return containsRec(temp.getLeft(), item);
		} else {
			return containsRec(temp.getRight(), item);
		}	
	}
	
	public boolean contains(E item) {
		System.out.println("contains " + item);
		return containsRec(root, item);
	}
	
	public int size() {
		return size;
	}
	
	/*
	private BinaryTreeNode<E> removeRec(BinaryTreeNode<E> temp, E item) {
		if (temp == null) {
			return null;
		}
	 
		if (temp.getItem().equals(item)) {
			if (temp.getLeft() == null && temp.getRight() == null) {
				return null;
			}
			if (temp.getRight() == null) {
				return temp.getLeft();
			}
			if (temp.getLeft() == null) {
				return temp.getRight();
			}
			
		} 
		
		if (item.compareTo(temp.getItem()) < 0) {
			temp.setLeft(removeRec(temp.getLeft(), item));
			return temp;
		} else {
			temp.setRight(removeRec(temp.getRight(), item));
			return temp;
		}
	}
	
	public void remove(E item) {
		System.out.println("remove " + item);
		--size;
		root = removeRec(root, item);
	}
	
	public boolean isEmpty() {
		return root == null ? true : false;
	}
	*/
}
	
/*	
boolean contains(E)
int size()
void add(E)			* E must be Comparable!
boolean remove(E) 		* make a method to display the tree contents!
Boolean isEmpty()
*/
