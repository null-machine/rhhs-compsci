import java.util.Iterator;

/**
* PoolingQueue.java
* A data structure specialized to pool objects.
* Items can only be modified once added, never removed - otherwise defeats the purpose of pooling.
* Usage trades longer loading times for better performance.
* @author Glen Wang
*/
public class PoolingQueue<T> implements Iterable<T> {

	private Node<T> head, tail;

	/**
	* pool
	* Pools an item by adding it to the queue.
	* @param E
	*/
	public void pool(T item) {
		if (tail == null) {
			tail = new Node<T>(item, null);
			head = tail;
		} else {
			Node<T> oldTail = tail;
			tail = new Node<T>(item, null);
			oldTail.next = tail;
		}
		return;
	}

	/**
	* cycle
	* Returns the item at the head of the queue, then moves said item to the tail.
	* @return a reference to the item at the head of the queue
	*/
	public T cycle() {
		if (head == null) {
			return null;
		}
		T returnItem = head.item;
		if (head.next == null) {
			return returnItem;
		}
		Node<T> oldHead = head;
		tail.next = oldHead;
		head = head.next;
		oldHead.next = null;
		tail = oldHead;
		return returnItem;
	}

	@Override
	public Iterator<T> iterator() {
		return new PoolIterator<T>(head);
	}

	private class Node<T> {
		private T item;
		private Node<T> next;

		private Node(T item, Node<T> next) {
			this.item = item;
			this.next = next;
		}
	}

	private class PoolIterator<T> implements Iterator<T> {
		private Node<T> node;

		@Override
		public boolean hasNext() {
			return (node.next != null);
		}

		@Override
		public T next() {
			node = node.next;
			return node.item;
		}

		PoolIterator(Node<T> head) {
			this.node = head;
		}
	}
}
