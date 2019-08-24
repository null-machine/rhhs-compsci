/**
 * Queue.java
 * A first in first out data structure.
 * @author Glen Wang
 * March 22, 2019
 */
public class Queue<E> { 
	
	private Node<E> head;
	private Node<E> tail;
	private int size;
	
	/**
     * enqueue
     * Adds an item to the queue.
     * @param The item to be added.
     */
	public void enqueue(E item) {
		if (tail == null) {
			tail = new Node<E>(item, null);
			head = tail;
			size = 1;
		} else {
			Node<E> oldTail = tail;
			tail = new Node<E>(item, null);
			oldTail.next = tail;
			++size;
		}
		return;
	}
	
	/**
     * dequeue
     * Removes an item from the queue.
     * @return The item to be removed.
     */
	public E dequeue() {
		if(head == null) {
			System.out.println("setting size to 0: " + size());
			size = 0;
			return null;
		}
		
		E returnItem = head.getItem();
		if(head.next == null) {
			clear();
			return returnItem;
		}
		head = head.next;
		--size;
		return returnItem;
	}
	
	/**
     * clear
     * Removes all items from the queue.
     */
	public void clear() { 
		head = null;
		tail = null;
		size = 0;
	}
	
	/**
     * size
     * @return The number of items in the queue.
     */
	public int size() {
		return size;
		/*
		Node<E> i = head;
		int count = 1;
		while(i.next != null) {
			++count;
			i = i.next;
		}
		return count;
		*/
	}
	
	private class Node<T> {
		
		private T item;
		private Node<T> next;

		private Node(T item, Node<T> next) {
			this.item = item;
			this.next = next;
		}

		private T getItem(){
			return this.item;
		}
	}
}
