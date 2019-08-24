
class SimpleLinkedList<E> { 
	
	private Node<E> head;
	private int size;
	
	public void add(E item) {
		//System.out.println("add called");
		Node<E> tempNode = head;
		
		if (head==null) {
			head=new Node<E>(item,null);
			size = 1;
			return;
		}
	
		while(tempNode.getNext()!=null) { 
			tempNode = tempNode.getNext();
			//System.out.println("hello");
		}
		
		tempNode.setNext(new Node<E>(item,null));
		++size;
		return;
	} 

	
	public E get(int index) { 
		//System.out.println("get called");
		Node temp = head;
		for(int i = 0; i < index; ++i) {
			temp = temp.getNext();
		}
		return (E)temp.getItem();
	}
	
	private Node getNode(int index) {
		if(index < 0 || index >= this.size()) {
			return null;
		}
		System.out.println("getNode called");
		Node temp = head;
		for(int i = 0; i < index; ++i) {
			temp = temp.getNext();
		}
		return temp;
	}
	
	public int indexOf(E item) {
		//System.out.println("indexOf called");
		int i = 0;
		if (item == null) {
			for(Node<E> temp = head; temp != null; temp = temp.getNext()) {
				if(item == null) {
					return i;
				}
				++i;
				System.out.println("null index check called");
			}
		} else {
			for(Node<E> temp = head; temp != null; temp = temp.getNext()) {
				if (temp.getItem().equals(item)) {
					//System.out.println("item match: " + item + " " + temp.getItem());
					return i;
				}
				++i;
			}
		}
		//System.out.println("indexOf failed\n\tsize: " + this.size() + "\n\titem: " + item + "\n\tindex: " + i);
		return -1;
	}
	
	
	public E remove(int index) {
		
		System.out.println("index remove called");
		Node prev = this.getNode(index - 1);
		Node next = this.getNode(index + 1);
		E returnItem = this.get(index);
		
		if(prev == null && next == null) { // size one
			this.clear();
			return returnItem;
		}
		
		if(prev == null) { // remove first
			head = next;
			--size;
			return returnItem;
		}
		
		if(next == null) { // remove last
			prev.setNext(null);
			--size;
			return returnItem;
		}
		
		prev.setNext(next);
		--size;
		return returnItem;
	}

	/*
	public E remove(int index) { // 1 2 3
		
		System.out.println(index + " oeauoeuoeu " + this.size());
		
		Node returnNode;
		if(index == 0) {
			returnNode = head;
			head = head.getNext();
			return (E)returnNode.getItem();
		} else {
			Node temp = head;
			for(int i = 0; i < index - 1; ++i) {
				temp = temp.getNext();
			}
			returnNode = temp.getNext();
			System.out.println(temp.getNext() + "oehuaboeuht");
			if(temp.getNext() == null) {
				System.out.println("attempt to remove out of bounds");
			} else if(temp.getNext().getNext() == null) {
				temp.setNext(null);
			} else {
				temp.setNext(temp.getNext().getNext());
			}
			return (E)returnNode.getItem();
		}
	}
	*/
	
	public boolean remove(E item) {
		System.out.println("item remove called");
		int index = this.indexOf(item);
		if(index == -1) {
			return false;
		} else {
			this.remove(index);
			--size;
			//System.out.println("item remove comparison: " + this.getNode(index).getItem() + item);
			return true;
		}
	}
	
	public void clear() { 
		System.out.println("clear called");
		if(head == null) {
			return;
		}
		head = null;
		size = 0;
	}
	
	public int size() {
		//System.out.println("size called");
		return size;
	}
}

class Node<T> {
	
	private T item;
	private Node<T> next;

	public Node(T item) {
		this.item=item;
		this.next=null;
	}

	public Node(T item, Node<T> next) {
		this.item=item;
		this.next=next;
	}

	public Node<T> getNext(){
		return this.next;
	}

	public void setNext(Node<T> next){
		this.next = next;
	}

	public T getItem(){
		return this.item;
	}

}
