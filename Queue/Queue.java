
class Queue<E> { 
	
	private Node<E> head;
	private int size;
	
	public void enqueue(E item) {
		if (head==null) {
			head=new Node<E>(item,null);
			size = 0;
		} else {
			head.setNext(new Node<E>(item, null));
		}
		return;
	}
	
	public E dequeue() {
		E returnItem = head.getItem();
		if(head.getNext() == null) {
			head = null;
		} else {
			head = head.getNext();
		}
		return returnItem;
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
